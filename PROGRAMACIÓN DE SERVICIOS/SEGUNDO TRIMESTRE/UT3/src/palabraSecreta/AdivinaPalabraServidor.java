package palabraSecreta;

import java.io.*;
import java.net.*;

public class AdivinaPalabraServidor {
    private static final int PUERTO = 6000;
    private static final String PALABRA = "gato";
    private static final int INTENTOS = 4;
    private static int contadorIdCliente = 1;

    public static void main(String[] args) {
        System.out.println("Servidor de Adivinanza de Palabras Iniciado.");
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Escuchando en el puerto " + PUERTO + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int idCliente = contadorIdCliente++;
                System.out.println("Cliente conectado: "+idCliente);

                new Thread(new ClienteSocket(clientSocket, idCliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static class ClienteSocket implements Runnable {
        private Socket socket;
        private int idCliente;

        public ClienteSocket(Socket socket, int idCliente) {
            this.socket = socket;
            this.idCliente = idCliente;
        }

        @Override
        public void run() {
            int intentosRestantes = INTENTOS;
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ) {
                out.println("Bienvenido al Juego de Adivinanza de Palabras!");
                out.println("Tienes " + INTENTOS + " intentos para adivinar la palabra secreta.");

                while (intentosRestantes > 0) {
                    String suPalabra = in.readLine();
                    if (suPalabra == null) {
                        System.out.println("Cliente "+idCliente+" desconectado.");
                        break;
                    }

                    System.out.println("Cliente "+idCliente+" adivinó: " + suPalabra);

                    if (suPalabra.equalsIgnoreCase(PALABRA)) {
                        out.println("¡Has ganado! La palabra secreta es '" + PALABRA + "'.");
                        System.out.println("Cliente "+idCliente+" ha ganado.");
                        break;
                    } else {
                        intentosRestantes--;
                        if (intentosRestantes > 0) {
                            out.println("Incorrecto. Te quedan " + intentosRestantes + " intentos.");
                            System.out.println("Cliente "+idCliente+" Intento fallido. Intentos restantes: " + intentosRestantes);
                        } else {
                            out.println("Has perdido. La palabra secreta era '" + PALABRA + "'.");
                            System.out.println("Cliente "+idCliente+" ha perdido.");
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al manejar al cliente: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    System.out.println("Conexión con el cliente cerrada.\n");
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
        }
    }
}
