package ejercicioPropuesto;

public class Numeros {

	private int numero;
	private long cuadrado;
	private long cubo;
	
	public Numeros(int numero, long cuadrado, long cubo) {
		this.numero = numero;
		this.cuadrado = cuadrado;
		this.cubo = cubo;
	}
	
	public Numeros() {
	
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public long getCuadrado() {
		return cuadrado;
	}

	public void setCuadrado(long cuadrado) {
		this.cuadrado = cuadrado;
	}

	public long getCubo() {
		return cubo;
	}

	public void setCubo(long cubo) {
		this.cubo = cubo;
	}
	
	
	
}
