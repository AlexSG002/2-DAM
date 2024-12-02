package cuentaActividad;

public class Cuenta {
	private int saldo;
	private int saldoMax;
	Cuenta(int s, int sMax) {
		saldo = s;
		saldoMax = sMax;
	}

	int getSaldo() {
		return saldo;
	}
	
	int getSaldoMax() {
		return saldoMax;
	}

	void restar(int cantidad) {
		saldo = saldo - cantidad;
	}
	
	void sumar(int cantidad) {
		saldo = saldo + cantidad;
	}

	synchronized void retirarDinero(int cant, String nom) {
		if (getSaldo() >= cant) {
			System.out.println(nom + " :Se va a retirar saldo (El saldo actual es: " + getSaldo() + ")");

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.getStackTrace();
			}
			restar(cant);
			System.out.println("\t" + nom + " retira => " + cant + " ACTUAL(" + getSaldo() + ")");

		} else
			

		if (getSaldo() <= 0) {
			System.out.println("SALDO NEGATIVO => " + getSaldo());
		}
	}
	
	synchronized void ingresarDinero(int cant, String nom) {
		if (getSaldo()+cant < saldoMax) {
			System.out.println(nom + " :Se va a ingresar saldo (El saldo actual es: " + getSaldo() + ")");

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.getStackTrace();
			}
			sumar(cant);
			System.out.println("\t" + nom + " ingresa => " + cant + " ACTUAL(" + getSaldo() + ")");

		} else
			System.out.println(nom + " no se puede ingresar dinero porque pasaría del saldo Máximo:"+ saldoMax +" ACTUAL:(" + getSaldo() + ")");

		
	}
	
}
