# Verificador de Palíndromos Recursivo em Java

Este projeto implementa um verificador de palíndromos em Java que utiliza recursão para determinar se uma palavra ou frase é um palíndromo. Um palíndromo é uma palavra, frase ou sequência de caracteres que é lida da mesma forma de trás para frente, ignorando maiúsculas e minúsculas.

## Como Funciona

O programa solicita ao usuário que insira palavras ou frases. Para cada entrada, ele verifica se a palavra é um palíndromo e imprime "SIM" se for um palíndromo ou "NAO" caso contrário. O programa continua a solicitar entradas até que o usuário digite "FIM".

### Função Recursiva

- **EhPalindromo**: Esta função verifica se uma palavra é um palíndromo de forma recursiva. Ela compara os caracteres da palavra a partir das extremidades em direção ao centro. Se todos os caracteres corresponderem, a função retorna `true`; caso contrário, retorna `false`.
