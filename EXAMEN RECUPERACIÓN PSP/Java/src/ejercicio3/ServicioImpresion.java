package ejercicio3;

public class ServicioImpresion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dimension=5;
		Pila p = new Pila(dimension);
		
		Thread impresor = new Thread(new Impresor(p));
		Thread productor = new Thread(new Productor(p));
		
		impresor.start();
		productor.start();
	}

}