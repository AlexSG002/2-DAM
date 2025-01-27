package ejercicioPropuesto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
	public static void main(String[] args) {
		final int PORT = 12346;
		final int BUFFER_SIZE = 1024;

		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			System.out.println("Servidor UDP iniciado y escuchando en el puerto " + PORT);

			byte[] buffer = new byte[BUFFER_SIZE];

			while (true) {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);

				ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
				ObjectInputStream ois = new ObjectInputStream(bais);
				Numeros numeroRecibido = (Numeros) ois.readObject();
				System.out.println("Recibido: " + numeroRecibido.getNumero());

				if (numeroRecibido.getNumero() <= 0) {
					System.out.println("Número menor o igual a cero. Cerrando servidor UDP.");
					break;
				}

				numeroRecibido.setCuadrado((long) numeroRecibido.getNumero() * numeroRecibido.getNumero());
				numeroRecibido.setCubo(
						(long) numeroRecibido.getNumero() * numeroRecibido.getNumero() * numeroRecibido.getNumero());

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(numeroRecibido);
				oos.flush();
				byte[] sendData = baos.toByteArray();

				InetAddress clientAddress = packet.getAddress();
				int clientPort = packet.getPort();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
				socket.send(sendPacket);
				System.out.println(
						"Enviado cuadrado: " + numeroRecibido.getCuadrado() + ", cubo: " + numeroRecibido.getCubo());
			}

			System.out.println("Servidor UDP finalizado.");
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error en el servidor UDP: " + e.getMessage());
		}
	}
}
