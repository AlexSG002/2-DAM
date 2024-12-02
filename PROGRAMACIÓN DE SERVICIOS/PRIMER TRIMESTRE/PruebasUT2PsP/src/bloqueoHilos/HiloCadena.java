package bloqueoHilos;

public class HiloCadena extends Thread {
	private ObjetoCompartido objeto;
	String cad;

	public HiloCadena(ObjetoCompartido c, String s) {
		this.objeto = c;
		this.cad = s;
	}

	public void run() {
		synchronized (objeto) {
			for (int i = 0; i < 10; i++) {
				objeto.PintaCadena(cad);
				objeto.notify();
				try {
					objeto.wait();
				} catch (InterruptedException e) {

				}
			}
			objeto.notify();
		}
		System.out.println("\n"+cad+" finalizado");
	}

}
