package pruebas.hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		ServerSocket servidor;
		servidor = new ServerSocket(6001);
		System.out.println("Servidor iniciando...");
		while(true) {
			Socket cliente = new Socket();
			cliente = servidor.accept(); //Esperando cliente
			HiloServidor hilo = new HiloServidor(cliente);
			hilo.start();
		}
	}
}
