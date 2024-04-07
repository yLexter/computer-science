package lab0.tests;

import lab0.triangle.Triangle;
import lab0.triangle.TriangleException;
import lab0.triangle.TriangleKind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {
	private Triangle triangleEquilateral;
	private Triangle triangleIsosceles;
	private Triangle triangleScalene;

	@BeforeEach
	public void setUp () throws TriangleException {
		this.triangleEquilateral = new Triangle(5, 5, 5);
		this.triangleIsosceles = new Triangle(5,5,4);
		this.triangleScalene = new Triangle(4,5,6);
	}
	
	public void testSizes(double[][] sizes, String message) {

		for (double[] size : sizes) {

			 assertThrows(TriangleException.class, () -> {
				 new Triangle(size[0], size[1], size[2]);
			}, message);

		}

	}

	@Test
	public void invalidTriangle() {

		double[][] zeroSizes = {
				{0,1,1},
				{1,0,1},
				{1,1,0},
				{0,0,0},
		};

		double[][] negativeSizes = {
			{4, -3, 5},
			{-3, 4, 5},
			{4, 5, -3},
		};

		double [][] impossibleSizes = {
			{2, 3, 6},
			{2, 6, 3},
			{6, 2, 3}
		};

		testSizes(zeroSizes, "Tamanho do Triângulo não pode ser zero");
		testSizes(negativeSizes, "Tamanho do Triângulo não pode menor que zero");
		testSizes(impossibleSizes, "Tringaulo com medidas impossíveis");
	}	

	@Test
	public void getKindTest() {
		assertEquals(TriangleKind.EQUILATERAL, triangleEquilateral.getKind());
		assertEquals(TriangleKind.ISOSCELES, triangleIsosceles.getKind());
		assertEquals(TriangleKind.SCALENE, triangleScalene.getKind());
	}

	@Test
	public void getNumberOfUniqueSidesTest() {
		assertEquals(triangleEquilateral.getNumberOfUniqueSides(), 1);
		assertEquals(triangleIsosceles.getNumberOfUniqueSides(), 2);
		assertEquals(triangleScalene.getNumberOfUniqueSides(), 3);
	}

}
