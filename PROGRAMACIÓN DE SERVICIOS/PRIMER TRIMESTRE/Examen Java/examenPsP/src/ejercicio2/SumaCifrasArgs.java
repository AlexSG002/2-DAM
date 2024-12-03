package ejercicio2;

import java.util.Scanner;

public class SumaCifrasArgs {

	public static void main(String[] args) {
		if (args.length > 0) {
			int numero = Integer.parseInt(args[0]);
			int total = 0;

			while (numero > 0) {
				int cifra = numero % 10;
				total += cifra;
				numero = numero / 10;
			}
			System.out.println("Producto de las cifras: " + total);
		}

	}
}
