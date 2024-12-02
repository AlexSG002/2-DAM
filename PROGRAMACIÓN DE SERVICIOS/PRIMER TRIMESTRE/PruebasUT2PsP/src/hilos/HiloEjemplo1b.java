package hilos;

public class HiloEjemplo1b implements Runnable{
	int numeroHilo=0;
	public HiloEjemplo1b(int numero) {
		numeroHilo=numero;
	}
	
	public void run() {
		System.out.println("Hola, soy el hilo "+numeroHilo);
	}
	
}
