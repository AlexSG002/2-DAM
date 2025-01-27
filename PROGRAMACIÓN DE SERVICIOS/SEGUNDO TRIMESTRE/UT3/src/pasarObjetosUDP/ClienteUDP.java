package pasarObjetosUDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {

	public static void main(String[] args) throws Exception{
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] recibidos = new byte[1024];
		InetAddress IPServidor = InetAddress.getLocalHost();
		int puerto = 9876;
		
		Persona per = new Persona("Maria" , 22);
		
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		os.writeObject(per);
		os.close();
		
		byte[] bytes = bs.toByteArray();
		
		System.out.println("Enviando "+ bytes.length + "bytes al servidor.");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPServidor, puerto);
		clientSocket.send(envio);
		
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		clientSocket.receive(recibo);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
		ObjectInputStream is = new ObjectInputStream(bais);
		Persona persona = (Persona)is.readObject();
		is.close();
		
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tProcedente de: "+IPOrigen+":"+puertoOrigen);
		System.out.println("\tDatos: "+persona.getNombre()+"*"+persona.getEdad());
		clientSocket.close();
	}
	
	
}
