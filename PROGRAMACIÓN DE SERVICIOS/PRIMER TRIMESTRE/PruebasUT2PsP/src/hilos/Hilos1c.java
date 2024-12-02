package hilos;

public class Hilos1c {

	public static void main(String[] args) {
		System.out.println("Inicio hilo principal");
		
		for(int i = 0; i<5; i++) {
			HiloEjemplo1c h = new HiloEjemplo1c(i);
			h.start();
		}
		System.out.println("Fin del hilo principal");
	}
}
