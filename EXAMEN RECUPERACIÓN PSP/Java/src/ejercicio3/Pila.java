package ejercicio3;

import java.util.ArrayList;

public class Pila {
	private int dimension = 0;
	private ArrayList<Integer> pilaimpresion;

	public Pila(int dimension) {
		this.dimension = dimension;
		this.pilaimpresion = new ArrayList<Integer>();
	}

	public synchronized void insertar(int documento) {
		while (pilaimpresion.size() == dimension) {

			System.out.println("PILA DE IMPRESION LLENA");
			System.out.println(pilaimpresion.toString());
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Imprimir la pila
		

		// Si hay hueco en la pila
		pilaimpresion.add(documento);
		System.out.println("Nuevo documento en el tope de la pila:  " + documento);
		notifyAll();

	}

	public synchronized int imprimir() {
		int documento = 0;
		while(pilaimpresion.isEmpty()) {

		System.out.println("\t PILA DE IMPRESIÓN VACÍA");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		documento = pilaimpresion.removeLast();
		// Si hay documentos imprime el ultimo documento

		System.out.println("Imprimido el documento: " + documento);
		notifyAll();
		return documento;

	}//
}// pila