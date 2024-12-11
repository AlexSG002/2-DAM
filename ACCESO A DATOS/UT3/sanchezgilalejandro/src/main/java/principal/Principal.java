package principal;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				System.out.println("Fin.");
				break;
			}
		} while (opcion != 4);
	}

	private static void mostrarMenu() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("1. EJERCICIO 1: ");
		System.out.println("2. EJERCICIO 2: ");
		System.out.println("3. EJERCICIO 3: ");
		System.out.println("4. Salir");
		System.out.println("-------------------------------------------------------------");
	}
}
