# Lab3: Coesão e Acoplamento

Refatoração das classes "Ofuscadas", forão encotrada os seguintes problema:

## Coesão

- A classe FigurasGeometricas possui métodos que lidam com diferentes tipos de figuras geométricas, como retângulos, quadrados e círculos. Isso viola o princípio da coesão, pois a classe está lidando com mais de uma responsabilidade.

- O método a(int tipoDaFigura) é responsável por calcular e exibir a área das figuras, enquanto o método p(int tipoDaFigura) calcula e exibe o perímetro. Esses métodos deveriam estar em classes separadas, cada uma responsável por um tipo específico de figura geométrica.

- O método toStringDaFigura(int tipoDaFigura) também viola o princípio da coesão, pois está responsável por retornar uma representação textual da figura. Esta responsabilidade deveria estar em uma classe separada.
Acoplamento

## Aclopamento

- A classe BrincandoComAsFigurasGeometricas está fortemente acoplada à classe FigurasGeometricas, pois utiliza diretamente seus métodos estáticos e constantes. Isso dificulta a manutenção e a reutilização do código, uma vez que qualquer alteração na classe FigurasGeometricas pode impactar a classe BrincandoComAsFigurasGeometricas.

- O acoplamento poderia ser reduzido movendo a lógica de cálculo e exibição das figuras geométricas para classes separadas, tornando a classe BrincandoComAsFigurasGeometricas mais genérica e fácil de manter.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- `entities`: Contém as classes das figuras geométricas.
- `testes`: Todos os testes das figuras e singleton.
- `erros`: Exceções customizadas.
- `Main`: Criação e resultados do padrão de coesãoe aclopamento.