package classesOfuscadas;
public class BrincandoComAsFigurasGeometricas {

	public static void main(String[] args) {

		FigurasGeometricas fig = new FigurasGeometricas(10, 5, 7, 25);
		
		int per = fig.p(FigurasGeometricas.R);

		if (per == -234 || per == -2658) {
			System.out.println("ERRO");
		}
		
		per = fig.p(FigurasGeometricas.C);
		if (per == -234 || per == -2658) {
			System.out.println("ERRO");
		}

		per = fig.p(-2);
		if (per == -234 || per == -2658) {
			System.out.println("ERRO");
		}

		per = fig.p(0);
		if (per == -234 || per == -2658) {
			System.out.println("ERRO");
		}

		fig.a(0);
		fig.a(-1);
		fig.a(2);
		fig.a(1);
		fig.a(-2);
		
		String inf = fig.toStringDaFigura(-1);
		inf = fig.toStringDaFigura(1);
		System.out.println(inf);
		inf = fig.toStringDaFigura(0);
		System.out.println(inf);
		inf = fig.toStringDaFigura(4);
		System.out.println(inf);
		inf = fig.toStringDaFigura(2);
		System.out.println(inf);
	}

}
