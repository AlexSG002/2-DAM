package ejercicioA;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {

	public static void main(String[] args) throws Exception {
		InetAddress destino = InetAddress.getLocalHost();
		int port = 12345;

		byte[] mensaje = new byte[1024];
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce un mensaje (Se contará la cantidad de a's): ");
		String texto = sc.nextLine();
		int contadorA = 0;
		mensaje = texto.getBytes();
		for (int i = 0; i < mensaje.length; i++) {
			char caracter = (char) mensaje[i];

			if (caracter == 'a') {
				contadorA++;
			}
			System.out.println("Número de a's: " + contadorA);
		}

		String numA = ", número de a's: " + contadorA;
		texto = texto + numA;
		
		mensaje = texto.getBytes();

		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
		DatagramSocket socket = new DatagramSocket(34567);

		System.out.println("Enviando Datagrama de la longitud: " + mensaje.length);
		System.out.println("Host destino            :" + destino.getHostName());
		System.out.println("IP destino              :" + destino.getHostAddress());
		System.out.println("Puerto local del socket :" + socket.getLocalPort());
		System.out.println("Puerto al que envio     :" + envio.getPort());

		socket.send(envio);
		socket.close();
		sc.close();
	}

}
