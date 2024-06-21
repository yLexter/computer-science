# Exercicio 1 - Stored procedures e functions SQL 

- Considerando o modelo de dados do exercício do beecrowd 2991

1. Crie uma stored procedure para inserir um empregado no sistema. A stored procedure deve receber as informações da tabela empregado, um conjunto (pode ser array de inteiros, ou outra estrutura de dados que deseje usar) de códigos vencimento, um conjunto (pode ser array de inteiros, ou outra estrutura de dados que deseje usar) de códigos de desconto. A stored procedure deve inserir o empregado, todos os seus respectivos vencimentos e descontos. 

        Stored Procedure ->
            insere_empregado(matr integer, nome character varying(50), endereco character varying(50), data_lotacao timestamp, lotacao_cod_dep integer, gerencia_cod_dep integer, lotacao_div integer, gerencia_div integer, aumento_tx numeric, lista_vencimentos[] integer, lista_descontos[] integer)

     O único atributo realmente obrigatório seria a matr (matrícula)

2. Crie uma função sql para que seja listado a matrícula, o nome, o nome do departamento , o nome da divisão dos empregados que possuem salário LÍQUIDO entre uma determinada faixa de valores. A função deve receber como parâmetro, a faixa de valores, e o código do departamento que se quer selecionar os empregados.

        Function ->
            lista_empregados_salarios_departamento( salario_inicial numeric, salario_final numeric, cod_depto integer): --> table (matricula, nome, nome da divisão dos empregados, salário líquido)
