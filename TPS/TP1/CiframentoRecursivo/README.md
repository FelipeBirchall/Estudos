# Cifra de César Recursiva em Java

Este repositório contém um código em Java que implementa a **Cifra de César**, um método de criptografia clássico que desloca cada letra do alfabeto por um número fixo de posições. O código utiliza uma abordagem **recursiva** para cifrar uma palavra ou frase fornecida pelo usuário.

## Funcionalidades

- **Entrada de texto:** O usuário pode digitar uma palavra ou frase.
- **Cifra de César:** O texto é cifrado com um deslocamento de 3 letras no alfabeto.
- **Recursão:** A cifragem é realizada de forma recursiva, sem o uso de loops.
- **Suporte a maiúsculas e minúsculas:** O código preserva a caixa das letras e ignora caracteres que não são letras.

## Como funciona

O programa lê a entrada do usuário, aplica a cifra de César e exibe o texto cifrado como saída. A cifra de César funciona deslocando cada letra do alfabeto por 3 posições. Por exemplo:

- Letra 'A' -> 'D'
- Letra 'B' -> 'E'
- Letra 'Z' -> 'C' (com wraparound)

O código trata tanto letras maiúsculas quanto minúsculas, mantendo outros caracteres inalterados.
