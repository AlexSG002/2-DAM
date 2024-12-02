package ejercicio3;

public class Fabrica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ranuras=10;
		Cinta c = new Cinta(ranuras);
		//crear la cintatransportadora y los hilos
		Thread bUno = new Thread(new BrazoMecanicoUno(c));
		Thread bDos = new Thread(new BrazoMecanicoDos(c));
		bUno.run();
		bDos.run();
		
	}
}