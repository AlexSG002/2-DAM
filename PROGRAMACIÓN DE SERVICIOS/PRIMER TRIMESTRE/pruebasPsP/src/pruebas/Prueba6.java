package pruebas;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Prueba6 {

	public static void main(String[] args) {
		ProcessBuilder test = new ProcessBuilder();
		
		Map entorno = test.environment();
		System.out.println("Variables de entorno: ");
		System.out.println(entorno);
		
		test = new ProcessBuilder("java","Hola","Maria");
		
		List l=test.command();
		Iterator iter=l.iterator();
		System.out.println("Los argumentos del comando son: ");
			while(iter.hasNext())
				System.out.println(iter.next());
			
			test.command("CMD","/C","DIR");
			try {
				Process p = test.start();
				InputStream is = p.getInputStream();
				int c;
				while((c=is.read())!=-1)
					System.out.print((char)c);
				
				is.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
}
