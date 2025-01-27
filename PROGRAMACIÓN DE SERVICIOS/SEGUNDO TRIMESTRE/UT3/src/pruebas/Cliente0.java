package pruebas;

import java.io.*;
import java.net.*;

public class Cliente0 {

    public static void main(String[] args) {

        String Host = "localhost";
        int Puerto = 6000;
        Socket Cliente;

        try {
            Cliente = new Socket(Host, Puerto);
            InetAddress ip = Cliente.getInetAddress();
            System.out.println("Puerto local: " + Cliente.getLocalPort());
            System.out.println("Puerto remoto: " + Cliente.getPort());
            System.out.println("Host remoto: " + ip.getHostName());
            System.out.println("IP Host remoto: " + ip.getHostAddress());

            Cliente.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
