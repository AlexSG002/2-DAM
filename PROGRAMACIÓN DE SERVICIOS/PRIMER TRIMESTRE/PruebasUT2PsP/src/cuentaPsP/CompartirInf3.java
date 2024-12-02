package cuentaPsP;

public class CompartirInf3 {

	public static void main(String[] args) {
		Cuenta c = new Cuenta(40);
		SacarDinero h1 = new SacarDinero("María",c);
		SacarDinero h2 = new SacarDinero("Iván", c);
		h1.start();
		h2.start();
	}
}
