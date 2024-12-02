package pruebaNivel;

import java.util.Scanner;

public class Nivel1 {
public static void main(String[] args) {
	System.out.println("Siendo la equación Ax^2+bx+c=0 y x = 3, introduce los valores de a,b y c para calcular");
	int x=3;
	Scanner sc = new Scanner(System.in);
	System.out.println("Introduce el valor de a");
	int a = sc.nextInt();
	System.out.println("Introduce el valor de b");
	int b = sc.nextInt();
	System.out.println("Introduce el valor de c");
	int c = sc.nextInt();
	
	int resultado=(a*(x*x))+(b*x)+(c);
	sc.close();
	System.out.println("El resultado de: "+a+"*3^2+"+b+"*3+"+c+" es: "+resultado);
}
}
