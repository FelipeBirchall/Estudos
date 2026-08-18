# Entregável 3 — Protótipo WebGIS

Protótipo funcional em HTML/CSS/JavaScript puro, usando:

- **[Leaflet](https://leafletjs.com/)** — mapa interativo (tiles OpenStreetMap);
- **[Turf.js](https://turfjs.org/)** — consultas espaciais (`booleanPointInPolygon`,
  `booleanContains`, `booleanIntersects`, `distance`, `circle`).

Ambas as bibliotecas estão **vendorizadas localmente** em `vendor/` (não
dependem de CDN), então filtros, raio, ordenação e consultas espaciais
funcionam mesmo offline. O único recurso que exige internet no navegador são
os **tiles do mapa-base** (imagens do OpenStreetMap) — sem internet o mapa
aparece em branco, mas marcadores, círculo de raio, polígono e linha
continuam sendo desenhados e a lógica de busca funciona normalmente.

## Como executar

Não há backend: é uma aplicação client-side estática. Duas formas de rodar:

**Opção A — abrir direto:**
Abra o arquivo `index.html` no navegador (duplo clique ou `Ctrl+O`).

**Opção B — servidor local (recomendado):**
```bash
cd "trabalho engenharia de software/03-prototipo-webgis"
python3 -m http.server 8000
# depois acesse http://localhost:8000
```

## O que o protótipo implementa

| Requisito do enunciado | Onde está no protótipo |
|---|---|
| Selecionar hospital (ponto de referência) | Seletor "1. Hospital de referência" |
| Filtro por categoria (hospital / farmácia / laboratório) | Seletor "2. Categoria" |
| Raio de busca configurável (200 m, 500 m, 1000 m, 2000 m ou custom) | Seletor "3. Raio de busca" |
| Ordenação por distância ou alfabética | Seletor "4. Ordenar por" |
| Consulta espacial — **está contido** | Cada estabelecimento é testado com `turf.booleanPointInPolygon` contra o círculo do raio de busca |
| Consulta espacial — **contém** | Checkbox "Exibir bairro (polígono)": testa se o polígono do bairro contém o hospital (`turf.booleanContains`) |
| Consulta espacial — **intercepta** | Checkbox "Exibir via/rota (linha)": testa se a linha intercepta o círculo do raio (`turf.booleanIntersects`) |
| Pontos, linhas e polígonos | Farmácias/laboratórios/hospitais = pontos; via = linha (`LineString`); bairro = polígono (`Polygon`) |
| Resultados exibidos em lista **e** no mapa | Lista lateral sincronizada com marcadores — clicar em um item centraliza o marcador; clicar no marcador destaca o item |
| Estória 1 (familiar busca farmácia) | Fluxo padrão: categoria "Farmácias" + raio + busca |
| Estória 2 (médico busca laboratório) | Fluxo padrão: categoria "Laboratórios de radiografia" + raio + busca |

## Estrutura do código

```
03-prototipo-webgis/
├── index.html        <- estrutura da página e painel de controles
├── css/style.css     <- estilo visual
├── js/data.js        <- dados geográficos de exemplo (GeoJSON: pontos, linha, polígono)
├── js/app.js         <- lógica: estado, consulta espacial, renderização no mapa/lista
└── vendor/           <- Leaflet e Turf.js vendorizados (sem dependência de CDN)
    ├── leaflet/
    └── turf/
```

## Observação sobre os dados

Os nomes de farmácias, laboratórios e o hospital, bem como suas coordenadas,
são **fictícios/ilustrativos**, criados para fins didáticos e para permitir
testar os operadores espaciais do protótipo. Eles aproximam a região do
bairro Bonsucesso em Belo Horizonte apenas como referência geográfica.
