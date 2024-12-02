package pruebas;

import java.io.*;

public class Prueba5 {

	public static void main(String[] args) throws IOException {
		
		File directorio = new File (".\\bin");
		
		ProcessBuilder pb = new ProcessBuilder("java","pruebas.EjemploLectura");
		
		pb.directory(directorio);
		String w = System.getProperty("user.dir");
		System.out.println("Mi ruta es: "+w);
		Process p = pb.start();
		
		OutputStream os = p.getOutputStream();
		os.write("Hola Maria\n".getBytes());
		os.flush();
		
		try {
			InputStream er=p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			
			while((liner=brer.readLine())!=null)
				System.out.println("ERROR>"+liner);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			InputStream er = p.getInputStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			
			while((liner=brer.readLine())!=null)
				System.out.println("BIEEEEN!>"+liner);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
