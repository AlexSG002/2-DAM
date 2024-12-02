package saludos;

import java.io.*;
public class LlamadaPB {

	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("java", "saludos.Main","Hola desde LlamadaPB!");
		pb.directory(new File("./bin"));
		try {
			Process p = pb.start();
			
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea=lector.readLine()) != null){
				System.out.println(linea);
			}
			
			int codigoCierre = p.waitFor();
			System.out.println("El proceso terminó con el código: "+codigoCierre);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
