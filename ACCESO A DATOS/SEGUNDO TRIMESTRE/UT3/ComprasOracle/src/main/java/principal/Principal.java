package principal;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import clases.*;

public class Principal {

	private static SessionFactory factory;
	
	public static void main(String[] args) {
		
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		factory = Conexion.getSession(); //Creo la sessionFactory una única vez.

		conuniqueresultmaxproductoporcompra();
		// Utilizando la clase auxiliar
		listartotalclientes1();
		
		// Consulta from Clientes
		listartotalclientes2();
		factory.close();


	}

	private static void listartotalclientes2() {
		
		Session session = factory.openSession();
		String hql ="From Clientes c order by c.codcliente";
		Query<Clientes> q = session.createQuery(hql, Clientes.class);
		List<Clientes> lista = q.list();
		int num = lista.size();
		System.out.printf("%10s %-30s %10s %10s %n","CODCLIENTE",
				"NOMBRE CLIENTE","NUMCOMPRAS","TOTAL" );
		System.out.printf("%10s %-30s %10s %10s %n","----------",
				"------------------------------","----------","----------" );
		Long tcon=0l;
		Double tsuma = 0d;
		for (Clientes tt: lista){
			
			System.out.printf("%10s %-30s %10s %10s %n",
					tt.getCodcliente(), tt.getNombre(),
					tt.getComprases().size(),0);
				
			
			
			tcon = tcon + tt.getComprases().size();
			tsuma = tsuma + 0;
			
		}
		System.out.printf("%10s %-30s %10s %10s %n","----------",
				"------------------------------","----------","----------" );
	
		System.out.printf("%10s %-30s %10s %10s %n",
				"TOTALES =>","",tcon, tsuma);
		session.close();
		
		
		session.close();
	}
	
	
	
	private static void listartotalclientes1() {
		
		Session session = factory.openSession();
		String hql = "select new clases.TotalCliente(c.codcliente, c.nombre , count(distinct con),"
				+ " sum(det.unidades * det.productos.pvp) ) "
				+ " from Clientes c left join c.comprases con join con.detcomprases det"
				+ " group by c.codcliente, c.nombre "
				+ " order by c.codcliente";
		
		Query<TotalCliente> q = session.createQuery(hql, TotalCliente.class);
		List<TotalCliente> lista = q.list();
		int num = lista.size();
		
		
		System.out.printf("%10s %-30s %10s %10s %n","CODCLIENTE",
				"NOMBRE CLIENTE","NUMCOMPRAS","TOTAL" );
		System.out.printf("%10s %-30s %10s %10s %n","----------",
				"------------------------------","----------","----------" );
		Long tcon=0l;
		Double tsuma = 0d;
		for (TotalCliente tt: lista){
			
			//System.out.println(tt.toString());
			System.out.printf("%10s %-30s %10s %10s %n",
			tt.getCodcliente(), tt.getNombre(), tt.getContador(), tt.getSuma());
			tcon = tcon + tt.getContador();
			tsuma = tsuma + tt.getSuma();
			
		}
		System.out.printf("%10s %-30s %10s %10s %n","----------",
				"------------------------------","----------","----------" );
	
		System.out.printf("%10s %-30s %10s %10s %n",
				"TOTALES =>","",tcon, tsuma);
		session.close();
	}

	private static void conuniqueresultmaxproductoporcompra() {
		
		Session session = factory.openSession();
	
		Query<Compras> q = session.createQuery("from Compras c order by c.numcompra",Compras.class);
		
		List<Compras> lista = q.list();
		int num = lista.size();
		if (num>0) {
			
			//NUMCOMPRA   CODCLIENTE  NOMBRE   PROD MÁS CARO DELA COMPRA(con Uniqueresult)
			System.out.printf("%9s %10s %30s %30s %n","NUMCOMPRA","CODCLIENTE",
					"NOMBRE CLIENTE","PROD MÁS CARO");
			System.out.printf("%9s %10s %30s %30s %n","---------","----------",
					"---------------------------","---------------------------");
		
			for (Compras com: lista){
				
				String con = " select d.productos.denominacion "
						+ " from Detcompras d where d.compras.numcompra = :numcom " 
						+ " and d.productos.pvp = "
						+ " (select max(d2.productos.pvp) "
						+ "  from Detcompras d2 where d2.compras.numcompra = :numcom ) ";
				
				   Query q2 =  session.createQuery(con, String.class);
				   q2.setParameter("numcom", com.getNumcompra());
				   String prod = (String) q2.uniqueResult();
				
				System.out.printf("%9s %10s %30s %30s %n",
						com.getNumcompra(), com.getClientes().getCodcliente(),
						com.getClientes().getNombre(),
						prod);

			}
			
			session.close();
		
	}
	
}//
	
} // fin clase

