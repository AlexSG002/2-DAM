package ejercicio3;

public class Productor implements Runnable{
	private Pila p=null;
    
    public Productor(Pila p){
           this.p=p;
    }
    public void run(){
    	int documento=0;
            while (true){
               documento = p.imprimir();
                 try {
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
                 
            }
    }
}

