package cuentaActividad;

public class Persona extends Thread {

	private Cuenta c;
	String nom;
	int random;

	public Persona(String n, Cuenta c) {
		super(n);
		this.c = c;
	}

	public void run() {
		for (int i = 0; i <= 2; i++) {
			try {
				
				random = (int) (Math.random() * 500 + 1);
				c.retirarDinero(random, getName());
				Thread.sleep(500);

				
				if (c.getSaldo() <= 0 || c.getSaldo() > 1000) {
					System.out.println(getName() + ": El saldo se fue de rango después de retirar. Saldo actual: " + c.getSaldo());
					break;
				}

				
				random = (int) (Math.random() * 500 + 1);
				c.ingresarDinero(random, getName());
				Thread.sleep(500);

				
				if (c.getSaldo() <= 0 || c.getSaldo() > 1000) {
					System.out.println(getName() + ": El saldo se fue de rango después de ingresar. Saldo actual: " + c.getSaldo());
					break;
				}

			} catch (InterruptedException e) {
				System.out.println(getName() + " ha sido interrumpido.");
				break;
			}
		}
	}
}
