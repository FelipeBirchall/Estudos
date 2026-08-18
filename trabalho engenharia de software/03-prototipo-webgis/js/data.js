/**
 * Dados geográficos de exemplo (ILUSTRATIVOS) para o protótipo WebGIS.
 * Nomes e endereços são fictícios, criados para fins didáticos.
 * Coordenadas aproximam a região do bairro Bonsucesso, Belo Horizonte/MG,
 * apenas para ancorar o exemplo geograficamente — não representam um
 * cadastro real de estabelecimentos.
 */

// Ponto de referência: hospital principal usado nas Estórias 1 e 2.
const HOSPITAL = {
  type: "Feature",
  properties: {
    id: "hosp-1",
    nome: "Hospital Municipal Referência BH",
    categoria: "hospital",
    endereco: "Rua das Acácias, 100 — Bonsucesso, Belo Horizonte/MG",
  },
  geometry: { type: "Point", coordinates: [-43.9587, -19.9021] }, // [lng, lat]
};

// Segundo hospital, só para demonstrar o filtro por categoria "hospital".
const HOSPITAIS_OUTROS = {
  type: "FeatureCollection",
  features: [
    {
      type: "Feature",
      properties: {
        id: "hosp-2",
        nome: "Hospital Regional Leste",
        categoria: "hospital",
        endereco: "Av. dos Ipês, 850 — Leste, Belo Horizonte/MG",
      },
      geometry: { type: "Point", coordinates: [-43.9430, -19.8965] },
    },
  ],
};

const FARMACIAS = {
  type: "FeatureCollection",
  features: [
    {
      type: "Feature",
      properties: { id: "farm-1", nome: "Farmácia Compacta", categoria: "farmacia", endereco: "Rua das Acácias, 140 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9592, -19.9019] },
    },
    {
      type: "Feature",
      properties: { id: "farm-2", nome: "Farmácia Vida Nova", categoria: "farmacia", endereco: "Rua Girassol, 55 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9577, -19.9006] },
    },
    {
      type: "Feature",
      properties: { id: "farm-3", nome: "Drogaria Bonsucesso", categoria: "farmacia", endereco: "Av. Bonsucesso, 320 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9562, -19.9046] },
    },
    {
      type: "Feature",
      properties: { id: "farm-4", nome: "Farmácia Popular Central", categoria: "farmacia", endereco: "Rua Tulipas, 210 — Carlos Prates" },
      geometry: { type: "Point", coordinates: [-43.9617, -19.9051] },
    },
    {
      type: "Feature",
      properties: { id: "farm-5", nome: "Farmácia São Jorge", categoria: "farmacia", endereco: "Rua Orquídeas, 78 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9602, -19.8971] },
    },
    {
      type: "Feature",
      properties: { id: "farm-6", nome: "Drogaria Saúde Já", categoria: "farmacia", endereco: "Av. Contorno Norte, 1200 — Padre Eustáquio" },
      geometry: { type: "Point", coordinates: [-43.9527, -19.8961] },
    },
    {
      type: "Feature",
      properties: { id: "farm-7", nome: "Farmácia Nova Esperança", categoria: "farmacia", endereco: "Rua Cravos, 300 — Padre Eustáquio" },
      geometry: { type: "Point", coordinates: [-43.9687, -19.9101] },
    },
    {
      type: "Feature",
      properties: { id: "farm-8", nome: "Farmácia Ideal", categoria: "farmacia", endereco: "Av. Amazonas, 4500 — Calafate" },
      geometry: { type: "Point", coordinates: [-43.9437, -19.8921] },
    },
  ],
};

const LABORATORIOS = {
  type: "FeatureCollection",
  features: [
    {
      type: "Feature",
      properties: { id: "lab-1", nome: "Laboratório RaioX Express", categoria: "laboratorio", endereco: "Rua das Acácias, 90 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9584, -19.9018] },
    },
    {
      type: "Feature",
      properties: { id: "lab-2", nome: "Laboratório Imagem Diagnóstica BH", categoria: "laboratorio", endereco: "Rua Girassol, 200 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9569, -19.9039] },
    },
    {
      type: "Feature",
      properties: { id: "lab-3", nome: "Centro de Radiologia São Lucas", categoria: "laboratorio", endereco: "Av. Bonsucesso, 410 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9612, -19.9031] },
    },
    {
      type: "Feature",
      properties: { id: "lab-4", nome: "RadCenter Diagnóstico por Imagem", categoria: "laboratorio", endereco: "Rua Orquídeas, 150 — Bonsucesso" },
      geometry: { type: "Point", coordinates: [-43.9532, -19.8981] },
    },
    {
      type: "Feature",
      properties: { id: "lab-5", nome: "Clínica de Radiografia Horizonte", categoria: "laboratorio", endereco: "Rua Cravos, 88 — Padre Eustáquio" },
      geometry: { type: "Point", coordinates: [-43.9657, -19.8971] },
    },
    {
      type: "Feature",
      properties: { id: "lab-6", nome: "Instituto de Diagnóstico por Imagem BH", categoria: "laboratorio", endereco: "Av. Amazonas, 4700 — Calafate" },
      geometry: { type: "Point", coordinates: [-43.9467, -19.9111] },
    },
  ],
};

// Polígono do bairro (demonstra os operadores espaciais "contém" / "está contido").
const BAIRRO = {
  type: "Feature",
  properties: { id: "bairro-1", nome: "Bairro Bonsucesso (limite ilustrativo)" },
  geometry: {
    type: "Polygon",
    coordinates: [[
      [-43.9660, -19.8955],
      [-43.9500, -19.8955],
      [-43.9500, -19.9080],
      [-43.9660, -19.9080],
      [-43.9660, -19.8955],
    ]],
  },
};

// Linha (via/rota) usada para demonstrar o operador espacial "intercepta".
const ROTA = {
  type: "Feature",
  properties: { id: "rota-1", nome: "Av. Presidente Antônio Carlos (trecho ilustrativo)" },
  geometry: {
    type: "LineString",
    coordinates: [
      [-43.9750, -19.9150],
      [-43.9650, -19.9080],
      [-43.9587, -19.9021],
      [-43.9500, -19.8950],
      [-43.9400, -19.8880],
    ],
  },
};
