package ejercicio2;

import java.util.Scanner;

public class SumaCifras {

	public static void main(String[] args) {
		System.out.println("Introduce un número: ");
		Scanner sc = new Scanner(System.in);
		int numero = sc.nextInt();
		int total = 0;

		while (numero > 0) {
			int cifra = numero % 10;
			total += cifra;
			numero = numero / 10;
		}

		System.out.println("Producto de las cifras: " + total);
	}
}
