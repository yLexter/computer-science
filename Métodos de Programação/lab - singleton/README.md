# Lab-6: Singleton

## Descrição
Implementação de uma "Fábrica" de figuras geométricas onde é possível criar um único círculo, três instâncias de triângulos (um isósceles, um equilátero e um retângulo) e inúmeros quadrados.

## Funcionalidades
- Criação de um único círculo
- Criação de três instâncias de triângulos (isósceles, equilátero e retângulo)
- Criação de inúmeros quadrados

## Como utilizar
1. Instancie a fábrica de figuras geométricas (`FiguraFactory`).
2. Utilize os métodos da fábrica para criar as figuras desejadas.

## Exemplo de uso
```java
    FiguraFactory factory = new FiguraFactory();

    Figura circulo = factory.criarCirculo(raio);

    Figura trianguloIsoceles = factory.criarTrianguloIsosceles(lado1, lado2, base);
    Figura trianguloEquilatero = factory.criarTrianguloEquilatero(lado);
    Figura trianguloRetangulo = factory.criarTrianguloRetangulo(cateto1, cateto2);

    Figura quadrado1 = factory.criarQuadrado(lado1);
    Figura quadrado2 = factory.criarQuadrado(lado2);
    Figura quadrado3 = factory.criarQuadrado(lado3);
```

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- `entities`: Contém as classes das figuras geométricas.
- `enums`: Enum para o tipo de triangulo.
- `testes`: Todos os testes das figuras e singleton.
- `erros`: Exceções customizadas.
- `Main`: Criação e resultados do singleton.