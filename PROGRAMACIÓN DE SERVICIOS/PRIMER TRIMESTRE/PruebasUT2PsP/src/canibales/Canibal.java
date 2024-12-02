package canibales;

public class Canibal implements Runnable {

private Olla laolla=null;
private int numero=0;
    
    public Canibal(Olla o,int num){
           laolla=o;
           numero=num;
    }
    public void run(){
    	
    	
            while (true){
                   laolla.SacardelaOlla(numero);
                   try {
   					Thread.sleep(700);
   				} catch (InterruptedException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
            }
    }
	
	
}