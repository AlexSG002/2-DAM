package cuentaActividad;

public class Main {

	public static void main(String[] args) {
		Cuenta c = new Cuenta(500,1000);
		Persona h1 = new Persona("María",c);
		h1.start();
		
	}
}
