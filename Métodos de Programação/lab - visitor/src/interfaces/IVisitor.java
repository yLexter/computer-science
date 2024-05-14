package interfaces;

import entities.Circulo;
import entities.Retangulo;
import entities.Trapezio;
import entities.Triangle;

public interface IVisitor<T> {
    T visitaCirculo(Circulo circulo);
    T visitaTriangulo(Triangle triangle);
    T visitaRetangulo(Retangulo retangulo);
    T visitaTrapezio(Trapezio trapezio);
}
