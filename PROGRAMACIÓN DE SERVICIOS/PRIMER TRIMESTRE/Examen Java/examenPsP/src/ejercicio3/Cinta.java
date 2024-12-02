package ejercicio3;

import java.util.ArrayList;

public class Cinta {
	private final int dimension;
	private final ArrayList<Double> cintatransportadora;

	public Cinta(int dimension) {
		this.dimension = dimension;
		this.cintatransportadora = new ArrayList<Double>(dimension);
	}

	public synchronized void insertar(double valor) {
		while (cintatransportadora.size() == dimension) {
			try {
				System.out.println("CINTA LLENA.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}

		cintatransportadora.add(valor);
		System.out.println("NUEVA PIEZA EN LA CINTA: " + valor);

		notifyAll();
	}

	public synchronized double extraer() {
		while (cintatransportadora.isEmpty()) {
			try {
				System.out.println("\tCINTA TRANSPORTADORA VACIA");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		double valor = cintatransportadora.remove(0);
		System.out.println("PIEZA EXTRAIDA DE LA CINTA: " + valor);

		notifyAll();
		return valor;
	}
}
