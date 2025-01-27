package pruebas;

import java.io.*;
import java.net.*;

public class Servidor0 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int Puerto = 6000;
        String Host = "localhost";
        ServerSocket Servidor;

        try {
            Servidor = new ServerSocket(Puerto);
            System.out.println("Escuchando en " + Servidor.getLocalPort());
            Socket cliente1 = Servidor.accept();
            Socket cliente2 = Servidor.accept();
            Servidor.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
