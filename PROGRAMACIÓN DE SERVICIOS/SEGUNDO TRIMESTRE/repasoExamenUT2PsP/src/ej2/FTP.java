package ej2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTP {

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
            
            ftpClient.makeDirectory("/nombreApellidos");
            ftpClient.changeWorkingDirectory("nombreApellidos");
            
            File directorioLocal = new File(".");
            File[] archivos = directorioLocal.listFiles((dir, name) -> name.startsWith("respuesta") && name.endsWith(".txt"));
            if(archivos != null && archivos.length > 0) {
            	for (File archivo : archivos) {
            		System.out.println("Subiendo: "+archivo.getName());
            		try(FileInputStream fis = new FileInputStream(archivo)){
            			boolean subido = ftpClient.storeFile(archivo.getName(), fis);
            			if(subido) {
            				System.out.println("Archivo subido");
            			}else {
            				System.out.println("Error al subir el archivo");
            			}
            		}
            	}
            }else {
            	System.out.println("No hay archivos que subir");
            }
            
            ftpClient.logout();
            ftpClient.disconnect();            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
	
}
