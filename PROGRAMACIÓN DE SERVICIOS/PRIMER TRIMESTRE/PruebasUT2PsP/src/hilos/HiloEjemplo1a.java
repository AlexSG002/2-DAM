package hilos;

public class HiloEjemplo1a extends Thread{
	int numeroHilo=0;
	public HiloEjemplo1a(int numero) {
		numeroHilo=numero;
	}
	
	public void run() {
		System.out.println("Hola, soy el hilo "+numeroHilo);
	}
	
}
