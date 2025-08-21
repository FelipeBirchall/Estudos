# Sistema de Cadastro de Pessoas em Java

Este projeto é um sistema simples de cadastro de pessoas desenvolvido em Java. Ele permite que o usuário cadastre múltiplas pessoas, armazenando informações como nome, idade e gênero. O programa utiliza uma lista (`ArrayList`) para gerenciar os dados e exibe todas as informações cadastradas ao final.

O código foi criado com foco na simplicidade e clareza, mas está aberto a futuras atualizações e melhorias conforme novos comandos e conceitos em Java forem aprendidos.

---

## Funcionalidades

- **Cadastro de Pessoas:** O usuário pode cadastrar quantas pessoas desejar, fornecendo nome, idade e gênero.
- **Armazenamento de Dados:** Os dados são armazenados em uma lista (`ArrayList<Pessoa>`).
- **Exibição de Cadastros:** Após o cadastro, o programa exibe todos os dados das pessoas cadastradas.

---

## Como Usar

1. Execute o programa.
2. Informe o número de cadastros que deseja realizar.
3. Insira os dados de cada pessoa (nome, idade e gênero).
4. Ao final, o programa exibirá todos os cadastros realizados.

---

## Estrutura do Código

- **Classe `Pessoa`:**
  - Representa os dados de uma pessoa (nome, idade e gênero).
  - Possui um método `exibirDados()` para mostrar as informações.

- **Classe `Main`:**
  - Contém o método `main`, que gerencia a interação com o usuário.
  - Utiliza um loop para coletar os dados de acordo com o número de cadastros informado.
  - Armazena os objetos `Pessoa` em uma lista (`ArrayList<Pessoa>`).

---

## Expansibilidade

Este projeto foi projetado para ser expandido e aprimorado. Aqui estão algumas ideias para futuras atualizações:

- **Validações de Entrada:**
  - Impedir idades negativas ou nomes vazios.
- **Persistência de Dados:**
  - Salvar os cadastros em um arquivo (`.txt`, `.csv`) ou banco de dados.
- **Funcionalidades Adicionais:**
  - Editar ou excluir cadastros existentes.
