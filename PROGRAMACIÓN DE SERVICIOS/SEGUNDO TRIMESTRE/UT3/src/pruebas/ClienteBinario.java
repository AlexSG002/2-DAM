package pruebas;

import java.io.*;
import java.net.*;

public class ClienteBinario {
	public static void main(String[] args) {

		try (ServerSocket serverSocket = new ServerSocket(6000)) {

			System.out.println("Esperando conexión al servidor");
			while (true) {

				try (Socket clientSocket = serverSocket.accept();

						DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
						DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())) {

					System.out.println("Cliente conectado.");

					int number;
					while (true) {
						
						try {
							number = dis.readInt();
						} catch (EOFException e) {
							
							System.out.println("Cliente cerró la conexión.");
							break;
							
						}

						if (number == 0) {
							
							System.out.println("Número 0 recibido. Cerrando conexión con el cliente.");
							break;
							
						}

						String binary = Integer.toBinaryString(number);
						System.out.println(number + " = " + binary);

						dos.writeUTF(binary);
						
					}

				} catch (IOException e) {
					
					System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
					
				}
			}

		} catch (IOException e) {
			
			System.err.println("Error en el servidor: " + e.getMessage());
			
		}
	}
}
