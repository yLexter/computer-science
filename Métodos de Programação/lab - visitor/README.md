# Laboratório: Visitor

Este projeto implementa um exemplo do padrão de projeto Visitor, utilizando as figuras geométricas: Círculo, Triângulo, Retângulo e Trapézio.

## Funcionalidades

As operações implementadas para cada figura geométrica são:

- **Desenhar:** Mostra uma representação visual da figura.
- **Calcular Área:** Calcula a área da figura.
- **Calcular Perímetro:** Calcula o perímetro da figura.
- **Maximizar Figura:** Duplica o raio (no caso do círculo), lado (triângulo), base e altura (retângulo e trapézio) da figura.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- `entities`: Contém as classes das figuras geométricas.
- `visitors`: Implementações dos visitors para cada operação.
- `testes`: Todos os testes das figura e visitors
- `interfaces`: Interfaces de Visitor e AceitaVisitor
- `erros`: Exceções customizadas
- `Main`: Utiliza o padrão Visitor para operar sobre as figuras geométricas.




