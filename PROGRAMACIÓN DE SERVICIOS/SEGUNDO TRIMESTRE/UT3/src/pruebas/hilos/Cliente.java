package pruebas.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) throws IOException{
		String Host = "localhost";
		int Puerto = 6001;
		Socket Cliente = new Socket (Host, Puerto);
		
		PrintWriter fsalida = new PrintWriter (Cliente.getOutputStream(), true);
		
		BufferedReader fentrada = new BufferedReader(new InputStreamReader (Cliente.getInputStream()));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String cadena, eco="";
		
		System.out.println("Introduce cadena: ");
		cadena = in.readLine();
		while(cadena!= null) {
			fsalida.println(cadena);
			eco = fentrada.readLine();
			System.out.println(" =>ECO: "+eco);
			System.out.println("Introduce cadena: ");
			cadena = in.readLine();
		}
		fsalida.close();
		fentrada.close();
		System.out.println("Fin del envío...");
		in.close();
		Cliente.close();
	}
}
