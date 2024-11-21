package ejercicio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class LlamaSumaCifrasArgs {

	public static void main(String[] args) {
		
		ProcessBuilder pb = new ProcessBuilder ("java","ejercicio2.SumaCifrasArgs","99");
		pb.directory(new File("./bin"));
		try {
			Process p = pb.start();
			
			Writer salidaProceso = new OutputStreamWriter(p.getOutputStream());
			
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea=lector.readLine()) != null){
				System.out.println(linea);
				
			}
			
			while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
