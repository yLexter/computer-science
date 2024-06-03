# Lab7: Visitor

Este é uma atividade desenvolvido em dupla com [Lisa Costa](https://github.com/lisacsiqueira). A atividade consistiu na implementação do Sistema de Informação Geral (SIG) utilizado na UEPB, que compreende vários subsistemas responsáveis por atividades relacionadas a setores específicos da universidade.

## Descrição da Atividade

Implementamos o SIG utilizando o padrão de projeto Facade, que proporciona uma interface unificada para um conjunto de interfaces em um subsistema. Isso simplifica o uso e a comunicação com os diferentes subsistemas do SIG, como:

- **Administrativo:** Reuniões agendadas com a diretoria, entrevistas.
- **Financeiro:** Balanço de contas, folha de pagamento.
- **Professores:** Alocação por disciplina, tempo de casa.
- **Alunos:** Histórico e RDM.
- **Almoxarifado:** Estoque, pedido de compra.
- **Infraestrutura:** Alocação de salas.

O padrão Facade nos permitiu encapsular a complexidade de interações entre os subsistemas do SIG, fornecendo uma interface simplificada para uso pelos usuários do sistema.

## Funcionalidades Implementadas

- Interface unificada para acesso aos subsistemas do SIG.
- Métodos para obter informações administrativas, financeiras, relacionadas a professores, alunos, almoxarifado e infraestrutura.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

## Admin e School
- `entities`: Contém as classes das figuras geométricas.
- `facades`: Implementação do facade para aclopar as funcionalidades.
- `servicos`: Serviços de uma determinada classe.
- `testes`: Todos os testes das figuras e facade.
- `erros`: Exceções customizadas.
- `Main`: Criação e resultados do facade.