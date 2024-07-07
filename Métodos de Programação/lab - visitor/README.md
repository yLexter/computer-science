#Lab-7: Visitor

This project implements an example of the Visitor design pattern, using the geometric figures: Circle, Triangle, Rectangle and Trapezium.

## Functionalities

The operations implemented for each geometric figure are:

- **Draw:** Shows a visual representation of the figure.
- **Calculate Area:** Calculates the area of ​​the figure.
- **Calculate Perimeter:** Calculates the perimeter of the figure.
- **Maximize Figure:** Doubles the radius (in the case of a circle), side (triangle), base and height (rectangle and trapezoid) of the figure.

## Project Structure

The project is structured as follows:

- `entities`: Contains the classes of geometric figures.
- `visitors`: Implementations of visitors for each operation.
- `tests`: All figure and visitor tests
- `interfaces`: Visitor and AceitaVisitor interfaces
- `errors`: Custom exceptions
- `Main`: Uses the Visitor pattern to operate on geometric figures.