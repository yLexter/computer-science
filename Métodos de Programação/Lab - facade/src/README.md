# Sistema de Informação Geral (SIG) - UEPB

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