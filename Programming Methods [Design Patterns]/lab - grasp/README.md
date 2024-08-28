#Lab-3: Cohesion and Coupling

Refactoring the "Obfuscated" classes, the following problems were encountered:

## Cohesion

- The Geometric Figures class has methods that deal with different types of geometric figures, such as rectangles, squares and circles. This violates the principle of cohesion, as the class is handling more than one responsibility.

- The method a(int typeOfFigure) is responsible for calculating and displaying the area of ​​the figures, while the method p(int typeOfFigure) calculates and displays the perimeter. These methods should be in separate classes, each responsible for a specific type of geometric figure.

- The toStringDaFigure(inttipoDaFigure) method also violates the principle of cohesion, as it is responsible for returning a textual representation of the figure. This responsibility should be in a separate class.
Coupling

## Coupling

- The BrincandoComAs FigurasGeometricas class is strongly coupled to the FigurasGeometricas class, as it directly uses its static and constant methods. This makes code maintenance and reuse difficult, since any change to the FigurasGeometricas class can impact the BrincandoComAsFiguresGeometricas class.

- Coupling could be reduced by moving the logic for calculating and displaying geometric figures to separate classes, making the BrincandoComAs FigurasGeometricas class more generic and easier to maintain.

## Project Structure

The project is structured as follows:

- `entities`: Contains the classes of geometric figures.
- `tests`: All figure and singleton tests.
- `errors`: Custom exceptions.
- `Main`: Creation and results of the cohesion and coupling pattern.