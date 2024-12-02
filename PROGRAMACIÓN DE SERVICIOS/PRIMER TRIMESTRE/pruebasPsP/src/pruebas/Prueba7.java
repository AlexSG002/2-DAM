package pruebas;

import java.io.*;

public class Prueba7 {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD","/C","DIR");
		File fOut = new File("src\\pruebas\\salida.txt");
		File fErr = new File("error.txt");
		
		pb.redirectOutput(fOut);
		pb.redirectError(fErr);
		pb.start();
	}
}
