package Principal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import clasesMapeadas.*;

public class Principal {

	// Atributo paara la conexión
	private static SessionFactory sesion;

	public static void main(String[] args) {

		// Quitar los INFO de hibernate
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		// Establecer conexión con la BD
		sesion = Conexion.getSession();

		// Crear la clase Scanner para leer lo que introduce el usuario
		Scanner sc = new Scanner(System.in);

		// Declarar e inicializar la variable que guarda la opción que escoge el usuario
		int op = 0;

		// Mostrar el menú al usuario hasta que pulse el 0 para salir
		while (op != 6) {

			// Llamar al método para mostrar el menú
			menu();

			// Asignar a una variable lo que el usuario introduce por teclado
			op = sc.nextInt();

			// Switch case con los diferentes métodos por cada opción
			switch (op) {

			case 1:

				/*
				 * CÓDIGO EVALUACIÓN INCORRECTO
				 */
//				Evaluaciones evaluacion = new Evaluaciones();
//				EvaluacionesId evaluacionId = new EvaluacionesId();
//				evaluacionId.setNumAlumno(1010);
//				evaluacionId.setCodAsig(100);
//				evaluacionId.setCodEvaluacion(0);	
//				evaluacion.setNota(BigDecimal.valueOf(8.5)); 
//				evaluacion.setId(evaluacionId);
				
				/*
				 * NOTA INCORRECTA
				 */
//				Evaluaciones evaluacion = new Evaluaciones();
//				EvaluacionesId evaluacionId = new EvaluacionesId();
//				evaluacionId.setNumAlumno(1010);
//				evaluacionId.setCodAsig(100);
//				evaluacionId.setCodEvaluacion(3);	
//				evaluacion.setNota(BigDecimal.valueOf(77)); 
//				evaluacion.setId(evaluacionId);
				
				/*
				 * NO EXISTE LA ASIGNATURA
				 */
//				Evaluaciones evaluacion = new Evaluaciones();
//				EvaluacionesId evaluacionId = new EvaluacionesId();
//				evaluacionId.setNumAlumno(1010);
//				evaluacionId.setCodAsig(83667);
//				evaluacionId.setCodEvaluacion(3);	
//				evaluacion.setNota(BigDecimal.valueOf(10)); 
//				evaluacion.setId(evaluacionId);
				
				/*
				 * NO EXISTE EL ALUMNO
				 */
//				Evaluaciones evaluacion = new Evaluaciones();
//				EvaluacionesId evaluacionId = new EvaluacionesId();
//				evaluacionId.setNumAlumno(1);
//				evaluacionId.setCodAsig(100);
//				evaluacionId.setCodEvaluacion(3);	
//				evaluacion.setNota(BigDecimal.valueOf(10)); 
//				evaluacion.setId(evaluacionId);
				
				/*
				 * YA EXISTE NOTA PARA ESE ALUMNO EN ESA ASIGNATURA
				 */
//				Evaluaciones evaluacion = new Evaluaciones();
//				EvaluacionesId evaluacionId = new EvaluacionesId();
//				evaluacionId.setNumAlumno(1010);
//				evaluacionId.setCodAsig(1);
//				evaluacionId.setCodEvaluacion(1);	
//				evaluacion.setNota(BigDecimal.valueOf(4)); 
//				evaluacion.setId(evaluacionId);
				
				/*
				 * ASIGNATURA NO CORRESPONDIENTE AL CURSO DEL ALUMNO
				 */
//				Evaluaciones evaluacion = new Evaluaciones();
//				EvaluacionesId evaluacionId = new EvaluacionesId();
//				evaluacionId.setNumAlumno(1010);
//				evaluacionId.setCodAsig(5);
//				evaluacionId.setCodEvaluacion(3);	
//				evaluacion.setNota(BigDecimal.valueOf(8.5)); 
//				evaluacion.setId(evaluacionId);
				
				
				/*
				 * EVALUACION CORRECTA
				 */
				Evaluaciones evaluacion = new Evaluaciones();
				EvaluacionesId evaluacionId = new EvaluacionesId();
				evaluacionId.setNumAlumno(1010);
				evaluacionId.setCodAsig(100);
				evaluacionId.setCodEvaluacion(3);	
				evaluacion.setNota(BigDecimal.valueOf(8.5)); 
				evaluacion.setId(evaluacionId);
			
				// Llamar al método para realizar la inserción
				insertarEvaluacion(evaluacion);
				break;

			case 2:

				// Llamar al método para actualizar los datos
				actualizarDatos();
				
				break;

			case 3:
				
				// Llamar al método para mostrar los datos de cada centro
				mostrarDatosCursos();
				
				break;

			case 4:

				// Llamar al método para mostrar las estadísticas de los centros
				mostrarEstadisticasCentros();
				break;
			case 6:
				System.out.println("\nHASTA PRONTO\n");
				break;
			default:
				System.out.println("\nPrueba con otra opción\n");
				break;
			}
		}

		// Cerrar el scanner
		sc.close();

	}

	private static void mostrarEstadisticasCentros() {

	    // Imprimir cabecera
	    System.out.println("\nEjercicio 4. Estadística de centros.");
	    System.out.println("-----------------------------------.");
	    System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %-30s %n", 
	        "COD-CENTRO", "NOMBRE", "NUMCURSOS", "NUMALUMNOS", "NUM-ASIG", "NOTA-MEDIA", "ALUMNO MÁXIMO");
	    System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %-30s %n", 
	        "-------------------", "---------------------------------------", "-------------------", 
	        "-------------------", "-------------------", "-------------------", "-----------------------------");

	    // Inicializar variables para totales
	    int totalCursos = 0;
	    int totalAlumnos = 0;
	    int totalAsignaturas = 0;
	    double sumaNotaMediaTotal = 0;
	    int contadorNotasMedias = 0;

	    // Abrir sesión
	    Session session = sesion.openSession();

	    // Realizar consulta principal
	    Query<Object[]> cons = session.createQuery(
	        "select"
	        + "    ce.codCentro as codigoCentro,"
	        + "    ce.nombre as nombreCentro,"
	        + "    COUNT(DISTINCT cu.codCurso) AS numeroCursos,"
	        + "    COUNT(DISTINCT a.numAlumno) AS numeroAlumnos,"
	        + "    COUNT(DISTINCT asig.codAsig) AS numeroAsignaturas,"
	        + "    round(AVG(e.nota), 1) AS notaMedia"
	        + " from "
	        + "    Centros ce"
	        + " join"
	        + "    ce.cursoses cu"
	        + " join"
	        + "    Alumnos a on a.cursos.codCurso = cu.codCurso"
	        + " join"
	        + "    Asignaturas asig on asig.cursos.codCurso = cu.codCurso"
	        + " join"
	        + "    Evaluaciones e on e.alumnos.numAlumno = a.numAlumno and e.asignaturas.codAsig = asig.codAsig"
	        + " group by"
	        + "    ce.codCentro,"
	        + "    ce.nombre"
	        + " order by"
	        + "    ce.codCentro",
	        Object[].class
	    );

	    // Obtener lista de resultados de la consulta principal
	    List<Object[]> datos = cons.list();

	    // Iterar sobre los datos de la consulta principal
	    for (Object[] fila : datos) {
	        // Descomponer la fila
	        Integer codigoCentro = (Integer) fila[0];
	        String nombreCentro = (String) fila[1];
	        Long numeroCursos = (Long) fila[2];
	        Long numeroAlumnos = (Long) fila[3];
	        Long numeroAsignaturas = (Long) fila[4];
	        Double notaMedia = (Double) fila[5];

	        // Realizar consulta secundaria para obtener el alumno con la nota máxima
	        Query<Object[]> cons2 = session.createQuery(
	            "select"
	            + "    a.nombre as nombreAlumno,"
	            + "    round(AVG(e.nota), 1) as notaMediaAlumno"
	            + " from"
	            + "    Evaluaciones e"
	            + " join"
	            + "    e.alumnos a"
	            + " join"
	            + "    e.asignaturas asig"
	            + " join"
	            + "    asig.cursos cu"
	            + " join"
	            + "    cu.centros ce"
	            + " where"
	            + "    ce.codCentro = :codCentro "
	            + "group by"
	            + "    a.nombre"
	            + " order by"
	            + "    notaMediaAlumno desc",
	            Object[].class
	        );

	        cons2.setParameter("codCentro", codigoCentro);

	        // Obtener el alumno con la nota más alta
	        List<Object[]> datos2 = cons2.list();
	        String alumnoMaximo = "N/A";
	        Double notaMaxima = 0.0;

	        if (!datos2.isEmpty()) {
	            // Tomar el primer resultado (ya está ordenado por notaMediaAlumno desc)
	            Object[] mejorAlumno = datos2.get(0);
	            alumnoMaximo = (String) mejorAlumno[0];
	            notaMaxima = (Double) mejorAlumno[1];
	        }

	        // Imprimir los datos del centro
	        System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %-30s %n", 
	            codigoCentro, nombreCentro, numeroCursos, numeroAlumnos, 
	            numeroAsignaturas, notaMedia, alumnoMaximo + " (" + notaMaxima + ")");

	        // Actualizar totales
	        totalCursos += numeroCursos;
	        totalAlumnos += numeroAlumnos;
	        totalAsignaturas += numeroAsignaturas;
	        contadorNotasMedias++;
	        sumaNotaMediaTotal += notaMedia;
	    }

	    // Imprimir totales
	    System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %-30s %n", 
	        "-------------------", "---------------------------------------", "-------------------", 
	        "-------------------", "-------------------", "-------------------", "-----------------------------");
	    System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %-30s %n", 
	        "TOTALES", "", totalCursos, totalAlumnos, totalAsignaturas, 
	        sumaNotaMediaTotal / contadorNotasMedias, "");

	    // Cerrar sesión
	    session.close();
	}

	private static void mostrarDatosCursos() {
	    
		// Abrir sesión
	    Session session = sesion.openSession();

	    // Realizar consulta
	    Query<Object[]> cons = session.createQuery(
	        "select"
	        + "  c.codCurso as codigoCurso,"
	        + "  c.denominacion as nombreCurso,"
	        + "  ce.nombre as nombreCentro,"
	        + "  ce.localidad as localidad"
	        + " from"
	        + "  Cursos c"
	        + " join"
	        + "  c.centros ce",
	        Object[].class
	    );

	    // Crear lista con los datos
	    List<Object[]> datos = cons.list();

	    // Iterar sobre los datos de la consulta
	    for (Object[] fila : datos) {
	        
	        // Descomponer cada fila
	        int codCurso = (int) fila[0];
	        String nombreCurso = (String) fila[1];
	        String nombreCentro = (String) fila[2];
	        String localidadCentro = (String) fila[3];
	        
	        // Imprimir cabecera
	        System.out.printf("%n%-40s %-30s %n", "COD-CURSO: " + codCurso, "NOMBRE CURSO: " + nombreCurso);
	        System.out.printf("%-40s %-30s %n", "NOMBRE CENTRO: " + nombreCentro, "LOCALIDAD: " + localidadCentro);
	        System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %n", "\tNUM_ALUMNO", "NOMBRE", "NOTA_EVA1", "NOTA_EVA2", "NOTA_EVA3", "NOTA-MEDIA");
	        System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %n", "\t-------------------", "---------------------------------------", "-------------------", "-------------------", "-------------------", "-------------------");
	        
	        // Realizar Consulta
	        Query<Object[]> cons2 = session.createQuery(
	            "select"
	            + "  a.numAlumno"
	            + " from"
	            + "  Alumnos a"
	            + " join"
	            + "  Cursos cu on cu.codCurso = a.cursos.codCurso"
	            + " join"
	            + "  Centros ce on ce.codCentro = cu.centros.codCentro"
	            + " where "
	            + "  cu.codCurso = :codCurso",
	            Object[].class
	        );
	        
	        // Establecer el parámetro dinámico para el número de alumno
	        cons2.setParameter("codCurso", codCurso);

	        // Crear lista con los datos
	        List<Object[]> datos2 = cons2.list();
	        
	        // Inicializar variables
	        int contadorNotas = 0;
	        double sumaNotasEval1 = 0;
	        double sumaNotasEval2 = 0;
	        double sumaNotasEval3 = 0;
	        double sumaNotasMedias = 0;
	        double notaMax = 0;  
	        List<String> ganadores = new ArrayList<>(); 
	        
	        for (Object[] fila2 : datos2) {
	            
	            // Descomponer cada fila
	            int numAlumnoActual = (int) fila2[0];
	            
	            // Realizar consulta de notas
	            Query<Object[]> cons3 = session.createQuery(
	                "select"
	                + "  a.numAlumno as NumeroAlumno,"
	                + "  a.nombre as NombreAlumno,"
	                + "  coalesce(round(avg(case when e.id.codEvaluacion = 1 then e.nota end), 3), 0) as NOTA_MEDIA_EVAL_1,"
	                + "  coalesce(round(avg(case when e.id.codEvaluacion = 2 then e.nota end), 3), 0) as NOTA_MEDIA_EVAL_2,"
	                + "  coalesce(round(avg(case when e.id.codEvaluacion = 3 then e.nota end), 3), 0) as NOTA_MEDIA_EVAL_3,"
	                + "  round("
	                + "    ("
	                + "      coalesce(avg(case when e.id.codEvaluacion = 1 then e.nota end), 0) +"
	                + "      coalesce(avg(case when e.id.codEvaluacion = 2 then e.nota end), 0) +"
	                + "      coalesce(avg(case when e.id.codEvaluacion = 3 then e.nota end), 0)"
	                + "    ) / 3, "
	                + "    3"
	                + "  ) as NOTA_MEDIA_GLOBAL"
	                + " from "
	                + "  Alumnos a"
	                + " join"
	                + "  Evaluaciones e on e.alumnos.numAlumno = a.numAlumno"
	                + " where"
	                + "  a.numAlumno = :numAlumno"
	                + " group by"
	                + "  a.numAlumno,"
	                + "  a.nombre",
	                Object[].class
	            );

	            // Establecer el parámetro dinámico para el número de alumno
	            cons3.setParameter("numAlumno", numAlumnoActual);
	            
	            // Crear lista con los datos
	            List<Object[]> datos3 = cons3.list();
	            
	            for (Object[] fila3 : datos3) {
	                
	                // Descomponer cada fila
	                int numAlumno = (int) fila3[0];
	                String nombreAlumno = (String) fila3[1];

	                // Realizar la conversión de las notas
	                Double notaEval1 = fila3[2] != null ? ((Number) fila3[2]).doubleValue() : 0.0;
	                Double notaEval2 = fila3[3] != null ? ((Number) fila3[3]).doubleValue() : 0.0;
	                Double notaEval3 = fila3[4] != null ? ((Number) fila3[4]).doubleValue() : 0.0;
	                Double notaMedia = fila3[5] != null ? ((Number) fila3[5]).doubleValue() : 0.0;

	                // Actualizar variables
	                sumaNotasEval1 += notaEval1;
	                sumaNotasEval2 += notaEval2;
	                sumaNotasEval3 += notaEval3;
	                sumaNotasMedias += notaMedia;
	                contadorNotas++;  

	                // Actualizar la nota máxima
	                if (notaMedia > notaMax) {
	                    notaMax = notaMedia;
	                    // Limpiar la lista de ganadores si hay una nueva nota máxima
	                    ganadores.clear();
	                    ganadores.add(nombreAlumno);
	                } else if (notaMedia == notaMax) {
	                    // Si la nota es igual a la máxima, agregar a los ganadores
	                    ganadores.add(nombreAlumno);
	                }

	                // Imprimir los resultados de cada alumno
	                System.out.printf("%-20s %-40s %-20.2f %-20.2f %-20.2f %-20.2f %n", 
	                    "\t" + numAlumno, nombreAlumno, notaEval1, notaEval2, notaEval3, notaMedia);
	            }
	        }
	        
	        // Imprimir resumen
	        System.out.printf("%-20s %-40s %-20s %-20s %-20s %-20s %n", "\t-------------------", "---------------------------------------", "-------------------", "-------------------", "-------------------", "-------------------");
	        System.out.printf("%-20s %-40s %-20.2f %-20.2f %-20.2f %-20.2f %n", "\tNOTAS MEDIAS", "", sumaNotasEval1 / contadorNotas, sumaNotasEval2 / contadorNotas, sumaNotasEval3 / contadorNotas, sumaNotasMedias / contadorNotas);
	        System.out.println("\tAlumno/s con más nota media: " + String.join(", ", ganadores));
	    }

	    // Cerrar sesión
	    session.close();
	}

	@SuppressWarnings("deprecation")
	private static void actualizarDatos() {
        Session session = sesion.openSession();

        try {
            session.beginTransaction();

            // Actualizar NUM_ALUMNOS y NUM_CURSOS de la tabla CENTROS
            Query<Object[]> centrosQuery = session.createQuery(
                "SELECT c.codCentro, SUM(cu.numAlumnos), COUNT(cu.codCurso) " +
                "FROM Centros c JOIN c.cursoses cu " +
                "GROUP BY c.codCentro", Object[].class);

            List<Object[]> centrosResultados = centrosQuery.getResultList();
            for (Object[] row : centrosResultados) {
                int codCentro = (int) row[0];
                long numAlumnos = row[1] != null ? (long) row[1] : 0;
                long numCursos = (long) row[2];

                Centros centro = session.get(Centros.class, codCentro);
                centro.setNumAlumnos((int) numAlumnos);
                centro.setNumCursos((int) numCursos);
                session.update(centro);
            }

            // Actualizar NUM_ALUMNOS de la tabla CURSOS
            Query<Object[]> cursosQuery = session.createQuery(
                "SELECT cu.codCurso, COUNT(a.numAlumno) " +
                "FROM Cursos cu JOIN cu.alumnoses a " +
                "GROUP BY cu.codCurso", Object[].class);

            List<Object[]> cursosResultados = cursosQuery.getResultList();
            for (Object[] row : cursosResultados) {
                int codCurso = (int) row[0];
                long numAlumnos = (long) row[1];

                Cursos curso = session.get(Cursos.class, codCurso);
                curso.setNumAlumnos((int) numAlumnos);
                session.update(curso);
            }

            // Actualizar NUM_ASIG de la tabla DEPARTAMENTOS
            Query<Object[]> departamentosQuery = session.createQuery(
                "SELECT d.codDepar, COUNT(a.codAsig) " +
                "FROM Departamentos d JOIN d.asignaturases a " +
                "GROUP BY d.codDepar", Object[].class);

            List<Object[]> departamentosResultados = departamentosQuery.getResultList();
            for (Object[] row : departamentosResultados) {
                int codDepar = (int) row[0];
                long numAsig = (long) row[1];

                Departamentos departamento = session.get(Departamentos.class, codDepar);
                departamento.setNumAsig((int) numAsig);
                session.update(departamento);
            }

            // Actualizar NOTA_MEDIA de los alumnos
            Query<Object[]> alumnosQuery = session.createQuery(
                "SELECT a.numAlumno, COALESCE(AVG(e.nota), 0) " +
                "FROM Alumnos a LEFT JOIN a.evaluacioneses e " +
                "GROUP BY a.numAlumno", Object[].class);

            List<Object[]> alumnosResultados = alumnosQuery.getResultList();
            for (Object[] row : alumnosResultados) {
                int numAlumno = (int) row[0];
                BigDecimal notaMedia = BigDecimal.valueOf((Double) row[1]);

                Alumnos alumno = session.get(Alumnos.class, numAlumno);
                alumno.setNotaMedia(notaMedia);
                session.update(alumno);
            }

            // Commit de las transacciones
            session.getTransaction().commit();
            System.out.println("Actualizaciones realizadas correctamente.");

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Error durante las actualizaciones: " + e.getMessage());
        } finally {
            session.close();
        }
    }
	
	
	@SuppressWarnings("deprecation")
	private static void insertarEvaluacion(Evaluaciones evaluacion) {
		
		// Abrir sesión de Hibernate
		Session session = sesion.openSession();

		// Extraer datos del objeto Evaluaciones recibido
		EvaluacionesId evaluacionId = evaluacion.getId();
		int numAlumno = evaluacionId.getNumAlumno();
		int codAsig = evaluacionId.getCodAsig();
		int tipoEvaluacion = evaluacionId.getCodEvaluacion();
		BigDecimal nota = evaluacion.getNota();

		// Comprobar el id de la evaluación
		if (tipoEvaluacion < 1 || tipoEvaluacion > 3) {
			System.out.println("Error: El tipo de evaluación debe ser 1, 2 o 3.");
			session.close();
			return;
		}
		
		// Comprobar la nota de la asignatura
		if (nota.doubleValue() < 1 || nota.doubleValue() > 10) {
			System.out.println("Error: La nota debe estar entre 1 y 10.");
			session.close();
			return;
		}
		
		// Comprobar si el alumno existe
		Alumnos alumno = session.get(Alumnos.class, numAlumno);
		if (alumno == null) {
			System.out.println("Error: No existe un alumno con ese ID.");
			session.close();
			return;
		}

		// Comprobar si la asignatura existe
		Asignaturas asignatura = session.get(Asignaturas.class, codAsig);
		if (asignatura == null) {
			System.out.println("Error: No existe una asignatura con ese ID.");
			session.close();
			return;
		}

		// Comprobar que la asignatura pertenece al curso del alumno
		if (!asignatura.getCursos().equals(alumno.getCursos())) {
			System.out.println("Error: La asignatura no pertenece al curso del alumno.");
			session.close();
			return;
		}

		// Comprobar si ya existe una nota puesta en esa asignatura en esa evaluación
		Query<Evaluaciones> queryEvaluacion = session.createQuery(
				"FROM Evaluaciones e WHERE e.id.numAlumno = :alumnoId "
						+ "AND e.id.codAsig = :asignaturaId AND e.id.codEvaluacion = :tipoEvaluacion",
				Evaluaciones.class);
		queryEvaluacion.setParameter("alumnoId", numAlumno);
		queryEvaluacion.setParameter("asignaturaId", codAsig);
		queryEvaluacion.setParameter("tipoEvaluacion", tipoEvaluacion);
		List<Evaluaciones> evaluacionesExistentes = queryEvaluacion.getResultList();

		if (!evaluacionesExistentes.isEmpty()) {
			System.out.println("Error: Ya existe una nota puesta para ese alumno en esa asignatura en es evaluación.");
			session.close();
			return;
		}

		// Asignar relaciones al objeto Evaluaciones
		evaluacion.setAsignaturas(asignatura);
		evaluacion.setAlumnos(alumno);

		// Realizar inserción a la BD
		session.beginTransaction();
		session.save(evaluacion);
		session.getTransaction().commit();

		// Mensaje de éxito
		System.out.println("Evaluación insertada correctamente.");

		// Cerrar sesión
		session.close();
		
	}

	private static void menu() {

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t................................................................");
		System.out.println("\t.  1. EJERCICIO 1: Insertar evaluaciones.");
		System.out.println("\t.  2. EJERCICIO 2: Actualizar contadores.");
		System.out.println("\t.  3. EJERCIICO 3: Mostrar datos de todos los cursos.");
		System.out.println("\t.  4. EJERCICIO 4: Mostrar estadística de centros.");
		System.out.println("\t.  6. Salir");
		System.out.println("\t.................................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");

	}

}
