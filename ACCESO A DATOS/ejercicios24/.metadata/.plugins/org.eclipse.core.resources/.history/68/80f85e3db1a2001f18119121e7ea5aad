package principal;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clases.*;

public class Principal {

	private static SessionFactory factori;

	public static void main(String[] args) {

		factori = Conexion.getSession(); // Creo la sessionFactory una única vez.

		insertardep();

		factori.close();

	}

	private static void insertardep() {

		Session session = factori.openSession(); // creo una sesión de trabajo
		Transaction tx = session.beginTransaction();

		try {
			Departamentos dep = new Departamentos();
			dep.setDeptNo(BigInteger.valueOf(62));
			dep.setDnombre("Mmmmm");
			dep.setLoc("GUADALAJARA");

			session.persist(dep);
			tx.commit();
			System.out.println("Departamento insertado");

		} catch (org.hibernate.exception.ConstraintViolationException e) {
			System.out.println(e.getMessage() );
			
		}catch (org.hibernate.exception.GenericJDBCException e1) {
			System.out.println(e1.getMessage() );
		}
		session.close();
	

	}

}
