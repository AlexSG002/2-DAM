package ejercicio2;

import java.io.*;

public class SumaCifrasFicheros {

	public static void main(String[] args) {
		File entradaNumero = new File("numeros.txt");
		File salidaError = new File("error.txt");
		File salidaNumero = new File("salida.txt");
		
		
		ProcessBuilder pb = new ProcessBuilder("java", "ejercicio2.SumaCifras");
		pb.directory(new File("./bin"));

		pb.redirectInput(entradaNumero);
		pb.redirectError(salidaError);
		pb.redirectOutput(salidaNumero);
		try {
			Process p = pb.start();
			BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;

			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}

			try {
				InputStream er = p.getErrorStream();
				BufferedReader brer = new BufferedReader(new InputStreamReader(er));
				String liner = null;

				while ((liner = brer.readLine()) != null)
					System.out.println("ERROR>" + liner);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
