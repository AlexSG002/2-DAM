package clases;

import org.apache.commons.net.ftp.*;

import java.io.IOException;

public class Main {
    
    private static final String SERVER = "localhost";
    private static final int PORT = 21;
    private static final String USER = "usuario1";
    private static final String PASSWORD = "usu1";

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            System.out.println("Conectado al servidor FTP: " + SERVER);     
            
            listarArchivos(ftpClient, "/");
            ftpClient.logout();
            ftpClient.disconnect();            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void listarArchivos(FTPClient ftpClient, String ruta) throws IOException {
        FTPFile[] archivos = ftpClient.listFiles(ruta);

        for (FTPFile archivo : archivos) {
            String fullPath = ruta + archivo.getName();
            if (archivo.isDirectory()) {
                System.out.println("[Directorio] " + fullPath);
                listarArchivos(ftpClient, fullPath + "/");
            } else {
                System.out.println("[Archivo] " + fullPath);
            }
        }
    }
}
