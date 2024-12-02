package binarios;

import java.io.*;

public class LLamaBinarioArgs {
	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("java", "binarios.BinarioArgs","44");
		pb.directory(new File("./bin"));
		try {
			Process p = pb.start();
			
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			
		
			while ((linea=lector.readLine()) != null){
				System.out.println(linea);
			}
	
			try {
				InputStream er=p.getErrorStream();
				BufferedReader brer = new BufferedReader(new InputStreamReader(er));
				String liner = null;
				
				while((liner=brer.readLine())!=null)
					System.out.println("ERROR>"+liner);
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
			int codigoCierre = p.waitFor();
			System.out.println("El proceso terminó con el código de error: "+codigoCierre);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
