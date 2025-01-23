package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.sql.*;

public class Principal {
	static Connection conexion = Conexiones.getOracle("PROYECTOS", "proyectos");

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				crearBD();
				break;
			case 2:
				listarProyecto(1);
				listarProyecto(100);
				break;
			case 3:
				int codEstudiante = 1;
				int codProyecto = 1;
				String tipoAportacion = "Financiamiento";
				int numAportaciones = 2;
				insertarParticipacion(codEstudiante, codProyecto, tipoAportacion, numAportaciones);
				break;
			case 0:
				System.out.println("Fin.");
				break;
			default:
				System.out.println("Opción no válida. Inténtalo de nuevo.");
			}
		} while (opcion != 0);

		sc.close();
	}

	private static void insertarParticipacion(int codEstudiante, int codProyecto, String tipoAportacion,
			int numAportaciones) {
		ODB odb = null;
		try {
			odb = ODBFactory.open("proyectos.dat");

			boolean proyectoExiste = false;
			boolean estudianteExiste = false;

			Proyectos proyecto = null;
			Estudiantes estudiante = null;

			IQuery queryProyecto = new CriteriaQuery(Proyectos.class, Where.equal("codigoproyecto", codProyecto));
			Objects<Proyectos> proyectos = odb.getObjects(queryProyecto);
			if (!proyectos.isEmpty()) {
				proyecto = proyectos.getFirst();
				proyectoExiste = true;
			}

			IQuery queryEstudiante = new CriteriaQuery(Estudiantes.class, Where.equal("codestudiante", codEstudiante));
			Objects<Estudiantes> estudiantes = odb.getObjects(queryEstudiante);
			if (!estudiantes.isEmpty()) {
				estudiante = estudiantes.getFirst();
				estudianteExiste = true;
			}

			if (!proyectoExiste || !estudianteExiste) {
				if (!proyectoExiste) {
					System.out.println("Error: Proyecto con código " + codProyecto + " no existe.");
				}
				if (!estudianteExiste) {
					System.out.println("Error: Estudiante con código " + codEstudiante + " no existe.");
				}
				System.out.println("No se ha insertado la participación.");
			} else {

				IQuery queryParticipa = new CriteriaQuery(Participa.class);
				Objects<Participa> todasParticipas = odb.getObjects(queryParticipa);
				int maxCodParticipacion = 0;
				for (Participa pa : todasParticipas) {
					if (pa.getCodparticipacion() > maxCodParticipacion) {
						maxCodParticipacion = pa.getCodparticipacion();
					}
				}
				int nuevoCodParticipacion = maxCodParticipacion + 1;

				Participa nuevaParticipacion = new Participa(nuevoCodParticipacion, estudiante, proyecto,
						tipoAportacion, numAportaciones);

				proyecto.getParticipantes().add(nuevaParticipacion);
				estudiante.getParticipaen().add(nuevaParticipacion);

				odb.store(nuevaParticipacion);
				odb.store(proyecto);
				odb.store(estudiante);

				System.out.println("Participación insertada correctamente:");
				System.out.println("Código Participación: " + nuevoCodParticipacion);
				System.out.println("Código Estudiante: " + codEstudiante);
				System.out.println("Código Proyecto: " + codProyecto);
				System.out.println("Tipo de Aportación: " + tipoAportacion);
				System.out.println("Número de Aportaciones: " + numAportaciones);
			}

		} catch (Exception e) {
			System.out.println("Error al insertar la participación: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (odb != null) {
				odb.close();
			}
		}
	}

	private static void listarProyecto(int codProyecto) {
		
		
		float importe = 0;
		float totalImporte = 0;
		int totalNumAportaciones = 0;
		ODB odb = null;
		odb = ODBFactory.open("proyectos.dat");
		
		Objects<Proyectos> proyectos = odb.getObjects(Proyectos.class);
		
		for(Proyectos pComprobar : proyectos) {
			if(pComprobar.getCodigoproyecto()!=codProyecto) {
				System.out.println("El código de proyecto introducido no existe");
			}
			break;
		}
				
		IQuery query = new CriteriaQuery(Proyectos.class, Where.equal("codigoproyecto", codProyecto));

		Objects<Proyectos> objects = odb.getObjects(query);
		for (Proyectos p : objects) {
			System.out.println("-------------------- ------------------------ -----------------------");
			System.out.println("Código proyecto: " + codProyecto + "           Nombre: " + p.getNombre());
			System.out.println("Fecha inicio: " + p.getFechainicio() + "     Fecha fin: " + p.getFechafin());
			System.out.println(
					"Presupuesto: " + p.getPresupuesto() + "        Extraaportación: " + p.getExtraaportacion());
			System.out.println("-------------------- ------------------------ -----------------------");
			System.out.println("Participantes en el proyecto: ");
			System.out.println("------------------------------");
			System.out.println(
					"CODPARTICIPACION CODESTUDIANTE         NOMBREESTUDIANTE         TIPAPORTACION NUMAPORTACIONES IMPORTE");
			System.out.println("Participantes en el proyecto:");
			System.out.println(
					"---------------- ------------- -------------------------------- ------------- --------------- --------");
			for (Participa pa : p.getParticipantes()) {
				importe = pa.getNumaportaciones() * p.getExtraaportacion();
				System.out.printf("%-16d %-18d %-30s %-15s %-11d %-10.2f%n", pa.getCodparticipacion(),
						pa.getEstudiante().getCodestudiante(), pa.getEstudiante().getNombre(),
						pa.getTipoparticipacion(), pa.getNumaportaciones(), importe);
				totalImporte += importe;
				totalNumAportaciones += pa.getNumaportaciones();
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
			System.out.printf("%-82s %-11d %-10.2f%n", "TOTALES:", totalNumAportaciones, totalImporte);
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
		}

		odb.close();

	}

	private static void crearBD() {
		String consultaProyectos = "SELECT * FROM PROYECTOS";
		String consultaParticipa = "SELECT * FROM PARTICIPA WHERE CODIGOPROYECTO = ?";
		String consultaEstudiantes = "SELECT * FROM ESTUDIANTES WHERE CODESTUDIANTE = ?";

		ODB odb = null;

		PreparedStatement sentencia = null;
		PreparedStatement sentencia2 = null;
		PreparedStatement sentencia3 = null;

		try {
			odb = ODBFactory.open("proyectos.dat");

			sentencia = conexion.prepareStatement(consultaProyectos);
			ResultSet resProyectos = sentencia.executeQuery();
			while (resProyectos.next()) {
				int codProyecto = resProyectos.getInt("CODIGOPROYECTO");
				String nombreProyecto = resProyectos.getString("NOMBRE");
				Date fechaIni = resProyectos.getDate("FECHAINICIO");
				Date fechaFin = resProyectos.getDate("FECHAFIN");
				float presupuesto = resProyectos.getFloat("PRESUPUESTO");
				float extraAportacion = resProyectos.getFloat("EXTRAAPORTACION");

				List<Participa> participantes = new ArrayList<>();

				Proyectos proyecto = new Proyectos(codProyecto, nombreProyecto, fechaIni, fechaFin, presupuesto,
						extraAportacion, participantes);

				sentencia2 = conexion.prepareStatement(consultaParticipa);
				sentencia2.setInt(1, codProyecto);
				ResultSet resParticipa = sentencia2.executeQuery();
				while (resParticipa.next()) {
					int codParticipacion = resParticipa.getInt("CODPARTICIPACION");
					String tipoParticipacion = resParticipa.getString("TIPOPARTICIPACION");
					int numAportaciones = resParticipa.getInt("NUMAPORTACIONES");
					int codEstudiante = resParticipa.getInt("CODESTUDIANTE");

					sentencia3 = conexion.prepareStatement(consultaEstudiantes);
					sentencia3.setInt(1, codEstudiante);
					ResultSet resEstudiante = sentencia3.executeQuery();

					Estudiantes estudiante = null;
					if (resEstudiante.next()) {
						int codEst = resEstudiante.getInt("CODESTUDIANTE");
						String nombre = resEstudiante.getString("NOMBRE");
						String direccion = resEstudiante.getString("DIRECCION");
						String tlf = resEstudiante.getString("TLF");
						Date fechaAlta = resEstudiante.getDate("FECHAALTA");

						List<Participa> participaen = new ArrayList<>();
						estudiante = new Estudiantes(codEst, nombre, direccion, tlf, fechaAlta, participaen);

						odb.store(estudiante);
					}

					resEstudiante.close();
					sentencia3.close();

					resEstudiante.close();
					sentencia3.close();

					Participa p = new Participa(codParticipacion, estudiante, proyecto, tipoParticipacion,
							numAportaciones);

					proyecto.getParticipantes().add(p);
					estudiante.getParticipaen().add(p);

					odb.store(p);
				}

				resParticipa.close();
				sentencia2.close();

				odb.store(proyecto);
			}

			resProyectos.close();
			sentencia.close();
			odb.close();
			conexion.close();
			System.out.println("Base de datos creada y objetos almacenados correctamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void mostrarMenu() {
		System.out.println("OPERACIONES PROYECTOS");
		System.out.println("1. Crear BD.");
		System.out.println("2. Listar un proyecto.");
		System.out.println("3. Insertar una participación.");
		System.out.println("0. Salir.");
	}
}
