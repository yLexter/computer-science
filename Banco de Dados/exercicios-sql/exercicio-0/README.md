# Projeto DDL - 1

Gere código SQL para criar o esquema que representa o modelo conceitual apresentado abaixo:

[![Exercicios-2-1.png](https://i.postimg.cc/WzXM8bPk/Exercicios-2-1.png)](https://postimg.cc/N229ftFs)

# Projeto DDL - 2

1. Alterar a tabela zona eleitoral para que ela não tenha a quantidade de eleitores;

2. Criar um índice “não-clustered” na tabela Candidato, com os atributos cod_cargo e cod_partido;
Gere código para inserir dados em todas as tabelas conforme o seguinte script:

3. Gere código para inserir dados em todas as tabelas conforme o seguinte script:

```sql
Zona (1, 'Zona norte'), 
Zona (2, 'Zona sul'), 
Zona (3, 'Zona leste'),
Zona (4, 'Zona oeste')

Seção (1, 'Seção NA', 100, 1), 
Seção (2, 'Seção NB', 250, 1), 
Seção (3, 'Seção SA', 1000, 2),
Seção (6, 'Seção SB', 3000, 2), 
Seção (4, 'Seção LA', 5000, 3), 
Seção (7, 'Seção LB', 20000, 3), 
Seção (5, 'Seção OA', 10000, 4), 
Seção (8, 'Seção OB', 10000, 4)
```

4. Alterar todos os atributos nome de todas as tabelas que possuam esse atributo, faça-os que sejam obrigatórios e não nulos.
