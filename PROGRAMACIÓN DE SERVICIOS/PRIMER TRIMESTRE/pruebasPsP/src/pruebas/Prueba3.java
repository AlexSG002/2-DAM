package pruebas;

import java.io.*;

public class Prueba3 {

	public static void main(String[] args) throws IOException {
		String w = System.getProperty("user.dir");
		System.out.println("Mi ruta es: "+w);
		File directorio = new File(".\\bin");
		
		ProcessBuilder pb = new ProcessBuilder("java","pruebas.Prueba2");
		pb.directory(directorio);
		
		System.out.printf("El directorio de trabajo es: %s%n ",pb.directory());
		
		Process p = pb.start();
		
		try {
			InputStream is=p.getInputStream();
			int c;
			while((c=is.read())!=-1)
				System.out.print((char)c);
			is.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
