package palabraSecreta;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class AdivinaPalabraCliente {
    private static final String LOCALHOST = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        System.out.println("Cliente de Adivinanza de Palabras Iniciado.");

        try (
            Socket socket = new Socket(LOCALHOST, PUERTO);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
        ) {
            String mensajeServidor;
            while ((mensajeServidor = in.readLine()) != null) {
                System.out.println(mensajeServidor);
                if (mensajeServidor.contains("intentos para adivinar")) {
                    break;
                }
            }

            while (true) {
                System.out.print("Introduce tu palabra: ");
                String suPalabra = sc.nextLine().trim();

                out.println(suPalabra);

                String respuesta = in.readLine();
                if (respuesta == null) {
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }

                System.out.println(respuesta);

                if (respuesta.startsWith("¡Has ganado!") || respuesta.startsWith("Has perdido.")) {
                    break;
                }
            }

            System.out.println("Juego finalizado. Gracias por jugar.");
        } catch (UnknownHostException e) {
            System.err.println("Servidor desconocido: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        }
    }
}
