package ejercicio2;

import java.io.*;

public class LlamaSumaCifrasArgs {

	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("java", "ejercicio2.SumaCifrasArgs","35");
		pb.directory(new File("./bin"));
		try {
			Process p = pb.start();
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
