package examen;

import java.util.Scanner;
import java.sql.*;

public class Principal {

	static Connection conexion = Conexiones.getOracle("EXAMEN", "examen");
	private int codEstudiante;
	private String nombre;
	private String dir;
	private String tlf;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				insertarEstudiante(obtenerCodEstudiante(), "ROBERTO MORANTE GARCÍA", "C/El Colmenar 13. Talavera. ESP",
						"666666666"); //OK
				insertarEstudiante(obtenerCodEstudiante(), "", "",
						""); //DATOS VACÍOS
				insertarEstudiante(obtenerCodEstudiante(), "ALICIA GARCÍA RAMOS", "C/El Colmenar 13. Talavera. ESP",
						"666666666"); //Mismo Nombre
				break;
			case 2:
				listarProyecto(1); //OK
				listarProyecto(5); //Sin datos
				break;
			case 3:
				anadirColumnas(1);
				break;
			case 4:
				System.out.println("Fin.");
				break;
			}
		} while (opcion != 4);
	}

	private static void anadirColumnas(int codProyecto) {
		String crearColumnas = "ALTER TABLE PROYECTOS ADD NUMEMPRE number(5) "
				+ "ADD IMPORTEEMP NUMBER(10) ADD NUMALUM NUMBER(5) ADD GASTOALUM NUMBER(10) ADD GASTORECUR NUMBER(10)";
		String actualizarNumEmple = "UPDATE PROYECTOS SET NUMEMPRE = (SELECT COUNT(*) FROM ENTIDADES JOIN PATROCINA USING "
							+ "(CODENTIDAD) JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO = ?)";
		String actualizarImporteEmp = "UPDATE PROYECTOS SET IMPORTEEMP = (SELECT SUM(IMPORTEAPORTACION) FROM ENTIDADES "
				+ "JOIN PATROCINA USING (CODENTIDAD) JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO =?)";
		String actualizarNumAlum = "UPDATE PROYECTOS SET NUMALUM = (SELECT COUNT(*) FROM ESTUDIANTES JOIN PARTICIPA "
				+ "USING(CODESTUDIANTE) JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO=?)";
		String actualizarGastoAlum = "UPDATE PROYECTOS SET GASTOALUM = (SELECT SUM(NUMAPORTACIONES*EXTRAAPORTACION) "
				+ "FROM PARTICIPA JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO=?)";
		String actualizarGastoRecur = "UPDATE PROYECTOS SET GASTORECUR = (SELECT SUM(CANTIDAD*PVP) FROM RECURSOS "
				+ "JOIN USA USING (CODRECURSO) JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO = ?)";
		
		String consulta = "SELECT * FROM PROYECTOS";
		
		try {
			PreparedStatement sent = conexion.prepareStatement(crearColumnas);
			sent.setInt(1, codProyecto);
			sent.executeUpdate();
			System.out.println("---------------------------------");
			System.out.println("Columnas creada.");
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención ya existe la columna.");
			// System.out.println(e.getMessage());

		}

		try {
			PreparedStatement sent = conexion.prepareStatement(actualizarNumEmple);
			sent.setInt(1, codProyecto);
			int lin = sent.executeUpdate();
			System.out.println("Columnas actualizadas, reg: " + lin);
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}

		try {
			PreparedStatement sent = conexion.prepareStatement(actualizarImporteEmp);
			sent.setInt(1, codProyecto);
			int lin = sent.executeUpdate();
			System.out.println("Columnas actualizadas, reg: " + lin);
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}
		
		try {
			PreparedStatement sent = conexion.prepareStatement(actualizarNumAlum);
			sent.setInt(1, codProyecto);
			int lin = sent.executeUpdate();
			System.out.println("Columnas actualizadas, reg: " + lin);
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}
		
		try {
			PreparedStatement sent = conexion.prepareStatement(actualizarGastoAlum);
			sent.setInt(1, codProyecto);
			int lin = sent.executeUpdate();
			System.out.println("Columnas actualizadas, reg: " + lin);
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}
		
		try {
			PreparedStatement sent = conexion.prepareStatement(actualizarGastoRecur);
			sent.setInt(1, codProyecto);
			int lin = sent.executeUpdate();
			System.out.println("Columnas actualizadas, reg: " + lin);
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}
		
		try {
			PreparedStatement sent = conexion.prepareStatement(consulta);
			ResultSet res = sent.executeQuery();
			
			while(res.next()) {
				System.out.printf("%6s %40s %15s %15s %15s %15s %8s %9s %8s %10s %12s %n","------","----------------------------------------",
						"---------------","---------------","---------------","---------------","---------------","--------","---------","--------","----------","------------");
			}
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}
		
		
	}

	private static void listarProyecto(int codProyecto) {
		String sql = "SELECT * FROM PROYECTOS WHERE CODIGOPROYECTO = ?";
		String nombreProyecto = "";
		Date fechaini = null;
		Date fechafin = null;
		Float presupuesto = 0.f;
		Float extra = 0.f;
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, codProyecto);
			ResultSet res = sentencia.executeQuery();
			while (res.next()) {
				nombreProyecto = res.getString(2);

				fechaini = res.getDate(3);

				fechafin = res.getDate(4);

				presupuesto = res.getFloat(5);

				extra = res.getFloat(6);

			}
			System.out.println("COD-PROYECTO: " + codProyecto + ",     NOMBRE: " + nombreProyecto);
			System.out.println("FECHA INICIO: " + fechaini + ",   FECHA FIN: " + fechafin);
			System.out.println("PRESUPUESTO: " + presupuesto + "    EXTRAAPORTACIÓN: " + extra);
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("LISTA DE ENTRADAS QUE PATROCINA EL PROYECTO");
			
			System.out.printf("%6s %40s %18s %18s %n", "CÓDIGO", "DESCRIPCIÓN", "IMPORTE APORTACIÓN",
					"FECHA APORTACIÓN");
			System.out.printf("%6s %40s %18s %18s %n", "------", "----------------------------------------",
					"------------------", "------------------");
			String sql2 = "SELECT * FROM ENTIDADES JOIN PATROCINA USING "
					+ "(CODENTIDAD) JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO = ?";
			PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
			sentencia2.setInt(1, codProyecto);
			ResultSet res2 = sentencia2.executeQuery();
			float totalImporte = 0;
			while (res2.next()) {
				int codEntidad = res2.getInt(2);
				String desc = res2.getString(3);
				Float importe = res2.getFloat(8);
				Date fecha = res2.getDate(9);
				totalImporte += importe;
				System.out.printf("%6s %40s %18s %18s %n", codEntidad, desc, importe, fecha);
				System.out.printf("%6s %40s %18s %18s %n", "------", "----------------------------------------",
						"------------------", "------------------");
			}
			System.out.println("TOTAL APORTACIONES:                                       " + totalImporte);
			System.out.println(
					"PRESUPUESTO TOTAL:                                        " + (totalImporte + presupuesto));
			String sql3 = "SELECT * FROM ESTUDIANTES JOIN PARTICIPA USING(CODESTUDIANTE) "
					+ "JOIN PROYECTOS USING (CODIGOPROYECTO) WHERE CODIGOPROYECTO=?";
			PreparedStatement sentencia3 = conexion.prepareStatement(sql3);
			sentencia3.setInt(1, codProyecto);
			ResultSet res3 = sentencia3.executeQuery();
			int totNumApt = 0;
			int totAport=0;
			int totalTotAport=0;
			System.out.printf("%6s %30s %40s %6s %15s %6s %10s %n", "CÓDIGO", "NOMBRE", "DIRECCIÓN", 
					"CODPAR", "TIPO APORTACIÓN", "NUMAPT", "TOTAPORT");
			System.out.printf("%6s %30s %40s %6s %15s %6s %10s %n", "------", "------------------------------", "-------------------------------------", 
					"------", "---------------", "------", "----------");
			while (res3.next()) {
				int codAlu = res3.getInt(2);
				String nom = res3.getString(3);
				String dir = res3.getString(4);
				int codPar = res3.getInt(7);
				String tipo = res3.getString(8);
				int numApor = res3.getInt(9);
				totAport = (int) (numApor*extra);
				totNumApt+=numApor;
				totalTotAport+=totAport;
				System.out.printf("%6s %30s %40s %6s %15s %6s %10s %n", codAlu, nom, dir, 
						codPar, tipo, numApor, totAport);
			}
			System.out.printf("%6s %30s %40s %6s %15s %6s %10s %n", "------", "------------------------------", "-------------------------------------", 
					"------", "---------------", "------", "----------");
			System.out.println("TOTALES                                                                                                    "+totNumApt+"        "+totalTotAport);
			res3.close();
			sentencia3.close();
			res2.close();
			sentencia2.close();
			res.close();
			sentencia.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int obtenerCodEstudiante() {
		int codEstudiante = 0;
		String sql = "SELECT MAX (CODESTUDIANTE) FROM ESTUDIANTES";
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet res = sentencia.executeQuery();
			res.next();
			codEstudiante = res.getInt(1);
			codEstudiante = codEstudiante + 1;

			res.close();
			sentencia.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codEstudiante;

	}

	private static void insertarEstudiante(int codEstudiante, String nom, String dir, String tlf) {
		String sql = "SELECT NOMBRE FROM ESTUDIANTES";
		String nomAux;
		boolean entradaValida = true;
		try {
			if (nom.equals("") || dir.equals("") || tlf.equals("")) {
				System.out.println("Los datos no pueden estar vacíos, comprueba datos");
				entradaValida = false;
			}
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet res = sentencia.executeQuery();
			while (res.next()) {
				nomAux = res.getString(1);
				if (nomAux.equals(nom)) {
					System.out.println("Ya existe un estudiante con ese nombre, no se ha podido insertar");
					entradaValida = false;
				}
			}

			if (entradaValida) {
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				String sql2 = "INSERT INTO ESTUDIANTES (CODESTUDIANTE, NOMBRE, DIRECCION, TLF, FECHAALTA) "
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement sentencia2 = conexion.prepareStatement(sql2);

				sentencia2.setInt(1, codEstudiante);
				sentencia2.setString(2, nom);
				sentencia2.setString(3, dir);
				sentencia2.setString(4, tlf);
				sentencia2.setDate(5, sqlDate);
				int filas = sentencia2.executeUpdate();
				System.out.println("Estudiante insertado correctamente con el código: " + codEstudiante);

				sentencia2.close();
			}
			res.close();
			sentencia.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarMenu() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("1. EJERCICIO 1: INSERTAR ESTUDIANTES");
		System.out.println("2. EJERCICIO 2: LISTAR PROYECTO");
		System.out.println("3. EJERCICIO 3: AÑADIR COLUMNAS Y ACTUALIZAR CON DATOS NUEVOS");
		System.out.println("4. Salir");
		System.out.println("-------------------------------------------------------------");
	}
}
