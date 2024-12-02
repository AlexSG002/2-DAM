package carrera;

public class EjemploSleep {

	public static void main(String[] args) {
		
		Thread tortuga1 = new Thread(new Tortuga(1));
		Thread tortuga2 = new Thread(new Tortuga(2));
		Thread tortuga3 = new Thread(new Tortuga(3));
		
		Thread liebre1 = new Thread(new Liebre(1));
		Thread liebre2 = new Thread(new Liebre(2));
		Thread liebre3 = new Thread(new Liebre(3));
		
		tortuga1.start();
		tortuga2.start();
		tortuga3.start();
		liebre1.start();
		liebre2.start();
		liebre3.start();
	}
}
