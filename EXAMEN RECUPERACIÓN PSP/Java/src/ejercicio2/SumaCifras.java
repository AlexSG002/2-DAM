package ejercicio2;

import java.util.Scanner;

public class SumaCifras {

	public static void main(String[] args) {
		System.out.println("Introduce un número para sumar sus cifras: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int suma = 0;

		while (num > 0) {
			int cifra = num % 10;
			suma += cifra;
			num = num / 10;
		}

		System.out.println("Suma de las cifras: " + suma);
		sc.close();
	}
}
