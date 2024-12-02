	package pruebas;
	
	import java.io.*;
	import java.io.InputStream;
	
	public class EjemploError {
	
		public static void main(String[] args) throws IOException {
	
			Process p = new ProcessBuilder("CMD", "/C", "DIRR").start();
	
			try {
				InputStream is = p.getInputStream();
				int c;
				while ((c = is.read()) != -1)
					System.out.print((char) c);
	
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			int exitVal;
	
			try {
				exitVal = p.waitFor();
				System.out.println("Valor de salida: " + exitVal);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				InputStream er = p.getErrorStream();
				BufferedReader brer = new BufferedReader(new InputStreamReader(er));
				String liner = null;
				
				while((liner=brer.readLine())!=null)
					System.out.println("ERROR>"+liner);
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
