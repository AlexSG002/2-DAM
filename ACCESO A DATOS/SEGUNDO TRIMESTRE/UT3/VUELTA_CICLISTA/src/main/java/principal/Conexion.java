package principal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexion {
	// Declaro la variable que voy a usar para la sesión en hibernate
	private static final SessionFactory sessionFactory;
	static {
		try {
			// Inicializo la variable estática con una nueva configuración de sesión
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Throwable es la clase base d las excepciones.
			System.err.println("INICIO DE SessionFactory fallado.." + ex);
			// Lanzo una excepción
			throw new ExceptionInInitializerError(ex);
			// esta excepción se lanza cuando hay un error al inicializar una variable
			// estática
		}
	}

	// Método que devuelve la sesion de hibernate
	public static SessionFactory getSession() {
		return sessionFactory;
	}
}