package pruebas;

import java.io.*;
public class Prueba8 {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD");
		File fBat = new File("fichero.bat");
		File fOut = new File("salida.txt");
		File fErr = new File("error.txt");
		pb.redirectInput(fBat);
		pb.redirectError(fOut);
		pb.redirectError(fErr);
		pb.start();
	}
}
