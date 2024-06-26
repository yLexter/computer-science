# Acompanhamento De Atividade Fisica E Nutricao Para O Bem Estar Dos Pacientes Nutrição

## Dupla: **Lisa Costa** ([GitHub](https://github.com/lisacsiqueira))

## Descrição

- As suas consultas consistem em realizar pesagem, medir circunferência abdominal além de outras medidas (coxas, bíceps, busto, quadril), idade, tipo de atividade física realizada pelo paciente, índice de massa corporal, gordura visceral e gordura total). Após a primeira consulta, é necessário que o paciente faça um checkup, apresentando resultados de alguns exames (hemograma, colesterol, triglicerídeos, glicemia de jejum, vitamina D, TSH e T4 livre).

- A partir dessas informações, a equipe de nutricionista traça um plano alimentar com a quantidade de refeições diárias, o horário de cada uma, e o que deve ser consumido em calorias diárias. Esse plano deve ser impresso e armazenado para futuras consultas a ele.

- Ao final de um mês, o paciente retorna e são atualizadas as medidas inicialmente coletadas (tudo deve ser medido novamente), e o plano alimentar é ajustado a partir delas. É necessário manter um histórico dessas informações, por paciente. As necessidades ou desejos do paciente (ganhar massa muscular ou perder peso) são anotadas também. As consultas são cobradas por mês, com direito a retorno a cada 30 dias para reavaliação. Os pacientes podem realizar consultas particulares ou por algum plano de saúde.

- É necessário manter um registro dos planos de saúde credenciados. Além disso, existe a possibilidade de o cliente aderir a pacotes, que dão descontos nas mensalidades e fidelizam. Assim, deve ser possível para o sistema cadastrar esses pacotes (custo e período de validade).

Os pagamentos podem ser realizados no cartão ou à vista. Flávia quer também a opção de relatórios no sistema.

Opções de relatórios que interessam são:

1. Quantidade de pacientes por plano de saúde e que pagam consultas, com informações a respeito dos valores que são repassados por plano e total recebido por consultas

2. Relatórios de evolução por paciente a cada consulta (por exemplo: quantos quilos emagreceu, quanto de gordura visceral perdeu, percentual de gordura total, imc), gráficos são desejados para cada uma desses relatórios individuais.

### Atividades físicas

- Sistema para gerenciamento integrado com a academia e a equipe de nutricionista. Ela possui um sistema de fichas, que devem ser levadas pelos pacientes sempre que vão treinar, para acompanhamento dos seus treinos. Acontece que as fichas rasgam ou se perdem e os clientes não estão satisfeitos com isso. Por isso, é preciso que o sistema auxilie os pacientes a acompanharem seus treinos via celular, ou um sistema desktop que dê a opção do cliente imprimir seu treino antes de realizá-lo.

- Antes de iniciar qualquer programa de atividade física, o paciente deve preencher uma ficha de anamnese complementar a que foi preenchida para o módulo nutricional, onde são anotados o seu nome, idade, objetivo da realização da atividade física, problemas de saúde que o paciente já possui, se está em tratamento médico ou usa algum medicamento de uso contínuo e se é ou foi fumante. O sistema precisa manter dados coletados das medidas dos clientes como peso e medidas em centímetros de: tórax, cintura, abdômen superior, quadril, braço direito, antebraço direito, braço esquerdo, antebraço esquerdo, coxa direita superior, coxa direita média,  coxa esquerda superior, coxa esquerda média, panturrilha esquerda, panturrilha direita e data que foram tiradas as medidas.

- Dentro de um período definido pelos educadores físicos, os clientes devem refazer sua avaliação física e os dados coletados devem ser cadastrados no sistema, e comparados com os dados anteriores para que o cliente saiba onde ele está evoluindo e onde precisa melhorar. Além disso, os exercícios realizados devem ser registrados no sistema a cada avaliação.

- Os exercícios devem ser cadastrados por dia da semana que são realizados, quais equipamentos são usados, quais grupos musculares trabalham, quantidade de séries e repetições por série (por exemplo, um cliente na segunda-feira realiza exercícios para perna, então ele usa faz agachamento guiado, 4 séries de 15 repetições cada, panturrilha sentado, 4 séries de 10 repetições cada, e assim por diante). Tudo isso deve ser registrado no sistema.

- O sistema deve ainda permitir que o cliente registre seu perfil com foto, com contrato atual, data de início e fim do contrato e possíveis promoções que o cliente possa aderir.

---

## Questões

### 1° Parte - Modelo Entidade & Relacionamento

- Preparar DER, Projeto relacional e Dicionários de dados

### 2° Parte - Create

- Escreva Scripts de criação de tabelas

### 3 Parte - Insert

- Escreva scripts de inserção de dados

### 4° Questão - Query

- Liste os candidatos pelo nome e CPF que obtiveram votos na cidade de "Campina Grande" e que gastaram mais de R$ 5.000,00 na campanha;
- Liste os candidatos pelo nome e CPF e código do partido, total declarado, total recebido em doação e  total gasto em campanha que possuíram mais de 500 votos na eleição de 2018 na cidade de Campina Grande;
- Liste o nome de todas as cidades e o respectivo total de votos no pleito de 2018 para o candidato"João". 
- Selecione o total de doação partidária recebido pelos candidatos do partido ‘CDU’.
- Apresente uma relação dos 3 partidos que receberam maior número de verbas partidárias 
- Relacione os 5 partidos mais bem votados na eleição de 2018 na Paraíba (considere apenas candidatos cujo registros esteja apto).
- Relacione os 10 candidatos mais ricos que foram eleitos na Paraíba em 2018 (considere apenas candidatos cujo registros esteja apto)
- Crie uma função que retorne uma lista de todas as cidades e seus respectivos quantitativos de votos nos candidatos do partido ‘CDU’. Ordene pela quantidade de votos válidos.


