package pruebas;

import java.io.*;
import java.net.*;

public class Servidor1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int Puerto = 6001;
        ServerSocket Servidor;
      
        try {
            Servidor = new ServerSocket(Puerto);
            Socket ClienteConectado = Servidor.accept();
	        OutputStream salida = null;
	        salida = ClienteConectado.getOutputStream();
	        DataOutputStream flujoSalida = new DataOutputStream(salida);
	        
	        flujoSalida.writeUTF("Saludos al cliente del servidor");
	        
	        InputStream entrada = null;
	        entrada = ClienteConectado.getInputStream();
	        DataInputStream flujoEntrada = new DataInputStream(entrada);
	        
	        System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());
	        
	        entrada.close();
	        flujoEntrada.close();
	        salida.close();
	        flujoSalida.close();
	        ClienteConectado.close();
            Servidor.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
