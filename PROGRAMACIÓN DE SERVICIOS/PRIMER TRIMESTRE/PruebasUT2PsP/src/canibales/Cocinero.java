package canibales;

public class Cocinero implements Runnable {

	private Olla laolla;

	public Cocinero(Olla o) {
		laolla = o;
		;
	}

	public void run() {
		int misionero = 0;
		while (true) {
			laolla.EcharAlaOlla(++misionero);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}