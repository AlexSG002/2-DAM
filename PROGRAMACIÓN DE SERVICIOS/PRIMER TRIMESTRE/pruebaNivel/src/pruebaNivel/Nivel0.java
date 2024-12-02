package pruebaNivel;

import java.util.Scanner;

public class Nivel0 {

	public static void main(String[] args) {
		System.out.println("Introduce el número de millas para pasarlo a KM");
		Scanner sc = new Scanner(System.in);
		double n=sc.nextInt();
		double millas=1852;
		double resultado=n/millas;
		System.out.println("El resultado es: "+resultado+" Kilómetros");
		sc.close();
	}
}
