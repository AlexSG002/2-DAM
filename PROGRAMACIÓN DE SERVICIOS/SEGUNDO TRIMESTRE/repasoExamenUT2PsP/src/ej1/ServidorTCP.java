package ej1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        final int PUERTO = 6000;

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando a cliente...");

            while (true) {
                try (Socket socket = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Cliente conectado: " + socket.getInetAddress());
                    String consulta;

                    while ((consulta = entrada.readLine()) != null) {
                        System.out.println("Consulta recibida: " + consulta);

                        if (consulta.equalsIgnoreCase("EXIT")) {
                            System.out.println("Cliente desconectado.");
                            break;
                        }

                        String[] partes = consulta.split("\\s+");
                        if (partes.length == 4 && partes[0].equalsIgnoreCase("select")
                                && partes[1].equals("*") && partes[2].equalsIgnoreCase("from")) {

                            String archivo = partes[3] + ".csv";
                            File f = new File(archivo);

                            if (f.exists() && !f.isDirectory()) {
                                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                                    String linea;
                                    while ((linea = br.readLine()) != null) {
                                        salida.println(linea);
                                    }
                                    salida.println("END_OF_FILE");
                                }
                            } else {
                                salida.println("ERROR: El archivo " + archivo + " no existe.");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
