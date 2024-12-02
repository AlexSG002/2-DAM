package ejercicio3;

import java.util.ArrayList;

public class BrazoMecanicoDos implements Runnable{
	private Cinta c;
    
    public BrazoMecanicoDos(Cinta c){
           this.c=c;
    }
    public void run(){
    	double pieza=0.0;
            while (true){
            	pieza=pieza+1;
               c.insertar(pieza);
                 try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 
            }
    }
}
