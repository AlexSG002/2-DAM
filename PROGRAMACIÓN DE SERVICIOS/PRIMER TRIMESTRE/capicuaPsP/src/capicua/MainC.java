package capicua;

import java.util.Scanner;

public class MainC {

	public static void main(String[] args) {
		System.out.println("Introduce un número para ver si es capicua");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int numeroOriginal=num;
		String numeroReversa="";
		int n=num;
		while(num>0) {
			n = n%10;
			numeroReversa +=n;
			num = num/10;
			n = num;
		}
		if(Integer.parseInt(numeroReversa)==numeroOriginal) {
			System.out.println("Numero capicua!");
		}else {
			System.out.println("Tu número no es capicua :c");
		}
	}
}
