package prueba;

public class Saludo {

	private static int cantidadDeSaludos = 0;
	private int contadorNoEstatico = 0;
	private int valorIncremental = 1;

	public static Saludo instance(int numero) {
		if (numero % 2 == 0) {
			return new Saludo(numero);
		} else {
			return new Saludo(0);
		}
	}

	public Saludo(int contadorNoEstatico) {
		this.contadorNoEstatico=contadorNoEstatico;
	}

	public static int getCantidadDeSaludos() {
		return cantidadDeSaludos;
	}

	public int getContadorNoEstatico() {
		return contadorNoEstatico;
	}

}
