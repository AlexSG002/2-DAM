package saludos;

public class Main {

	public static void main(String[] args) {
		if(args.length!=0) {
			for(int i=0; i<5; i++) {
				System.out.println(args[0]);
			}
		}else {
			System.out.println("No hay mensajes!");
			System.exit(1);
		}
	}
}
