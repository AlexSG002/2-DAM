package principal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;
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
		
		listarTotalProductos();
		
		listarDatosPorProducto();
		
		borrarCliente(1);
		
		borrarCliente(6);
		
		borrarCliente(100);
		factory.close();


	}

	private static void borrarCliente(int cli) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
		String hqlDel = "delete Clientes c where c.codcliente = ?1";
		int filas = session.createMutationQuery( hqlDel ).setParameter(1, cli ).executeUpdate();
		if(filas!=0) {
			System.out.println("CLIENTE BORRADO: "+cli);
		}else {
			System.out.println("CLIENTE NO EXISTE: "+cli);
		}
		System.out.println("FILAS BORRADAS: "+filas);
		tx.commit();
		}catch(org.hibernate.exception.ConstraintViolationException e) {
			System.out.println("ATENCIÓN CLIENTE "+cli+" NO SE PUEDE BORRAR, TIENE REG RELACIONADOS.");
		}
		
		
		session.close();
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
	
	private static void listarTotalProductos() {
		Session session = factory.openSession();
		String hql ="From Productos p order by p.codproducto";
		Query<Productos> q = session.createQuery(hql, Productos.class);
		
		List<Productos> lista = q.list();

		System.out.println();
		
		int totaluni = 0, max =0;
		float totalimp = 0;
		String nommax = "";
//		String hql2 = "select coalesce(sum( d.unidades ), 0) from, Detcompras de where d.id.codproducto =: cod";
		for(Productos p : lista) {
			Set <Detcompras> detalle = p.getDetcomprases();
			int sumauni = 0;
			for(Detcompras de : detalle) {
				sumauni = sumauni + de.getUnidades().intValue();
			}
			
//			Query<BigInteger> q2 = session.createQuery(hql2, BigInteger.class);
//			q2.setParameter("cod", p.getCodproducto());
//			BigInteger ss = q2.uniqueResult();
//			sumauni = ss.intValue();
			
			float total = p.getPvp().floatValue()*sumauni;
			System.out.printf("%10s %-30s %10s %10s %n", p.getCodproducto(), p.getDenominacion(), p.getPvp(), sumauni, total);
			
			totaluni = totaluni+sumauni;
			totalimp = totalimp +total;
			
			if(sumauni >= max) {
				
				if(sumauni == max) {
					nommax = nommax + p.getDenominacion();
				}else {
					nommax = p.getDenominacion();
				}
				
			}
		}
		
		System.out.printf("%10s %-30s %10s %10s %n", "TOTALES: ", "", "", totaluni, totalimp);
		
		System.out.println("Producto/s más vendido/s( "+max+" ): "+nommax);
		
		session.close();
		
	}
	
	
	
	
	private static void listarDatosPorProducto() {
		Session session = factory.openSession();
		String hql ="From Productos p order by p.codproducto";
		Query<Productos> q = session.createQuery(hql, Productos.class);
		
		List<Productos> lista = q.list();
		for(Productos p: lista) {
			System.out.println("*****");
			System.out.println("CODIGO PRODUCTO: "+p.getCodproducto());
			System.out.println("Denominación: "+p.getDenominacion()+"    Precio: "+p.getPvp());
			System.out.println("---------------------------------------------------------------------------------------");
			
			if(p.getDetcomprases().size()==0) {
				System.out.println("   ** SIN COMPRAS **");
				System.out.println("---------------------------------------------------------------------------------------");
			}else {
				System.out.printf("%10s %10s %10s %-30s %10s %10s %n", "Num_Compra","FechaCompras","CodCliente","Nombre Cliente", "Unidades","Importe");
				System.out.printf("%10s %10s %10s %-30s %10s %10s %n", "-----------","-----------","-----------","---------------------------------", "-----------","-----------");
				
				Set <Detcompras> detalle = p.getDetcomprases();
				int sumauni = 0;
				float timp = 0;
				
				for(Detcompras de:detalle) {
					float imp = de.getUnidades().intValue() * p.getPvp().floatValue();
					timp=timp+imp;
					sumauni = sumauni + de.getUnidades().intValue();
					System.out.printf("%10s %10s %10s %-30s %10s %10s %n", de.getCompras().getNumcompra(),
							de.getCompras().getFecha(),
							de.getCompras().getClientes().getCodcliente(),
							de.getCompras().getClientes().getNombre(), 
							de.getUnidades(), imp);
				}
				System.out.printf("%10s %10s %10s %-30s %10s %10s %n", "-----------","-----------","-----------","---------------------------------", "-----------","-----------");
				System.out.printf("%10s %10s %10s %-30s %10s %10s %n", "TOTALES","","","", sumauni, timp);
			}
		}
		
		session.close();
	}
	
} // fin clase

