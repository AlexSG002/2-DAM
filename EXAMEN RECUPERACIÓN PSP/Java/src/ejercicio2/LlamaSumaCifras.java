package ejercicio2;

import java.io.*;
import java.util.Scanner;

public class LlamaSumaCifras {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProcessBuilder pb = new ProcessBuilder("java", "ejercicio2.SumaCifras");
		pb.directory(new File("./bin"));
		try {
			Process p = pb.start();
			Writer salidaProceso = new OutputStreamWriter(p.getOutputStream());
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
				if (linea.contains("Introduce un número para sumar sus cifras: "));
				break;
			}
			String num = sc.next();
			salidaProceso.write(num + "\n");
			salidaProceso.flush();
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
