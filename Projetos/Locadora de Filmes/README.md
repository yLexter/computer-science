# Projeto Locadora de Filmes

Este projeto de locadora de filmes em C para a disciplina de Linguagem de Progamação I (Professor Kláudio) foi desenvolvido com o objetivo de explorar os conhecimentos em C, especialmente em estruturas de dados (linked list) e alocação dinâmica de memória e Manipulação de Arquivos.

## Funcionalidades

O sistema possui as seguintes funcionalidades:

1. **Ver Filmes:** Permite visualizar a lista de filmes disponíveis na locadora.
2. **Alugar Filme:** Permite alugar um filme da locadora.
3. **Cadastrar Cliente:** Permite cadastrar um novo cliente na locadora.
4. **Mostrar Clientes:** Exibe a lista de clientes cadastrados na locadora.
5. **Deletar Cliente:** Permite excluir um cliente da lista de clientes cadastrados.
6. **Mostrar Filmes Alugados:** Exibe a lista de filmes atualmente alugados por clientes.
7. **Deletar Filme:** Permite excluir um filme da lista de filmes disponíveis na locadora.
8. **Cadastrar Filme:** Permite cadastrar um novo filme na locadora.
9. **Devolver Filme:** Permite que um cliente devolva um filme alugado.
10. **Mostrar Filmes Populares:** Exibe os filmes mais populares (mais alugados).
11. **Alterar quantidade dos filmes:** Permite alterar a quantidade de cópias disponíveis de um filme.
12. **Sair:** Encerra o programa.

## Funcionalidades de Ordenação (Cliente)

O sistema possui as seguintes opções de ordenação para os Clientes:

1. **Crescente:** Ordena os clientes em ordem crescente.
2. **Decrescente:** Ordena os clientes em ordem decrescente.
3. **Por Idade:** Ordena os clientes por idade.
4. **Nenhuma:** Não aplica nenhuma ordenação.

## Funcionalidades de Ordenação de (Filmes)

1. `ordenarFilmesPorTituloCrescente`: Ordena os filmes por título em ordem crescente.
2. `ordenarFilmesPorTituloDecrescente`: Ordena os filmes por título em ordem decrescente.
3. `ordenarFilmesPorDuracao`: Ordena os filmes por duração.
4. `ordenarFilmesPorGenero`: Ordena os filmes por gênero.
5. `ordenarFilmesPorNota`: Ordena os filmes por nota.
6. `ordenarFilmesPorClassificacao`: Ordena os filmes por classificação.
7. `ordenarFilmesPorQuantidade`: Ordena os filmes por quantidade.

## Banco de Dados

O sistema utiliza arquivos de texto (`.txt`) como banco de dados para armazenar todas as informações sobre filmes e clientes.