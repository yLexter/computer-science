# Projeto - WP

- Transferir a seguinte descrição verbal para um Modelo de Entidade-Relacionamento (MER):

1. Um projeto (Proj-#) consiste em vários pacotes de trabalho (WP-#).
2. Os pacotes de trabalho são estruturados em uma estrutura hierárquica de decomposição do trabalho, onde um pacote de trabalho pode ser subdividido em vários outros pacotes de trabalho, enquanto cada pacote de trabalho é subordinado a exatamente um pacote de trabalho.
3. Um ou mais funcionários (SI-ID) são atribuídos a cada pacote de trabalho, onde os funcionários também podem ser empregados em vários pacotes de trabalho. Os funcionários têm certas qualificações (Qual-ID) e são atribuídos de forma única a departamentos (Dept-ID).
4. Um pacote de trabalho pode exigir uma ou mais qualificações.
5. Cada projeto tem um funcionário como pessoa responsável; no entanto, um funcionário também pode ter responsabilidade por vários projetos.

--- 

![Projeto WP - DER](https://i.imgur.com/kUtpblQ.png)
