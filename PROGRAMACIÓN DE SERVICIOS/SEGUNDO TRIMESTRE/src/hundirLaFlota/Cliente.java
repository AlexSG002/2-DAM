package hundirLaFlota;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORT = 6000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Conectado al servidor en " + HOST + ":" + PORT);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);

            String bienvenida = in.readLine();
            System.out.println("Servidor: " + bienvenida);

            boolean juegoTerminado = false;

            while (!juegoTerminado) {
                System.out.print("Ingresa una posición (fila,columna) o ingresa (*) para salir: ");
                String entrada = scanner.nextLine();
                
                if(entrada.equals("*")) {
                	juegoTerminado = true;
                	 System.out.print("Hasta luego!");
                	break;
                }
                
                if (!entrada.matches("\\d+,\\d+")) {
                    System.out.println("Formato inválido. Usa fila,columna (ej: 3,5)");
                    continue;
                }

                out.write(entrada + "\n");
                out.flush();

                String respuesta = in.readLine();
                if (respuesta == null) {
                    System.out.println("Servidor desconectado.");
                    break;
                }
                System.out.println("Servidor: " + respuesta);

                if (respuesta.equals("Hundido") || respuesta.equals("Agua")) {
                    String intentos = in.readLine();
                    if (intentos != null) {
                        System.out.println("Servidor: " + intentos);
                    }
                } else {
                    if (respuesta.startsWith("¡Felicidades") || respuesta.startsWith("Has agotado")) {
                        juegoTerminado = true;
                        String mensajeFinal = in.readLine();
                        if (mensajeFinal != null && !mensajeFinal.isEmpty()) {
                            System.out.println("Servidor: " + mensajeFinal);
                        }
                    } else {

                    }
                }
            }

            in.close();
            out.close();
            scanner.close();
            socket.close();
            System.out.println("Juego terminado. Conexión cerrada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
