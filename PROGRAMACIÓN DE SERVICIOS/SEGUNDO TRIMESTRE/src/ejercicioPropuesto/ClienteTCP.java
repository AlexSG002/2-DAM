package ejercicioPropuesto;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.print("Introduce un número (>0 para continuar, <=0 para salir): ");
                int numero = scanner.nextInt();

                Numeros numeros = new Numeros();
                numeros.setNumero(numero);

                try (
                    Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ) {

                    oos.writeObject(numeros);
                    oos.flush();

                    if (numero <= 0) {
                        System.out.println("Número <= 0. Finalizando cliente.");
                        break;
                    }

                    Numeros respuesta = (Numeros) ois.readObject();
                    System.out.println("Cuadrado: " + respuesta.getCuadrado());
                    System.out.println("Cubo: " + respuesta.getCubo());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
                }

                if (numero <= 0) {
                    break;
                }
            }
        } finally {
            scanner.close();
        }
    }
}
