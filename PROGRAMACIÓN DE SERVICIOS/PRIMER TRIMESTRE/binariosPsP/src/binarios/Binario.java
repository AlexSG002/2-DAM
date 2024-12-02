package binarios;

import java.util.Scanner;

public class Binario {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce un número decimal para traducirlo a binario");
		int num = sc.nextInt();
		
		String binario = "";
		int div = num;
		int rest = div;
		while(div>=1) {
			rest = rest%2;
			binario=rest+binario;
			div = div/2;
			rest = div;
		}
		System.out.println(binario);
		sc.close();
	}
	
}
