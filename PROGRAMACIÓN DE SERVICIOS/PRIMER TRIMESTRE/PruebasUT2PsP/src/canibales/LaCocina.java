package canibales;

public class LaCocina {
	public static void main(String[] args) {
		int tamaño = 5;// tamaño de la olla
		Olla laolla = new Olla(tamaño);
		// Crear el cocinero y lanzarlo
		// Crear 3 caníbales y lanzarlos
		Thread c = new Thread(new Cocinero(laolla));
		c.start();
		for (int i = 1; i <= 3; i++) {
			Thread ca = new Thread(new Canibal(laolla, i));
			ca.start();
		}

	}
}
