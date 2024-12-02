package capicua;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
public class LlamadaPB {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		ProcessBuilder pb = new ProcessBuilder("java", "capicua.MainC");
		pb.directory(new File("./bin"));
		try {
			Process p = pb.start();
			
			Writer salidaProceso = new OutputStreamWriter(p.getOutputStream());
			
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea=lector.readLine()) != null){
				System.out.println(linea);
				
				if(linea.contains("Introduce un n�mero para ver si es capicua"));
				break;
			}
			
			String num = sc.next();
			salidaProceso.write(num+"\n");
			salidaProceso.flush();
			
			while ((linea = lector.readLine()) != null) {
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
