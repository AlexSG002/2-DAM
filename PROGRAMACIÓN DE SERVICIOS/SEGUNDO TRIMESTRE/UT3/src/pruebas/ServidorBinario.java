package pruebas;

import java.io.*;
import java.net.*;

public class ServidorBinario {
    public static void main(String[] args) throws Exception {
        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor escuchando en el puerto " + puerto);
        while (true) {
            Socket clienteConectado = servidor.accept();
            DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            int numero = flujoEntrada.readInt();

            if (numero == 0) {
                System.out.println("Cliente ha enviado 0. Cerrando el servidor.");
                break;
            }

            String binario = Integer.toBinaryString(numero);
            flujoSalida.writeUTF(binario);

            flujoEntrada.close();
            flujoSalida.close();
            clienteConectado.close();
        }

        servidor.close();
        System.out.println("Servidor cerrado.");
    }
}
