package saludos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalidaTXT {

	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("java", "saludos.Main","Hola a Salida en TXT");
		pb.directory(new File("./bin"));
		File salida = new File("salida.txt");
		pb.redirectOutput(salida);
		try {
			Process p = pb.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
