package numeroSecreto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pruebas.hilos.HiloServidor;

public class ServidorAdivina {

	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(6001);
		System.out.println("Servidor iniciado...");
		
		int numero = (int) (1 + 25*Math.random());
		System.out.println("Número secreto: "+numero);
		
		ObjetoCompartido objeto = new ObjetoCompartido(numero);
		int id = 0;
		while(true) {
			Socket cliente = new Socket();
			cliente = servidor.accept();
			id++;
			HiloServidorAdivina hilo = new HiloServidorAdivina(cliente, objeto, id);
			hilo.start();
		}
	}
}
