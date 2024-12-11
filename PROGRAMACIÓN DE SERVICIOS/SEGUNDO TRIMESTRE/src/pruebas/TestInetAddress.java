package pruebas;

import java.net.*;

public class TestInetAddress {

	public static void main(String[] args) {
		InetAddress dir = null;

		System.out.println("====================================================");
		System.out.println("SALIDA PARA LOCALHOST: ");
		try {
			// LOCALHOST
			// dir tendrá la dirección ip del localhost
			dir = InetAddress.getByName("localhost");
			pruebaMetodos(dir);

			System.out.println("====================================================");
			System.out.println("SALIDA PARA UNA URL:");
			if (args.length > 0) {
				dir = InetAddress.getByName(args[0]);
			pruebaMetodos(dir);
			}
			// Array de tipo InetAddress con todas las direcciones IP asignadas a google.es
			System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostAddress());
			InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
			for (int i = 0; i < direcciones.length; i++) {
				System.out.println("\t\t" + direcciones[i].toString());
				System.out.println("====================================================");
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}

	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("\tMetodo getByName(): " + dir);
		InetAddress dir2;

		try {
			// Dir 2 tendrá la dirección IP de la máquina donde se está ejecutando
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost: " + dir2);
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// USAMOS ETODOS DE LA CLASE
		// String con Nombre del host:
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		// String con la dirección IP del host
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		// String con el nombre del dominio completo
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
	}

}
