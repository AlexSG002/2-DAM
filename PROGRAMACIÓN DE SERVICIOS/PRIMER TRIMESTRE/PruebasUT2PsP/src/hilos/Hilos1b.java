package hilos;

public class Hilos1b {

	public static void main(String[] args) {
		System.out.println("Inicio hilo principal");
		Thread t1 = new Thread(new HiloEjemplo1b(1));
		Thread t2 = new Thread(new HiloEjemplo1b(2));
		t1.start();
		t2.start();
		System.out.println("Fin del hilo principal");
	}
}
