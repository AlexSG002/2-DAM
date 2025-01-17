package ejercicioPropuesto;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12346;
        final int BUFFER_SIZE = 1024;
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);

            while (true) {
                System.out.print("Introduce un número (>0 para continuar, <=0 para salir): ");
                int numero = scanner.nextInt();

                Numeros numeros = new Numeros();
                numeros.setNumero(numero);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                oos.flush();
                byte[] sendData = baos.toByteArray();

                InetAddress serverInetAddress = InetAddress.getByName(SERVER_ADDRESS);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, SERVER_PORT);
                socket.send(sendPacket);

                if (numero <= 0) {
                    System.out.println("Número <= 0. Finalizando cliente UDP.");
                    break;
                }

                byte[] receiveBuffer = new byte[BUFFER_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                try {
                    socket.receive(receivePacket);

                    ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Numeros respuesta = (Numeros) ois.readObject();

                    System.out.println("Cuadrado: " + respuesta.getCuadrado());
                    System.out.println("Cubo: " + respuesta.getCubo());
                } catch (SocketTimeoutException e) {
                    System.err.println("Timeout: No se recibió respuesta del servidor.");
                } catch (ClassNotFoundException e) {
                    System.err.println("Error al deserializar el objeto: " + e.getMessage());
                }

                if (numero <= 0) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente UDP: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
