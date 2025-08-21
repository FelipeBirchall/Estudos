# Verificador de Anagramas em Java

Este projeto contém um programa simples em Java que verifica se duas frases fornecidas pelo usuário são anagramas uma da outra. Um anagrama é uma palavra ou frase formada pela transposição das letras de outra palavra ou frase.

## Funcionalidades

- **Leitura de frases**: O programa lê duas frases inseridas pelo usuário.
- **Verificação de anagramas**: Compara as duas frases e determina se são anagramas.
- **Resultado claro**: Exibe "SIM" se as frases forem anagramas ou "NÃO" caso contrário.

## Como funciona

1. O programa utiliza a classe `Scanner` para ler as duas frases.
2. Verifica se as frases têm o mesmo comprimento. Se não tiverem, elas não podem ser anagramas.
3. Converte as frases em arrays de caracteres para facilitar a comparação.
4. Compara cada caractere das duas frases para determinar se todas as letras de uma frase estão presentes na outra.
5. Exibe "SIM" se as frases forem anagramas ou "NÃO" caso contrário.
