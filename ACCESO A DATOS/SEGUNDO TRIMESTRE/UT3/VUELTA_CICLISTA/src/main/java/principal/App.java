package principal;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Camisetas;
import datos.Ciclistas;
import datos.Equipos;
import datos.ResumenCamisetas;
import datos.ResumenCamisetasId;

public class App {
	private static SessionFactory sesion;
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion = Conexion.getSession(); // Creo la sessionFactory una única vez.
		Scanner sc = new Scanner(System.in);
		int op = 0;
		
		while(op!=4) {
			menu();
			op = sc.nextInt();
			switch(op) {
			case 1:
				System.out.println();
				actualizarCamisetas();
				System.out.println();
				break;
			case 2:
				System.out.print("\nDigame el código del equipo: ");
				BigInteger cod2 = sc.nextBigInteger();
				resumenEquipo(cod2);
				System.out.println();
				break;
			case 3:
				System.out.println();
				consultarGenerales();
				System.out.println();
				break;
			case 4:
				System.out.println("\nHASTA PRONTO!!\n");
				break;
			default:
				System.out.println("\nEsrá opción no esta disponible!!\n");
				break;
			}
		}
	}
	
	public static void actualizarCamisetas() {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("RELLENAR TABLA RESUMEN CAMISETAS");
		String hqlEq = "from Equipos";
		String hqlInser = "from ResumenCamisetas";
		Query<ResumenCamisetas> qR = session.createQuery(hqlInser, ResumenCamisetas.class);
		List<ResumenCamisetas> listaIn = qR.list();
		if(listaIn.size()==0) {
			Query<Equipos> query1 = session.createQuery(hqlEq, Equipos.class);
			List<Equipos> lista1 = query1.list();
			for(Equipos e : lista1) {
				System.out.println("Equipo: "+e.getCodigoequipo()+", "+e.getNombreequipo());
				System.out.printf("%40s %15s %10s %10s\n", "OPERACIÓN", "CAMISETA", "Nº VECES", "PREMIO");
				System.out.println("--------------------------------------------------------------------------------------------------------");
				String hql2 = "select c.nombreciclista, a.codigocamiseta, count(distinct l.etapas.codigoetapa) from Ciclistas c join Lleva l on c.nombreciclista = l.ciclistas.nombreciclista join Camisetas a on l.camisetas.codigocamiseta = a.codigocamiseta group by c.nombreciclista, a.codigocamiseta";
				Query<Object []> query2 = session.createQuery(hql2, Object[].class);
				List<Object []> lista2 = query2.list();
				for(Object[] c : lista2) {
					String ope = "INSERTADO: "+c[0];
					String hql3 = "select c.importepremio from Camisetas c where c.codigocamiseta = :cod";
					BigInteger importe1 = session.createQuery(hql3, BigInteger.class).setParameter("cod", c[1]).uniqueResult();
					double imp = importe1.doubleValue();
					double premio = imp * Integer.parseInt(String.valueOf(c[2]));
					System.out.printf("%-40s %10s %10s %12s\n",ope,c[1], c[2], premio);
					ResumenCamisetas re = new ResumenCamisetas();
					String hql4 = "from Ciclistas c where c.nombreciclista = :nombre";
					Ciclistas cicli = (Ciclistas) session.createQuery(hql4, Ciclistas.class).setParameter("nombre", c[0]).uniqueResult();
					re.setImportepremio(BigInteger.valueOf((long) premio));
					re.setNumveces(BigInteger.valueOf((long) c[2]));
					re.setCamisetas(obtenerCamiseta((BigInteger) c[1]));
					re.setEquipos(obtenerEquipo(e.getCodigoequipo()));
					re.setCiclistas(obtenerCiclista(cicli.getCodigociclista()));
					ResumenCamisetasId res = new ResumenCamisetasId((BigInteger) c[1], cicli.getCodigociclista(), e.getCodigoequipo());
					re.setId(res);
					session.persist(re);
				}
			}
		}else {
			System.err.println("\nYa están todas las filas insertadas\n");
		}
		tx.commit();
		session.close();
	}
	
	public static Ciclistas obtenerCiclista(BigInteger cod) {
		Session session = sesion.openSession();
		String hql = "from Ciclistas c where c.codigociclista = :cod";
		Ciclistas c = (Ciclistas) session.createQuery(hql, Ciclistas.class).setParameter("cod", cod).uniqueResult();
		if(c!=null) {
			return c;
		}
		session.close();
		return null;
	}
	
	public static Camisetas obtenerCamiseta(BigInteger cod) {
		Session session = sesion.openSession();
		String hql = "from Camisetas c where c.codigocamiseta = :cod";
		Camisetas c = (Camisetas) session.createQuery(hql, Camisetas.class).setParameter("cod", cod).uniqueResult();
		if(c!=null) {
			return c;
		}
		session.close();
		return null;
	}
	
	public static Equipos obtenerEquipo(BigInteger cod) {
		Session session = sesion.openSession();
		String hql = "from Equipos e where e.codigoequipo = :cod";
		Equipos e = (Equipos) session.createQuery(hql, Equipos.class).setParameter("cod", cod).uniqueResult();
		if(e!=null) {
			return e;
		}
		session.close();
		return null;
	}
	
	public static void consultarGenerales() {
		Session session = sesion.openSession();
		String hql1 = "select e.codigoetapa, e.km, e.pobsalida, e.pobllegada, e.ciclistas.nombreciclista from Etapas e where e.pobsalida = e.pobllegada and e.tipoetapa LIKE '%Montaña%'";
		Query<Object[]> query1 = session.createQuery(hql1, Object[].class);
		List<Object[]> lista1 = query1.list();
		System.out.printf("%8s %10s %20s %20s %20s\n", "CODIGO", "KM", "SALIDA", "LLEGADA", "GANADOR");
		System.out.printf("%8s %10s %20s %20s %20s\n", "------", "--------", "------------------", "------------------", "------------------");
		for(Object[] e : lista1) {
			System.out.printf("%8s %10s %20s %20s %20s\n", e[0], e[1], e[2], e[3], e[4]);
		}
		System.out.println();
		String hql2 = "select c.codigociclista, c.nombreciclista, e.codigoetapa, e.tipoetapa, t.codigotramo, t.nombretramo, t.categoria from Ciclistas c join "
				+ "Etapas e on c.codigociclista = e.ciclistas.codigociclista join Tramospuertos t on e.codigoetapa = t.etapas.codigoetapa where t.pendiente LIKE '%5,5%%' order by c.codigociclista";
		Query<Object []> query2 = session.createQuery(hql2, Object[].class);
		List<Object[]> lista2 = query2.list();
		System.out.printf("%10s %25s %10s %15s %15s %10s %15s\n", "COD_CICLI", "NOMBRE", "COD_ETA", "TIPO_ETA", "COD_TRA", "NOMBRE_TRA", "CATEGORIA");
		System.out.printf("%10s %25s %10s %15s %15s %10s %15s\n", "--------", "--------------------", "--------", "------------", "---------", "------------------", "----------------");
		for(Object[] i : lista2) {
			System.out.printf("%10s %25s %10s %15s %15s %10s %15s\n", i[0], i[1], i[2], i[3], i[4], i[5], i[6]);
		}
		System.out.println();
		String hql3 = "select e.codigoequipo, e.nombreequipo, c.nombreciclista, count(l) from Equipos e join Ciclistas c on e.codigoequipo = c.equipos.codigoequipo join Lleva l on c.codigociclista = l.ciclistas.codigociclista where l.camisetas.codigocamiseta = 4 group by e.codigoequipo, e.nombreequipo, c.nombreciclista  order by e.codigoequipo, c.nombreciclista";
		Query<Object []> query3 = session.createQuery(hql3, Object[].class);
		List<Object[]> lista3 = query3.list();
		System.out.printf("%10s %20s %25s %10s\n", "COD_EQ", "NOMBRE_EQ", "NOMBRE_CLI", "HA LLEVADO");
		System.out.printf("%10s %20s %25s %10s\n", "-------", "--------------", "-------------------", "---------------");
		for(Object [] a : lista3) {
			System.out.printf("%10s %20s %25s %10s\n", a[0], a[1], a[2], a[3]);
		}
		System.out.println();
		String hql4 = "from Equipos e order by e.codigoequipo";
		Query<Equipos> query4 = session.createQuery(hql4, Equipos.class);
		List<Equipos> lista4 = query4.list();
		System.out.printf("%12s %30s %12s %15s %10s\n", "COD_EQ", "NOMBRE", "COD_CA", "COLOR", "LLEVA");
		System.out.printf("%12s %30s %12s %15s %10s\n", "--------", "----------------------", "--------", "-----------", "-----------");
		for(Equipos eq : lista4) {
			String hql5 = "select e.codigoequipo, e.nombreequipo, c.codigocamiseta, c.color, count(l) \r\n"
					+ "from Equipos e\r\n"
					+ "join Ciclistas ci on e.codigoequipo = ci.equipos.codigoequipo\r\n"
					+ "join Lleva l on ci.codigociclista = l.ciclistas.codigociclista\r\n"
					+ "join Camisetas c on c.codigocamiseta = l.camisetas.codigocamiseta\r\n"
					+ "where e.codigoequipo = :cod\r\n"
					+ "group by e.codigoequipo, e.nombreequipo, c.codigocamiseta, c.color\r\n"
					+ "";
			Query<Object []> query5 = session.createQuery(hql5, Object[].class).setParameter("cod", eq.getCodigoequipo());
			List<Object[]> lista5 = query5.list();
			for(Object[] a : lista5) {
				System.out.printf("%12s %30s %12s %15s %10s\n", a[0], a[1], a[2], a[3], a[4]);
			}
		}
		session.close();
	}
	
	public static void resumenEquipo(BigInteger cod) {
		Session session = sesion.openSession();
		if(comproabrExisteEquipo(cod)) {
			String hql = "from Equipos e where e.codigoequipo = :cod";
			Equipos eq = (Equipos) session.createQuery(hql, Equipos.class).setParameter("cod", cod).uniqueResult();
			System.out.println("DATOS DEL EQUIPO CON CÓDIGO: "+cod);
			System.out.printf("COD_EQUIPO: %8s NOMBRE: %20s\nPaís: %15s\n", eq.getCodigoequipo(), eq.getNombreequipo(), eq.getPais());
			String hql1 = "select c.codigociclista, c.nombreciclista, count(e.codigoetapa), count(t.codigotramo) from Ciclistas c left join Etapas e on c.codigociclista = e.ciclistas.codigociclista left join Tramospuertos t on c.codigociclista = t.ciclistas.codigociclista where c.equipos.codigoequipo = :cod group by c.codigociclista, c.nombreciclista order by c.codigociclista";
			Query<Object []> query = session.createQuery(hql1, Object[].class).setParameter("cod", cod);
			List<Object []> lista = query.list();
			if(lista.size()>0) {
				System.out.printf("%10s %25s %10s %10s %15s\n", "CODIGO", "NOMRBE", "ETAPAS_WIN", "TRAMOS_GANADOS", "Nº VECES CAMISETA");
				System.out.printf("%10s %25s %10s %10s %15s\n", "---------", "-------------------------", "-----------", "-----------", "-------------------");
				for(Object [] ci : lista) {
					System.out.printf("%10s %25s %8s %8s\n", ci[0], ci[1], ci[2], ci[3]);
				}
			}
			String hql2 = "";
		}else {
			System.out.println("Ese equipo no existe");
		}
		session.close();
	}
	
	public static boolean comproabrExisteEquipo(BigInteger cod) {
		Session session = sesion.openSession();
		boolean existe = false;
		String hql1 = "from Equipos e where e.codigoequipo = :cod";
		Equipos eq = (Equipos) session.createQuery(hql1, Equipos.class).setParameter("cod", cod).uniqueResult();
		if(eq!=null) {
			existe = true;
		}
		session.close();
		return existe;
	}
	
	public static void menu() {
		System.out.println("=====================================================");
		System.out.println("===================== CICLISMO BD ===================");
		System.out.println("=====================================================");
		System.out.println("1º) Rellenar Tabla RESUMEN_CAMISETAS");
		System.out.println("2º) Resumen ciclista de un Equipo");
		System.out.println("3º) Consultas HQL");
		System.out.println("4º) Salir");
		System.out.println("=====================================================");
		System.out.print("Eliga una opción: ");
	}
}