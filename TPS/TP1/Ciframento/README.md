# Ciframento de César em Java

## Descrição
Este programa implementa o **Ciframento de César**, uma técnica de criptografia clássica que desloca cada letra de uma palavra ou frase por um número fixo de posições no alfabeto. Neste caso, o deslocamento é de **3 posições**. O programa lê palavras ou frases digitadas pelo usuário e imprime a versão cifrada.

## Funcionamento
1. O programa solicita ao usuário que digite uma palavra ou frase.
2. Cada letra da entrada é deslocada em 3 posições no alfabeto:
   - Letras minúsculas (`a` a `z`) e maiúsculas (`A` a `Z`) são cifradas.
   - Caracteres que não são letras (espaços, pontuações, etc.) são mantidos inalterados.
3. Se o deslocamento ultrapassar o limite do alfabeto (por exemplo, `z` + 3), o programa volta para o início do alfabeto.
4. O resultado cifrado é exibido na tela.
5. O loop continua até que o usuário digite **"FIM"**.