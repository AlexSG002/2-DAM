package ejercicio3;

public class BrazoMecanicoUno implements Runnable {
    private Cinta cinta;

    public BrazoMecanicoUno(Cinta cinta) {
        this.cinta = cinta;
    }

    @Override
    public void run() {
        while (true) {
            double pieza = cinta.extraer();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

   
}
