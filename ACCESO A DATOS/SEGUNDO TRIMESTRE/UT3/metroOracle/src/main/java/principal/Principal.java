package principal;

import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clases.TTrenes;


public class Principal {
	
	private static SessionFactory factory;

	public static void main(String[] args) {

		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		factory = Conexion.getSession(); // Creo la sessionFactory una única vez.
		
		insertarConInsert();
	}
	
	private static void insertarTrenesNuevos(){
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql ="From TTrenes t order by t.codTren";
		
		Query<TTrenes> q = session.createQuery(hql, TTrenes.class);
		
		List<TTrenes> lista = q.list();
		
		for(TTrenes tren : lista) {
			Set <TTrenes> trenes;
		}
		
		
		
	}
	
	private static void insertarConInsert() {
	    Session session = factory.openSession();

	    String con = "insert into TTrenes (codTren, nombre, tipo, TCocheras.codCochera, TLineas.codLinea) "
	               + "select n.codTren, n.nombre, n.tipo, cast(n.codCochera as integer), n.codLinea from TNuevosTrenes n";
	    Transaction tx = session.beginTransaction();
	    try {
	        Query cons = (Query) session.createMutationQuery(con);
	        int filas = cons.executeUpdate();

	        tx.commit();

	        System.out.printf("FILAS INSERTADAS: %d%n", filas);

	    } catch (org.hibernate.exception.ConstraintViolationException e) {
	        System.out.println(e.getMessage());
	        tx.rollback();
	    } finally {
	        session.close();
	    }
	}

	
}
