package pruebas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Cliente1 {
    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int Puerto = 6001; // puerto remoto

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        // EL servidor ME ENVIA UN MENSAJE
        String cadena = flujoEntrada.readUTF();
        System.out.println("Recibiendo del SERVIDOR: \n\t" + cadena);

        // LE ENVIO DE NUEVO EL MENSAJE AL SERVIDOR
        flujoSalida.writeUTF(cadena);

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
