package canibales;

public class Olla {

	private int tamaño;
	private int[] olla;
	private int echar = 0, sacar = 0;
	private int cont = 0;

	public Olla(int tamaño) {
		this.tamaño = tamaño;
		olla = new int[tamaño];
	}

	public synchronized void EcharAlaOlla(int nummisionero) {
		// Si la olla está llena
		while (cont == tamaño) {
			System.out.println("No se puede echar a la olla: OLLA  LLENA ");

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Si se puede cocinar habrá que echar otro
		}
		olla[echar] = nummisionero;
		echar = (echar + 1) % tamaño;
		cont++;
		System.out.println("		Cocinando al misionero:  " + nummisionero);
		notifyAll();
	}

	public synchronized int SacardelaOlla(int numerocanibal) {
		while (cont == 0) {
			// Si la olla está vacía no se puede sacar
			System.out.println("No hay nada más que sacar: OLLA VACÍA ");

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int misionero = olla[sacar];
		sacar = (sacar + 1) % tamaño;
		cont--;
		// Si hay misioneros cocinados se podrán sacar y comer
		System.out.println("El canibal " + numerocanibal + " está comiendo al misionero " + misionero);
		notifyAll();

		return misionero;

	}//

}
