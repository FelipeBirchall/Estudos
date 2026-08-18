# Entregável 2 — Especificação Textual dos Casos de Uso

Especificação de todos os casos de uso presentes no diagrama
(`01-diagrama-casos-de-uso/`), no formato de tabela exigido pelo enunciado.

---

## UC01 — Selecionar Hospital

| Campo | Conteúdo |
|---|---|
| **Nome** | Selecionar Hospital |
| **Descrição** | Permite ao usuário escolher, em uma lista ou diretamente no mapa, o hospital que servirá como ponto de referência (origem) para as buscas de farmácias e laboratórios. |
| **Fluxo principal** | 1. Usuário abre o sistema. 2. Sistema exibe o mapa de Belo Horizonte com o(s) hospital(is) cadastrado(s) marcado(s). 3. Usuário seleciona um hospital na lista ou clica no marcador do hospital no mapa. 4. Sistema centraliza o mapa no hospital selecionado e define esse ponto como referência para as próximas consultas. |
| **Fluxos alternativos** | A1. Sistema possui apenas um hospital cadastrado: passo 3 é automático (hospital único é pré-selecionado). A2. Usuário tenta prosseguir sem selecionar hospital: sistema bloqueia a busca e exibe aviso "Selecione um hospital de referência". |
| **Pré-condições** | Sistema carregado com ao menos um hospital cadastrado com coordenadas geográficas válidas. |
| **Pós-condições** | Hospital definido como ponto de referência (origem) para os casos de uso `Localizar Farmácias Próximas` e `Localizar Laboratórios de Radiografia Próximos`. |

---

## UC02 — Definir Raio de Busca

| Campo | Conteúdo |
|---|---|
| **Nome** | Definir Raio de Busca |
| **Descrição** | Permite ao usuário configurar a distância máxima (raio), a partir do hospital, dentro da qual os estabelecimentos serão buscados. |
| **Fluxo principal** | 1. Usuário visualiza as opções de raio predefinidas (200 m, 500 m, 1000 m, 2000 m). 2. Usuário seleciona um valor predefinido ou informa um valor customizado. 3. Sistema valida o valor informado. 4. Sistema atualiza o círculo de busca desenhado no mapa em torno do hospital. |
| **Fluxos alternativos** | A1. Valor customizado inválido (negativo, zero ou não numérico): sistema exibe mensagem de erro e mantém o último raio válido. A2. Usuário altera o raio após já ter resultados exibidos: sistema recalcula e atualiza automaticamente a lista e o mapa (reexecuta `Realizar Consulta Espacial`). |
| **Pré-condições** | Hospital de referência já selecionado (`Selecionar Hospital`). |
| **Pós-condições** | Raio de busca definido e representado como área circular no mapa, pronto para uso pela consulta espacial. |

---

## UC03 — Filtrar por Categoria

| Campo | Conteúdo |
|---|---|
| **Nome** | Filtrar por Categoria |
| **Descrição** | Permite restringir a busca a um tipo específico de estabelecimento de saúde: hospital, farmácia ou laboratório de radiografia. |
| **Fluxo principal** | 1. Usuário abre o painel de filtros. 2. Usuário marca a(s) categoria(s) desejada(s) (ex.: apenas "Farmácia"). 3. Sistema aplica o filtro sobre o conjunto de estabelecimentos antes de executar a consulta espacial. |
| **Fluxos alternativos** | A1. Nenhuma categoria selecionada: sistema assume todas as categorias como padrão. A2. Usuário troca o filtro após uma busca já realizada: sistema reprocessa a lista e o mapa automaticamente. |
| **Pré-condições** | Sistema com o cadastro de estabelecimentos carregado. |
| **Pós-condições** | Conjunto de estabelecimentos restrito à(s) categoria(s) escolhida(s), usado como entrada para `Realizar Consulta Espacial`. |

---

## UC04 — Realizar Consulta Espacial

| Campo | Conteúdo |
|---|---|
| **Nome** | Realizar Consulta Espacial |
| **Descrição** | Motor de consulta geográfica do sistema: a partir do hospital (ponto), do raio de busca e do filtro de categoria, calcula quais estabelecimentos satisfazem as relações espaciais exigidas, usando os operadores está contido, contém e intercepta. |
| **Fluxo principal** | 1. Sistema gera a área de busca (polígono circular) a partir do hospital e do raio definido. 2. Sistema aplica o operador **está contido** para cada estabelecimento candidato (ponto do estabelecimento está contido na área de busca?). 3. Sistema calcula a distância euclidiana/geodésica de cada estabelecimento contido até o hospital. 4. Sistema retorna a lista de estabelecimentos que satisfazem a consulta, com suas distâncias. |
| **Fluxos alternativos** | A1 (extend — *Aplicar operador: Contém*). Usuário ativa a camada de bairro (polígono): sistema verifica se o polígono do bairro **contém** o hospital ou um estabelecimento, destacando a relação no painel de informações. A2 (extend — *Aplicar operador: Intercepta*). Usuário ativa a camada de via/rota (linha): sistema verifica se a linha **intercepta** a área de busca (círculo do raio) e destaca o trecho correspondente. A3. Nenhum estabelecimento satisfaz a consulta: sistema retorna lista vazia, tratada em `Localizar Farmácias Próximas`/`Localizar Laboratórios de Radiografia Próximos`. |
| **Pré-condições** | Hospital selecionado, raio definido e filtro de categoria (opcional) aplicado. |
| **Pós-condições** | Conjunto de estabelecimentos que satisfazem a(s) relação(ões) espacial(is) consultada(s), pronto para ordenação (`Listar Resultados por Distância`) e exibição (`Visualizar Resultados no Mapa`). |

---

## UC05 — Localizar Farmácias Próximas

| Campo | Conteúdo |
|---|---|
| **Nome** | Localizar Farmácias Próximas |
| **Descrição** | Permite ao familiar do paciente localizar farmácias em torno do hospital selecionado, dentro de um raio definido, para encontrar rapidamente a opção mais conveniente (Estória 1). |
| **Fluxo principal** | 1. Usuário seleciona o hospital (*include* UC01). 2. Usuário define o raio de busca (*include* UC02). 3. Usuário filtra a categoria "Farmácia" (*include* UC03). 4. Sistema executa a consulta espacial (*include* UC04) e obtém as farmácias contidas no raio. 5. Sistema ordena os resultados por distância (*include* UC07). 6. Sistema exibe a lista ordenada e os marcadores das farmácias no mapa (*include* UC08). |
| **Fluxos alternativos** | A1. Nenhuma farmácia encontrada no raio: sistema exibe mensagem "Nenhuma farmácia encontrada neste raio" e sugere aumentar o raio de busca. A2. Usuário altera o raio ou o critério de ordenação e refaz a busca: sistema repete os passos 4–6 automaticamente. A3. Usuário troca o critério de ordenação para alfabética (*extend* UC07b). |
| **Pré-condições** | Hospital disponível no sistema. |
| **Pós-condições** | Lista de farmácias exibida em ordem de proximidade (ou alfabética), com marcadores correspondentes plotados no mapa. |

---

## UC06 — Localizar Laboratórios de Radiografia Próximos

| Campo | Conteúdo |
|---|---|
| **Nome** | Localizar Laboratórios de Radiografia Próximos |
| **Descrição** | Permite ao médico do paciente localizar laboratórios de radiografia próximos ao hospital selecionado, dentro de um raio definido, para encaminhar o paciente ao serviço mais adequado e próximo (Estória 2). |
| **Fluxo principal** | 1. Usuário (médico) seleciona o hospital (*include* UC01). 2. Usuário define o raio de busca (*include* UC02). 3. Usuário filtra a categoria "Laboratório de Radiografia" (*include* UC03). 4. Sistema executa a consulta espacial (*include* UC04) e obtém os laboratórios contidos no raio. 5. Sistema ordena os resultados por distância (*include* UC07). 6. Sistema exibe a lista ordenada e os marcadores dos laboratórios no mapa (*include* UC08). |
| **Fluxos alternativos** | A1. Nenhum laboratório encontrado no raio: sistema exibe mensagem e sugere aumentar o raio. A2. Médico altera o raio e refaz a busca: sistema repete os passos 4–6. A3. Médico troca o critério de ordenação para alfabética (*extend* UC07b). |
| **Pré-condições** | Hospital disponível no sistema. |
| **Pós-condições** | Lista de laboratórios de radiografia exibida em ordem de proximidade (ou alfabética), com marcadores correspondentes plotados no mapa. |

---

## UC07 — Listar Resultados por Distância

| Campo | Conteúdo |
|---|---|
| **Nome** | Listar Resultados por Distância |
| **Descrição** | Apresenta os estabelecimentos retornados pela consulta espacial em uma lista ordenada, do mais próximo ao mais distante do hospital de referência. |
| **Fluxo principal** | 1. Sistema recebe o conjunto de estabelecimentos com suas distâncias calculadas (saída de UC04). 2. Sistema ordena o conjunto em ordem crescente de distância. 3. Sistema renderiza a lista com nome, categoria, endereço e distância (em metros) de cada item. |
| **Fluxos alternativos** | A1 (*extend* UC07b). Usuário escolhe ordenação alfabética: sistema reordena a lista por nome (A–Z) em vez de por distância. A2. Lista vazia: sistema exibe estado vazio informativo. |
| **Pré-condições** | Consulta espacial (UC04) executada com sucesso. |
| **Pós-condições** | Lista de resultados exibida na ordem escolhida pelo usuário. |

---

## UC07b — Ordenar Resultados Alfabeticamente

| Campo | Conteúdo |
|---|---|
| **Nome** | Ordenar Resultados Alfabeticamente |
| **Descrição** | Caso de uso de extensão que permite ao usuário reordenar a lista de resultados pelo nome do estabelecimento, em vez da distância ao hospital. |
| **Fluxo principal** | 1. Usuário aciona o controle "Ordenar por nome" na lista de resultados. 2. Sistema reordena a lista atual em ordem alfabética crescente (A–Z), preservando o conjunto de resultados já filtrado pela consulta espacial. 3. Sistema atualiza a exibição da lista. |
| **Fluxos alternativos** | A1. Usuário retorna à ordenação por distância: sistema reaplica a ordenação padrão de UC07. |
| **Pré-condições** | Lista de resultados de UC07 previamente exibida. |
| **Pós-condições** | Lista reexibida em ordem alfabética. |

---

## UC08 — Visualizar Resultados no Mapa

| Campo | Conteúdo |
|---|---|
| **Nome** | Visualizar Resultados no Mapa |
| **Descrição** | Exibe, sobre o mapa interativo, os marcadores dos estabelecimentos retornados pela consulta, o hospital de referência e a área de busca (raio), além de permitir camadas auxiliares de linha (via/rota) e polígono (bairro). |
| **Fluxo principal** | 1. Sistema recebe o conjunto de resultados ordenado (saída de UC07). 2. Sistema plota o marcador do hospital, o círculo do raio de busca e um marcador para cada estabelecimento resultante, com ícone diferenciado por categoria. 3. Usuário pode clicar em um marcador para ver detalhes (nome, endereço, distância) em um popup. 4. Usuário pode clicar em um item da lista para destacar/centralizar o marcador correspondente no mapa. |
| **Fluxos alternativos** | A1. Usuário ativa a camada de polígono do bairro: sistema desenha o polígono e indica se ele contém o hospital (operador **contém**). A2. Usuário ativa a camada de via/rota (linha): sistema desenha a linha e indica se ela intercepta a área de busca (operador **intercepta**). A3. Nenhum resultado a exibir: sistema mantém apenas hospital e raio plotados. |
| **Pré-condições** | Consulta espacial (UC04) e listagem (UC07) executadas. |
| **Pós-condições** | Mapa exibindo hospital, raio de busca e marcadores dos estabelecimentos encontrados, sincronizado com a lista de resultados. |
