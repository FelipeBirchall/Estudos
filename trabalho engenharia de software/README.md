# Trabalho de Engenharia de Software — WebGIS de Saúde (Belo Horizonte)
### Feito por Felipe Birchal e Filipe Lorenzato

## Tema

Especificação e prototipação de um sistema **WebGIS** para localização de
serviços de saúde (farmácias e laboratórios de radiografia) próximos a um
hospital em Belo Horizonte, com apoio a consultas espaciais, filtros e
ordenação por distância.

## Estrutura do trabalho

```
trabalho engenharia de software/
├── README.md                              <- este arquivo (visão geral)
├── 01-diagrama-casos-de-uso/
│   ├── diagrama-casos-de-uso.puml         <- diagrama UML (PlantUML)
│   └── diagrama-casos-de-uso.md           <- diagrama (Mermaid) + leitura do diagrama
├── 02-especificacao-casos-de-uso/
│   └── especificacao-casos-de-uso.md      <- especificação textual de cada caso de uso
└── 03-prototipo-webgis/
    ├── index.html                         <- protótipo funcional (abrir no navegador)
    ├── css/style.css
    ├── js/app.js
    ├── js/data.js                         <- dados geográficos de exemplo (GeoJSON)
    └── README.md                          <- como rodar e como o protótipo atende as estórias
```

## Entregáveis

1. **Diagrama de Casos de Uso** — pasta `01-diagrama-casos-de-uso/`.
2. **Especificação textual dos casos de uso** — pasta `02-especificacao-casos-de-uso/`.
3. **Protótipo WebGIS funcional** — pasta `03-prototipo-webgis/`.

## Resumo do problema

- **Referência espacial:** um hospital em Belo Horizonte (ponto).
- **Usuários:** familiar de paciente (busca farmácias) e médico (busca
  laboratórios de radiografia).
- **Consulta:** raio de busca configurável (ex.: 200 m, 500 m, 1000 m,
  2000 m), filtro por categoria (hospital / farmácia / laboratório),
  ordenação por distância ou alfabética.
- **Recursos de SIG explorados:** pontos (hospital, farmácias,
  laboratórios), linha (via/rota) e polígono (bairro), com os operadores
  espaciais **está contido**, **contém** e **intercepta**.

## Uso responsável de IA

Este material foi produzido com apoio de IA (assistente de programação) para
acelerar a escrita da especificação e a implementação do protótipo. Os dados
geográficos (nomes de farmácias/laboratórios e coordenadas) usados no
protótipo são **ilustrativos**, criados para fins didáticos — não
correspondem a um cadastro real e não devem ser usados como fonte de
informação factual sobre estabelecimentos de Belo Horizonte. A localização
do hospital de referência foi aproximada por região (bairro Bonsucesso, BH)
apenas para ancorar o exemplo geograficamente.
