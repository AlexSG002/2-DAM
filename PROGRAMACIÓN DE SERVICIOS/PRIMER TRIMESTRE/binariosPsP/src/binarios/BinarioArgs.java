package binarios;


public class BinarioArgs {

	public static void main(String[] args) {
		 if (args.length == 0) {
	            System.out.println("Error: No se ha proporcionado ningún número.");
	            return;
	        }
		
		System.out.println("Introduce un número decimal para traducirlo a binario");
		int num = Integer.parseInt(args[0]);
		
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
	}
	
}
