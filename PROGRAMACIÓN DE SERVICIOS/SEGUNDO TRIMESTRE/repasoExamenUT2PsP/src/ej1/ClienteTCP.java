package ej1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        final String DIR_SERVIDOR = "localhost";
        final int PUERTO = 6000;
        Scanner sc = new Scanner(System.in);

        try (Socket socket = new Socket(DIR_SERVIDOR, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor en " + DIR_SERVIDOR + ":" + PUERTO);

            while (true) {
                System.out.println("Introduce una consulta (select * from fichero) o 'EXIT' para salir:");
                String consulta = sc.nextLine();

                salida.println(consulta);

                if (consulta.equalsIgnoreCase("EXIT")) {
                    break;
                }

                String[] partes = consulta.split("\\s+");
                if (partes.length == 4 && partes[0].equalsIgnoreCase("select") &&
                        partes[1].equals("*") && partes[2].equalsIgnoreCase("from")) {

                    String archivoRespuesta = "respuesta" + partes[3] + ".txt";
                    String primeraLinea = entrada.readLine();

                    if (primeraLinea != null && primeraLinea.startsWith("ERROR:")) {
                        System.err.println(primeraLinea);
                    } else {
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoRespuesta))) {
                            System.out.println("Recibiendo datos y guardando en: " + archivoRespuesta);

                            bw.write(primeraLinea);
                            bw.newLine();
                            System.out.println(primeraLinea);

                            String linea;
                            while ((linea = entrada.readLine()) != null) {
                                if (linea.equals("END_OF_FILE")) {
                                    break;
                                }
                                bw.write(linea);
                                bw.newLine();
                                System.out.println(linea);
                            }
                        } catch (IOException e) {
                            System.err.println("Error escribiendo el archivo de respuesta: " + e.getMessage());
                        }
                    }
                } else {
                    System.err.println("Formato de consulta inválido. Usa: select * from <nombre_fichero>");
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
