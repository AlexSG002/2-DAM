package pruebas;

import java.io.*;

public class Prueba4 {

	public static void main(String[] args) throws IOException {
		
		Process p = new ProcessBuilder("CMD","/C","DATE").start();
		
		OutputStream os = p.getOutputStream();
		
		os.write("06/12/2018".getBytes());
		os.flush();
		
		InputStream is = p.getInputStream();
		int c;
		while((c=is.read())!=-1)
			System.out.print((char)c);
		
		is.close();
		
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de salida: "+exitVal);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
