package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorChat extends Thread{
	DataInputStream fentrada;
	Socket socket = null;
	ComunHilos comun;
	
	public HiloServidorChat(Socket s, ComunHilos comun) {
		this.socket = s;
		this.comun = comun;
		
		try {
			fentrada = new DataInputStream(socket.getInputStream());
		}catch (IOException e ) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		System.out.println("NUMERO DE CONEXIONES ACTUALES: "+comun.getACTUALES());
		
		String texto = comun.getMensajes();
		EnviarMensajesATodos(texto);
		
		while(true) {
			String cadena = "";
			try {
				cadena = fentrada.readUTF();
				if(cadena.trim().equals("*")) {
					comun.setACTUALES(comun.getACTUALES() -1);
					System.out.println("NUMERO DE CONEXIONES ACTUALES: "+comun.getACTUALES());
					break;
				}
				comun.setMensajes(comun.getMensajes() +cadena+ "\n");
				EnviarMensajesATodos(comun.getMensajes());
					
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
		
		try {
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void EnviarMensajesATodos(String texto) {
		int i;
		
		for(i = 0; i < comun.getCONEXIONES(); i++) {
			Socket s1 = comun.getElementoTabla(i);
			if(!s1.isClosed()) {
				try {
					DataOutputStream fsalida = new DataOutputStream(s1.getOutputStream());
					fsalida.writeUTF(texto);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
