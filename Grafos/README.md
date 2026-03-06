# Grafo com Lista Flexível (Java)

## Descrição

Este código implementa um **grafo direcionado** utilizando **listas encadeadas (listas flexíveis)** para armazenar as conexões entre os vértices.

Cada vértice possui duas listas:

* **Sucessores** → vértices para os quais ele aponta.
* **Predecessores** → vértices que apontam para ele.

## Estrutura

### Classe `Celula`

Representa um nó da lista encadeada.

* `elemento` → valor armazenado (vértice conectado)
* `prox` → referência para o próximo nó

### Classe `Lista`

Implementa uma lista encadeada simples.

Principais métodos:

* `inserirFim(int x)` → insere um elemento no final da lista
* `mostrar()` → percorre e imprime os elementos da lista

## Armazenamento do Grafo

O grafo utiliza dois vetores de listas:

* `sucessores[]`
* `predecessores[]`

Cada posição do vetor representa um **vértice**.

Quando existe uma aresta `A → B`:

1. `B` é inserido na lista de **sucessores de A**
2. `A` é inserido na lista de **predecessores de B**
