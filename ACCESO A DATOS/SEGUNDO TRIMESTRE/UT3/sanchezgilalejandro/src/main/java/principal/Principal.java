package principal;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clases.Alumnos;
import clases.Nuevosalumnos;

import java.math.BigInteger;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Principal {

	private static SessionFactory factory;

	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		factory = Conexion.getSession();

		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				ejercicio1();
				break;
			case 2:
				ejercicio2();
				break;
			case 3:
				ejercicio3();
				break;
			case 4:
				System.out.println("Fin.");
				break;
			}
		} while (opcion != 4);
	}

	private static void ejercicio1() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String hqlCurso = "select c.codcurso, c.denominacion, c.precio, c.nivel, " + "count(distinct a) as numAlumnos, "
				+ "count(distinct ca.asignaturas.codasig) as numAsignaturas " + "from Cursos c "
				+ "inner join c.alumnoses a " + "inner join c.cursoasigs ca " + "inner join ca.asignaturas asig "
				+ "group by c.codcurso, c.denominacion, c.precio, c.nivel";

		Query<Object[]> queryCurso = session.createQuery(hqlCurso, Object[].class);
		List<Object[]> listaCursos = queryCurso.list();

		String hqlAsignaturas = "select ca.asignaturas.codasig, ca.asignaturas.nombreasig, "
				+ "ca.asignaturas.precioasig, ca.asignaturas.tipoasig, ca.asignaturas.creditos, "
				+ "count(distinct m.alumnos) as numAlumnos " + "from Cursos c " + "inner join c.cursoasigs ca "
				+ "inner join ca.asignaturas asig " + "left join Matriculas m on m.asignaturas = asig "
				+ "where c.codcurso = :codCurso " + "group by ca.asignaturas.codasig, ca.asignaturas.nombreasig, "
				+ "ca.asignaturas.precioasig, ca.asignaturas.tipoasig, ca.asignaturas.creditos "
				+ "order by ca.asignaturas.codasig";

		for (Object[] curso : listaCursos) {
			int codCurso = ((BigInteger) curso[0]).intValue();
			System.out.println("CODCURSO: " + codCurso + "             DENOMINACIÓN: " + curso[1]);
			System.out.println("Precio: " + curso[2] + "             Nivel:  " + curso[3]);
			System.out.println("Número de alumnos: " + curso[4] + "    Número de asignaturas: " + curso[5]);
			System.out.println("");

			Query<Object[]> queryAsignaturas = session.createQuery(hqlAsignaturas, Object[].class);
			queryAsignaturas.setParameter("codCurso", codCurso);
			List<Object[]> listaAsignaturas = queryAsignaturas.list();

			System.out.printf("%7s %10s %6s %8s %10s %10s %9s\n", "CODASIG", "NOMBREASIG", "PRECIO", "TIPOASIG",
					"%INCREMENTO", "NUM_ALUMNS", "TOTALASIG");
			System.out.printf("%7s %10s %6s %8s %10s %10s %9s\n", "-------", "----------", "------", "--------",
					"-----------", "----------", "---------");

			for (Object[] asignatura : listaAsignaturas) {
				if (asignatura[0] == null) {
					System.out.println("CURSO SIN ASIGNATURAS");
					System.out.println("----------------------------------------------------------------");
				} else {
					double porcentaje = 0;
					double incremento = 0;
					double total = 0;

					if (asignatura[3].equals('A')) {
						porcentaje = 0.05;
					} else if (asignatura[3].equals('B')) {
						porcentaje = 0.06;
					} else if (asignatura[3].equals('C')) {
						porcentaje = 0.08;
					} else if (asignatura[3].equals('D')) {
						porcentaje = 0.1;
					}

					BigInteger pvp = (BigInteger) asignatura[2];
					incremento = porcentaje * pvp.intValue();
					total = pvp.intValue() + incremento;
					System.out.printf("%7s %10s %6s %8s %10s %10s %9s\n", asignatura[0], asignatura[1], asignatura[2],
							asignatura[3], incremento, asignatura[5], total);
				}
			}
			System.out.println("");
		}

		tx.commit();
		session.close();
	}

	private static void ejercicio2() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String hqlMaxAlumnos = "select max(count(m.alumnos)) " + "from Asignaturas a "
				+ "left join Matriculas m on m.asignaturas = a " + "group by a.codasig, a.nombreasig";
		Query<Long> queryMax = session.createQuery(hqlMaxAlumnos, Long.class);
		Long maxAlumnos = (Long) queryMax.uniqueResult();
		if (maxAlumnos != null) {
			String hqlAsignaturasMax = "select a.codasig, a.nombreasig, count(m.alumnos) as numAlumnos "
					+ "from Asignaturas a " + "left join Matriculas m on m.asignaturas = a "
					+ "group by a.codasig, a.nombreasig " + "having count(m.alumnos) = :maxAlumnos "
					+ "order by numAlumnos desc";
			Query<Object[]> queryAsignaturasMax = session.createQuery(hqlAsignaturasMax, Object[].class);
			queryAsignaturasMax.setParameter("maxAlumnos", maxAlumnos);
			List<Object[]> result = queryAsignaturasMax.list();

			if (!result.isEmpty()) {
				System.out.println("Asignaturas con más alumnos:");
				for (Object[] asignatura : result) {
					System.out.println("Código: " + asignatura[0]);
					System.out.println("Nombre: " + asignatura[1]);
					System.out.println("Número de alumnos: " + asignatura[2]);
					System.out.println("--------------");
				}
			}
		}

		String hqlAlumnosNotaMax = "select a.codalum, a.nombre, avg(m.notaasig) as notaMedia " + "from Alumnos a "
				+ "inner join Matriculas m on m.alumnos = a " + "group by a.codalum, a.nombre "
				+ "order by notaMedia desc";

		Query<Object[]> query = session.createQuery(hqlAlumnosNotaMax, Object[].class);
		List<Object[]> resultado = query.list();
		if (!resultado.isEmpty()) {
			double notaMaxima = -1;
			for (Object[] row : resultado) {
				double notaMedia = (double) row[2];
				if (notaMedia > notaMaxima) {
					notaMaxima = notaMedia;
				}
			}
			System.out.println("Los alumnos con la mayor nota media (" + notaMaxima + "):");
			for (Object[] row : resultado) {
				BigInteger codalum = (BigInteger) row[0];
				String nombre = (String) row[1];
				double notaMedia = (double) row[2];
				if (notaMedia == notaMaxima) {
					System.out.println("Código: " + codalum);
					System.out.println("Nombre: " + nombre);
					System.out.println("Nota Media: " + notaMedia);
					System.out.println("--------------");
				}
			}
		}
		tx.commit();
		session.close();
	}

	private static void ejercicio3() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hqlAlumnoMax = "select max(codalum) from Alumnos";
		String hqlAlumnos = "from Alumnos";
		String hqlNuevosAlumnos = "from Nuevosalumnos";
		Query <Nuevosalumnos> qNuevo = session.createQuery(hqlNuevosAlumnos, Nuevosalumnos.class);
		List <Nuevosalumnos> listaNuevosAlumnos = qNuevo.list();
			Query<Alumnos> qAlumnos = session.createQuery(hqlAlumnos, Alumnos.class);
			List<Alumnos> listaAlumnos = qAlumnos.list();
			Query <BigInteger> queryAlumnoMax = session.createQuery(hqlAlumnoMax, BigInteger.class);
			BigInteger codAlumnoMax = queryAlumnoMax.uniqueResult();
			for(Nuevosalumnos na : listaNuevosAlumnos) {
				int codAlumnoMaxEntero = codAlumnoMax.intValue()+1;
				for(Alumnos a : listaAlumnos) {
					if(na.getCodalum().equals(a.getCodalum())) {
						System.out.println("Alumno con codigo ya existe: "+a.getCodalum()+" se cambia el código a : "+codAlumnoMaxEntero);
					}
				}
			}
		tx.commit();
		session.close();
	}

	private static void mostrarMenu() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("1. EJERCICIO 1");
		System.out.println("2. EJERCICIO 2");
		System.out.println("3. EJERCICIO 3");
		System.out.println("4. Salir");
		System.out.println("-------------------------------------------------------------");
		System.out.print("Seleccione una opción: ");
	}
}
