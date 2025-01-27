package pasarObjetosUDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {

	public static void main(String[] args) throws Exception {
		byte[] recibidos = new byte[1024];

		// Asocio el socket al puerto 12345
		DatagramSocket serverSocket = new DatagramSocket(12345);
		System.out.println("Esperando Datagrama .......... ");
		DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
		serverSocket.receive(paqRecibido);// recibo datagrama
		
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
		ObjectInputStream in = new ObjectInputStream(bais);
		Persona persona = (Persona) in.readObject();
		in.close();
		
		InetAddress IPOrigen = paqRecibido.getAddress();
		int puerto = paqRecibido.getPort();
		System.out.println("\tProcedente de: "+IPOrigen+":"+puerto);
		System.out.println("\tDatos: "+persona.getNombre()+"*"+persona.getEdad());
		
		persona.setNombre("Maria Dolores");
		persona.setEdad(34);
		
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		os.writeObject(persona);
		os.close();
		
		byte[] bytes = bs.toByteArray();
		
		serverSocket.close(); // cierro el socket
		System.out.println("Enviando "+bytes.length+ " bytes al cliente.");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPOrigen, puerto);
		serverSocket.send(envio);
		serverSocket.close();
		System.out.println("Socket cerrado...");
	}
	
}
