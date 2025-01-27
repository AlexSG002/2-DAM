package hundirLaFlota;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int Puerto = 6000;
    private static final int RangoMatriz = 10;
    private static final int Barcos = 10;
    private static final int Intentos = 20;

    public static void main(String[] args) {
        int[][] tablero = Tablero.generarTablero();

        try (ServerSocket serverSocket = new ServerSocket(Puerto)) {
            System.out.println("Servidor iniciado. Esperando cliente en el puerto " + Puerto + "...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            int contadorIntentos = 0;
            int barcosHundidos = 0;

            out.write("Bienvenido al juego Hundir la Flota!\n");
            out.flush();

            while (contadorIntentos < Intentos && barcosHundidos < Barcos) {
                String mensaje = in.readLine();
                if (mensaje == null) {
                    break;
                }

                String[] partes = mensaje.trim().split(",");
                if (partes.length != 2) {
                    out.write("Formato inválido. Usa fila,columna (ej: 3,5)\n");
                    out.flush();
                    continue;
                }

                try {
                    int fila = Integer.parseInt(partes[0].trim());
                    int columna = Integer.parseInt(partes[1].trim());

                    if (fila < 0 || fila >= RangoMatriz || columna < 0 || columna >= RangoMatriz) {
                        out.write("Posición fuera de rango. Debe estar entre 0 y 9 para filas y columnas.\n");
                        out.flush();
                        continue;
                    }

                    if (tablero[fila][columna] == 1) {
                        tablero[fila][columna] = 2;
                        barcosHundidos++;
                        out.write("Hundido\n");
                    } else if (tablero[fila][columna] == 0) {
                        tablero[fila][columna] = -1;
                        out.write("Agua\n");
                    } else {
                        out.write("Ya atacaste esa posición.\n");
                        out.flush();
                        continue;
                    }

                    contadorIntentos++;
                    out.write("Intentos restantes: " + (Intentos - contadorIntentos) + "\n");
                    out.flush();
                } catch (NumberFormatException e) {
                    out.write("Entrada inválida. Asegúrate de enviar números enteros para fila y columna.\n");
                    out.flush();
                }
            }

            if (barcosHundidos == Barcos) {
                out.write("¡Felicidades! Has hundido todos los barcos en " + contadorIntentos + " intentos.\n");
            } else {
                out.write("Has agotado tus intentos. ¡Fin del juego!\n");
            }
            out.flush();

            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Cliente desconectado. Servidor finalizado.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
