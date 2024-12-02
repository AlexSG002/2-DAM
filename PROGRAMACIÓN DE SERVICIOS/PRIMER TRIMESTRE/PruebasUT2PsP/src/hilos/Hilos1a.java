package hilos;

public class Hilos1a {

	public static void main(String[] args) {
		System.out.println("Inicio hilo principal");
		HiloEjemplo1a t1 = new HiloEjemplo1a(1);
		HiloEjemplo1a t2 = new HiloEjemplo1a(2);
		t1.start();
		t2.start();
		System.out.println("Fin del hilo principal");
	}
}
