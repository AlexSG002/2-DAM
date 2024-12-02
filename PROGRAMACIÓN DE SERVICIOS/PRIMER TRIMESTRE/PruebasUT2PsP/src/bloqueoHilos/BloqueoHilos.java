package bloqueoHilos;

public class BloqueoHilos {

	public static void main(String[] args) {
		ObjetoCompartido compartido = new ObjetoCompartido();
		HiloCadena a = new HiloCadena(compartido,"A");
		HiloCadena b = new HiloCadena(compartido,"B");
		a.start();
		b.start();
	}
}
