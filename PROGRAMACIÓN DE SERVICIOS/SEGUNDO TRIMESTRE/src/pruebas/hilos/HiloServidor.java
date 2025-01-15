package pruebas.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
	BufferedReader fentrada;
	PrintWriter fsalida;
	Socket socket = null;

	public HiloServidor(Socket s) {
		socket = s;

		try {
			fsalida = new PrintWriter(socket.getOutputStream(), true);
			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		}
	}
	
	public void run() {
		String cadena = "";
		while(!cadena.trim().equals("*")) {
			System.out.println("COMUNICO CON: "+socket.toString());
			try {
				cadena = fentrada.readLine();
			}catch(IOException e) {
				e.printStackTrace();
			}
			fsalida.println("FIN CON: "+cadena.trim().toUpperCase());
			fsalida.close();
			try {
				fentrada.close();
				socket.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
