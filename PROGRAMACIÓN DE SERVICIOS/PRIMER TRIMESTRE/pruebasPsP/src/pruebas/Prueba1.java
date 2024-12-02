package pruebas;
import java.io.*;

public class Prueba1 {

	public static void main(String[] args) throws IOException {
		
		ProcessBuilder pb = new ProcessBuilder("NOTEPAD");
		Process p = pb.start();
	}
}
