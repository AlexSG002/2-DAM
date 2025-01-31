package ejercicio2;


public class SumaCifrasArgs {

	public static void main(String[] args) {
		if(args.length>=1){
			int num = Integer.parseInt(args[0]);
			int suma = 0;

			while (num > 0) {
				int cifra = num % 10;
				suma += cifra;
				num = num / 10;
			}

			System.out.println("Suma de las cifras: " + suma);
		}else {
			System.out.println("No hay número en los argumentos");
		}
		
	}
}
