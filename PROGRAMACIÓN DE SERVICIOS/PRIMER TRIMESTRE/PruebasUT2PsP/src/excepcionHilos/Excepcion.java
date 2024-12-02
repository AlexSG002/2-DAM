package excepcionHilos;

public class Excepcion extends Thread{

	public void run() {
		try {
			while(!isInterrupted()) {
				System.out.println("En el hilo");
				Thread.sleep(10);
			}
		}catch(InterruptedException e) {
			System.out.println("Ha ocurrido una excepción");
		}
		
		System.out.println("Fin del hilo");
		
	}
	
	public void interrumpir() {
		interrupt();
	}
	
	
	public static void main(String[] args){
		Excepcion eh = new Excepcion();
		eh.start();
		for(int i = 0; i<1000000000; i++);
		eh.interrumpir();
	}
}
