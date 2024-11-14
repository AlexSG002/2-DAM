package principal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Principal {

	private static SessionFactory sesion;
	
	public static void main(String[] args) {
		
		sesion = Conexion.getSession(); //Creo la sessionFactory una única vez.

		
		sesion.close();


	}

}
