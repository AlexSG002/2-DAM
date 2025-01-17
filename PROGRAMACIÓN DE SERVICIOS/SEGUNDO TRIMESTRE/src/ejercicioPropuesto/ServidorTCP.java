package ejercicioPropuesto;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor TCP iniciado y escuchando en el puerto " + PORT);

            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();
                    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ) {
                    Numeros numeroRecibido = (Numeros) ois.readObject();
                    System.out.println("Recibido: " + numeroRecibido.getNumero());

                    if (numeroRecibido.getNumero() <= 0) {
                        System.out.println("Número menor o igual a cero. Cerrando servidor.");
                        break;
                    }

                    numeroRecibido.setCuadrado((long) numeroRecibido.getNumero() * numeroRecibido.getNumero());
                    numeroRecibido.setCubo((long) numeroRecibido.getNumero() * numeroRecibido.getNumero() * numeroRecibido.getNumero());

                    oos.writeObject(numeroRecibido);
                    oos.flush();
                    System.out.println("Enviado cuadrado: " + numeroRecibido.getCuadrado() + ", cubo: " + numeroRecibido.getCubo());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }

            System.out.println("Servidor TCP finalizado.");
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor TCP: " + e.getMessage());
        }
    }
}
