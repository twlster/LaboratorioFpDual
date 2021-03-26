package herencia;

public class EjemploFinal {

	private final String ejemplo;
	
	public EjemploFinal(String ejemplo) {
		this.ejemplo=ejemplo;
	}
	public String getEjemplo() {
		return ejemplo;
	}
	
	public void setEjemplo(String ejemplo) {
		this.ejemplo = String.valueOf(ejemplo);
	}
	
}
