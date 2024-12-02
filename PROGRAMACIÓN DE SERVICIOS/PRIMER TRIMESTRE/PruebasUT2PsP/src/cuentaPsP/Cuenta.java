package cuentaPsP;

public class Cuenta {
	private int saldo;

	Cuenta(int s) {
		saldo = s;
	}

	int getSaldo() {
		return saldo;
	}

	void restar(int cantidad) {
		saldo = saldo - cantidad;
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
			System.out.println(nom + " no se puede retirar dinero porque NO HAY SALDO (" + getSaldo() + ")");

		if (getSaldo() <= 0) {
			System.out.println("SALDO NEGATIVO => " + getSaldo());
		}
	}
}
