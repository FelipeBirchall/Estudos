/**
 * Protótipo WebGIS — Saúde BH
 * Implementa: seleção de hospital, raio de busca, filtro por categoria,
 * consulta espacial (está contido / contém / intercepta) via Turf.js,
 * ordenação (distância ou alfabética) e visualização em mapa (Leaflet).
 */
(function () {
  "use strict";

  const HOSPITAIS = [HOSPITAL, ...HOSPITAIS_OUTROS.features];

  const CATEGORIA_STYLE = {
    hospital: { emoji: "🏥", cor: "#c62828" },
    farmacia: { emoji: "💊", cor: "#dcc816" },
    laboratorio: { emoji: "🧪", cor: "#0d6b08" },
  };

  // -------------------- Estado --------------------
  const state = {
    hospitalId: HOSPITAL.properties.id,
    categoria: "farmacia",
    raioMetros: 500,
    ordenar: "distancia",
    mostrarBairro: false,
    mostrarRota: false,
  };

  // -------------------- Mapa --------------------
  const map = L.map("map").setView(
    [HOSPITAL.geometry.coordinates[1], HOSPITAL.geometry.coordinates[0]],
    15
  );

  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "&copy; OpenStreetMap contributors",
    maxZoom: 19,
  }).addTo(map);

  const layerHospital = L.layerGroup().addTo(map);
  const layerResultados = L.layerGroup().addTo(map);
  const layerRaio = L.layerGroup().addTo(map);
  const layerBairro = L.layerGroup().addTo(map);
  const layerRota = L.layerGroup().addTo(map);

  function iconFor(categoria, destaque) {
    const s = CATEGORIA_STYLE[categoria] || { emoji: "📍", cor: "#555" };
    const tamanho = destaque ? 34 : 26;
    return L.divIcon({
      className: "",
      html: `<div style="
        width:${tamanho}px;height:${tamanho}px;border-radius:50%;
        background:${s.cor};display:flex;align-items:center;justify-content:center;
        font-size:${destaque ? 16 : 13}px; box-shadow:0 1px 4px rgba(0,0,0,.4);
        border:2px solid white;">${s.emoji}</div>`,
      iconSize: [tamanho, tamanho],
      iconAnchor: [tamanho / 2, tamanho / 2],
    });
  }

  // -------------------- Utilidades --------------------
  function getHospital() {
    return HOSPITAIS.find((h) => h.properties.id === state.hospitalId) || HOSPITAL;
  }

  function formatDistancia(m) {
    return m < 1000 ? `${Math.round(m)} m` : `${(m / 1000).toFixed(2)} km`;
  }

  function todosCandidatos() {
    return [...FARMACIAS.features, ...LABORATORIOS.features, ...HOSPITAIS.filter(
      (h) => h.properties.id !== state.hospitalId
    )];
  }

  // -------------------- Consulta espacial (UC04) --------------------
  function executarConsulta() {
    const hospital = getHospital();
    const hospitalPoint = turf.point(hospital.geometry.coordinates);
    const raioKm = state.raioMetros / 1000;
    const area = turf.circle(hospital.geometry.coordinates, raioKm, {
      steps: 64,
      units: "kilometers",
    });

    const candidatos = todosCandidatos().filter(
      (f) => f.properties.categoria === state.categoria
    );

    let resultados = candidatos
      .map((f) => {
        const ponto = turf.point(f.geometry.coordinates);
        const estaContido = turf.booleanPointInPolygon(ponto, area); // operador: está contido
        if (!estaContido) return null;
        const distanciaM = turf.distance(hospitalPoint, ponto, { units: "kilometers" }) * 1000;
        return { feature: f, distanciaM, estaContido };
      })
      .filter(Boolean);

    resultados.sort((a, b) =>
      state.ordenar === "alfabetica"
        ? a.feature.properties.nome.localeCompare(b.feature.properties.nome, "pt-BR")
        : a.distanciaM - b.distanciaM
    );

    return { hospital, area, resultados };
  }

  // -------------------- Renderização do mapa --------------------
  function renderHospital(hospital) {
    layerHospital.clearLayers();
    const [lng, lat] = hospital.geometry.coordinates;
    L.marker([lat, lng], { icon: iconFor("hospital", true) })
      .bindPopup(`<b>${hospital.properties.nome}</b><br>${hospital.properties.endereco}<br><em>Ponto de referência</em>`)
      .addTo(layerHospital);
  }

  function renderRaio(area) {
    layerRaio.clearLayers();
    L.geoJSON(area, {
      style: { color: "#2c5f8a", weight: 2, dashArray: "6 4", fillOpacity: 0.06 },
    }).addTo(layerRaio);
  }

  function renderResultados(resultados) {
    layerResultados.clearLayers();
    resultados.forEach(({ feature, distanciaM }) => {
      const [lng, lat] = feature.geometry.coordinates;
      L.marker([lat, lng], { icon: iconFor(feature.properties.categoria, false) })
        .bindPopup(
          `<b>${feature.properties.nome}</b><br>${feature.properties.endereco}<br>Distância: ${formatDistancia(distanciaM)}`
        )
        .on("click", () => destacarItemLista(feature.properties.id))
        .addTo(layerResultados);
    });
  }

  function renderLista(resultados) {
    const ul = document.getElementById("resultados-lista");
    const count = document.getElementById("resultados-count");
    ul.innerHTML = "";
    count.textContent = `(${resultados.length})`;

    if (resultados.length === 0) {
      const li = document.createElement("li");
      li.className = "resultado-vazio";
      li.textContent = "Nenhum estabelecimento encontrado neste raio. Tente aumentar o raio de busca.";
      ul.appendChild(li);
      return;
    }

    resultados.forEach(({ feature, distanciaM }) => {
      const li = document.createElement("li");
      li.className = "resultado-item";
      li.dataset.id = feature.properties.id;
      li.innerHTML = `
        <div class="nome">${CATEGORIA_STYLE[feature.properties.categoria].emoji} ${feature.properties.nome}</div>
        <div class="meta"><span>${feature.properties.endereco}</span><span>${formatDistancia(distanciaM)}</span></div>
      `;
      li.addEventListener("click", () => {
        const [lng, lat] = feature.geometry.coordinates;
        map.setView([lat, lng], 17);
        layerResultados.eachLayer((m) => {
          if (m.getLatLng().lat === lat && m.getLatLng().lng === lng) m.openPopup();
        });
      });
      ul.appendChild(li);
    });
  }

  function destacarItemLista(id) {
    document.querySelectorAll(".resultado-item").forEach((el) => {
      el.style.background = el.dataset.id === id ? "#eaf3fb" : "";
    });
  }

  // -------------------- Camadas espaciais extras (contém / intercepta) --------------------
  function renderCamadasEspaciais(hospital, area) {
    const hospitalPoint = turf.point(hospital.geometry.coordinates);
    const infoDiv = document.getElementById("spatial-info");
    let infoHtml = "";

    layerBairro.clearLayers();
    if (state.mostrarBairro) {
      const contem = turf.booleanContains(BAIRRO, hospitalPoint); // operador: contém
      L.geoJSON(BAIRRO, {
        style: { color: "#8e24aa", weight: 2, fillOpacity: 0.05 },
      })
        .bindPopup(`<b>${BAIRRO.properties.nome}</b>`)
        .addTo(layerBairro);
      infoHtml += `<div>Polígono do bairro <b>${contem ? "contém" : "não contém"}</b> o hospital: <span class="${contem ? "ok" : "no"}">${contem ? "✔ contém" : "✘ não contém"}</span></div>`;
    }

    layerRota.clearLayers();
    if (state.mostrarRota) {
      const intercepta = turf.booleanIntersects(ROTA, area); // operador: intercepta
      L.geoJSON(ROTA, { style: { color: "#ef6c00", weight: 4 } })
        .bindPopup(`<b>${ROTA.properties.nome}</b>`)
        .addTo(layerRota);
      infoHtml += `<div>Via/rota <b>${intercepta ? "intercepta" : "não intercepta"}</b> a área de busca: <span class="${intercepta ? "ok" : "no"}">${intercepta ? "✔ intercepta" : "✘ não intercepta"}</span></div>`;
    }

    infoDiv.innerHTML = infoHtml;
  }

  // -------------------- Orquestração (UC05 / UC06) --------------------
  function buscar() {
    const { hospital, area, resultados } = executarConsulta();
    renderHospital(hospital);
    renderRaio(area);
    renderResultados(resultados);
    renderLista(resultados);
    renderCamadasEspaciais(hospital, area);
  }

  // -------------------- Inicialização dos controles --------------------
  function initHospitalSelect() {
    const select = document.getElementById("hospital-select");
    HOSPITAIS.forEach((h) => {
      const opt = document.createElement("option");
      opt.value = h.properties.id;
      opt.textContent = h.properties.nome;
      select.appendChild(opt);
    });
    select.value = state.hospitalId;
    select.addEventListener("change", (e) => {
      state.hospitalId = e.target.value;
      const h = getHospital();
      map.setView([h.geometry.coordinates[1], h.geometry.coordinates[0]], 15);
      buscar();
    });
  }

  function initCategoria() {
    document.querySelectorAll('input[name="categoria"]').forEach((el) => {
      el.addEventListener("change", (e) => {
        state.categoria = e.target.value;
        buscar();
      });
    });
  }

  function initRaio() {
    document.querySelectorAll('input[name="raio"]').forEach((el) => {
      el.addEventListener("change", (e) => {
        state.raioMetros = Number(e.target.value);
        document.getElementById("raio-custom-input").value = "";
        buscar();
      });
    });
    document.getElementById("raio-custom-input").addEventListener("input", (e) => {
      const v = Number(e.target.value);
      if (v > 0) {
        state.raioMetros = v;
        document.querySelectorAll('input[name="raio"]').forEach((r) => (r.checked = false));
        buscar();
      }
    });
  }

  function initOrdenar() {
    document.querySelectorAll('input[name="ordenar"]').forEach((el) => {
      el.addEventListener("change", (e) => {
        state.ordenar = e.target.value;
        buscar();
      });
    });
  }

  function initCamadas() {
    document.getElementById("toggle-bairro").addEventListener("change", (e) => {
      state.mostrarBairro = e.target.checked;
      buscar();
    });
    document.getElementById("toggle-rota").addEventListener("change", (e) => {
      state.mostrarRota = e.target.checked;
      buscar();
    });
  }

  function initBotaoBuscar() {
    document.getElementById("btn-buscar").addEventListener("click", buscar);
  }

  initHospitalSelect();
  initCategoria();
  initRaio();
  initOrdenar();
  initCamadas();
  initBotaoBuscar();
  buscar();
})();
