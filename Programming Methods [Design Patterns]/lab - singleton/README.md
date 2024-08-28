#Lab-6: Singleton

## Description
Implementation of a "Factory" of geometric figures where it is possible to create a single circle, three instances of triangles (one isosceles, one equilateral and one rectangle) and countless squares.

## Functionalities
- Creation of a single circle
- Creation of three instances of triangles (isosceles, equilateral and rectangle)
- Creation of countless squares

## How to use
1. Instantiate the geometric figure factory (`FigureFactory`).
2. Use factory methods to create the desired figures.

## Example of use
```java
 FiguraFactory factory = new FiguraFactory();

 Circle figure = factory.createCircle(radius);

 Isoceles triangle figure = factory.createIsoscelesTriangle(side1, side2, base);
 FigureEquilateral triangle = factory.createEquilateralTriangle(side);
 Rectangular triangle figure = factory.criarRectangularTriangle(cathet1, cathet2);

 Figure square1 = factory.createSquare(side1);
 Figure square2 = factory.createSquare(side2);
 Figure square3 = factory.createSquare(side3);
```

## Project Structure

The project is structured as follows:

- `entities`: Contains the classes of geometric figures.
- `enums`: Enum for the triangle type.
- `tests`: All figure and singleton tests.
- `errors`: Custom exceptions.
- `Main`: Singleton creation and results.