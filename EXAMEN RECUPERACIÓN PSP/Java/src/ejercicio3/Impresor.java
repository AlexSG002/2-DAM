package ejercicio3;

import java.util.ArrayList;
import java.util.*;

public class Impresor implements Runnable{
	private Pila p=null;
    
    public Impresor(Pila p){
           this.p=p;
    }
    public void run(){
    	int item=0;
            while (true){
                item++;
                p.insertar((int) item);
                   try {
   			Thread.sleep(100);
   			
   		} catch (InterruptedException e) {
   			// TODO Auto-generated catch block
   		e.printStackTrace();
   		}
            }
    }
}