package pruebas;

import java.io.*;
import java.io.InputStream;

public class Prueba2 {

	public static void main(String[] args) throws IOException {
		
		Process p = new ProcessBuilder("CMD", "/C", "DIR").start();	
		
		try {
		InputStream is=p.getInputStream();
		int c;
		while((c=is.read())!=-1)
			System.out.print((char)c);
		
		is.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int exitVal;
		
		try {
			exitVal=p.waitFor();
			System.out.println("Valor de salida: "+exitVal);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
