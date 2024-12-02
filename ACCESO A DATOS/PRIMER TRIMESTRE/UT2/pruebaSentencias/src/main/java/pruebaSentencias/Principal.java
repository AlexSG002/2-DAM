package pruebaSentencias;

import java.sql.*;

public class Principal {

	public static void main(String[] args) {

		// Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		Connection conexion = Conexiones.getOracle("ejemplo25", "ejemplo25");
		// Connection conexion = Conexiones.getSQlite(".\\basedatos\\ejemplo.db");

		if (conexion != null) {

			actividad2_11(conexion, 10); // si existe con empleados
			System.out.println("-----------------------------");
			
			actividad2_11(conexion, 99); // dep no existe
			System.out.println("-----------------------------");

			actividad2_11(conexion, 40); // dep existe pero sin empleados

			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("ERROR EN LA CONEXIÓN. COMPRUEBA..");
		}
	}

	private static void actividad2_11(Connection conexion, int dept_no) {

		// comprobar si el dep existe
		String sql1 = "select dnombre from departamentos where dept_no = ?";

		PreparedStatement sentencia;

		try {
			sentencia = conexion.prepareStatement(sql1);
			sentencia.setInt(1, dept_no);
			ResultSet resul = sentencia.executeQuery();
			if (resul.next()) {
				String nombre = resul.getString(1);
				System.out.println("EMPLEADOS DEL DEPARTAMENTO: " + nombre);

				String sql2 = "select apellido, salario, oficio from empleados  where dept_no = ?";
				PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
				sentencia2.setInt(1, dept_no);
				ResultSet resul2 = sentencia2.executeQuery();
				if (resul2.next()) {
					System.out.println("Departamento con " + nombre);
					System.out.printf("%15s %10s %15s%n", "APELLIDO", "SALARIO", "OFICIO");
					System.out.printf("%15s %10s %15s%n", "---------------", "----------", "---------------");
					// recorrer el result set
					// definir contador y acumulador
					int cuen = 0;
					float suma = 0.f;
					do {
						cuen = cuen + 1;
						suma = suma + resul2.getFloat(2);
						System.out.printf("%15s %10s %15s%n", resul2.getString(1), resul2.getFloat(2),
								resul2.getString(3));

					} while (resul2.next());

					System.out.printf("%15s %10s %15s%n", "---------------", "----------", "---------------");
					// con select
					String sql3 = "select avg(salario), count(emp_no) from empleados  where dept_no = ?";
					PreparedStatement sentencia3 = conexion.prepareStatement(sql3);
					sentencia3.setInt(1, dept_no);
					ResultSet resul3 = sentencia3.executeQuery();
					resul3.next();
					float medi = resul3.getFloat(1);
					int cuenta = resul3.getInt(2);
					System.out.println("SALARIO MEDIO: " + medi);
					System.out.println("Numero empleados: " + cuenta);

					// con contadores
					float medi2 = suma / cuen;
					System.out.println("--SALARIO MEDIO: " + medi2);
					System.out.println("--Numero empleados: " + cuen);

					sentencia2.close();
					sentencia3.close();
					resul2.close();
					resul3.close();

				} else {
					System.out.printf("%15s %10s %15s%n", "---------------", "----------", "---------------");
					System.out.println("    Departamento sin empleado");
					System.out.printf("%15s %10s %15s%n", "---------------", "----------", "---------------");

				}

			} else {
				System.out.println("DEPARTAMENTO NO EXISTE: " + dept_no);
			}
			sentencia.close();
			resul.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void pruebasinsertar() {

		// Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		// Connection conexion = Conexiones.getOracle("ejemplo25", "ejemplo25");
		Connection conexion = Conexiones.getSQlite(".\\basedatos\\ejemplo.db");

		if (conexion != null) {

			System.out.println("PRUEBA verempleadosdep");

			// EMP_NO, APELLIDO, OFICIO, DIR, SALARIO, COMISIÓN, DEPT_NO
			// Error en empleado, dir y dep
			System.out.println(insertarempleado(conexion, 7369, "", "", 222, -1500.0f, 100.0f, 45));
			System.out.println("--------------");
			// Error en empleado,
			System.out.println(insertarempleado(conexion, 7369, null, "INFORMÁTICO", 7499, 1500.0f, 100.0f, 10));
			System.out.println("--------------");

			// Error en dep,
			System.out.println(insertarempleado(conexion, 123, "EMPLE123", null, 7499, 1500.0f, 100.0f, 45));
			System.out.println("--------------");

			// TODO OK
			System.out.println(insertarempleado(conexion, 123, "EMPLE123", "INFORMÁTICO", 7499, 1500.0f, 100.0f, 10));

			System.out.println("--------------");
			// TODO OK
			System.out.println(insertarempleado(conexion, 124, "EMPLE124", "INFORMÁTICO", 7499, 1800.0f, 110.0f, 20));

			System.out.println("--------------");
			// TODO OK
			System.out.println(insertarempleado(conexion, 125, "EMPLE125", "INFORMÁTICO", 7499, 1800.0f, 110.0f, 20));

			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("ERROR EN LA CONEXIÓN. COMPRUEBA..");
		}
	}

	private static String insertarempleado(Connection conexion, int emp_no, String apellido, String oficio, int dir,
			float salario, float comision, int dept_no) {

		String mensaje = "";
		int error = 0; // si ocurre error la ponemos a 1

		if (salario <= 0) {
			error = 1;
			mensaje = mensaje + "EL SALARIO ES NEGATIVO, ERROR NO PUEDE SER NEGATIVO. \n";
		}

		if (apellido == null || apellido.equals("")) {
			error = 1;
			mensaje = mensaje + "EL APELLIDO NO PUEDE SER NULO. \n";
		}

		if (oficio == null || oficio.equals("")) {
			error = 1;
			mensaje = mensaje + "EL OFICIO NO PUEDE SER NULO. \n";
		}

		// comprobar si el empleado existe
		String sql1 = "select count(*) from empleados where emp_no=" + emp_no;
		Statement sentencia;
		try {
			sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery(sql1);
			resul.next();
			int cuenta = resul.getInt(1);
			if (cuenta == 1) {
				// empleado existe, no se inserta
				mensaje = mensaje + "EL NUM DE EMPLEADO YA EXISTE: " + emp_no + ", ERROR AL INSERTAR.\n";
				error = 1;
			}

			// comprobar si el director existe
			sql1 = "select count(*) from empleados where emp_no=" + dir;
			sentencia = conexion.createStatement();
			resul = sentencia.executeQuery(sql1);
			resul.next();
			cuenta = resul.getInt(1);
			if (cuenta == 0) {
				// director no existe.
				mensaje = mensaje + "EL DIRECTOR (" + dir + ") NO EXISTE EN EMPLEADOS, ERROR AL INSERTAR.\n";
				error = 1;
			}

			// comprobar si el dep existe

			sql1 = "select count(*) from departamentos where dept_no=" + dept_no;
			sentencia = conexion.createStatement();
			resul = sentencia.executeQuery(sql1);
			resul.next();
			cuenta = resul.getInt(1);
			if (cuenta == 0) {
				// dept no existe.
				mensaje = mensaje + "EL NUM DE DEPARTAMENTO(" + dept_no + ") NO EXISTE EN LA TABLA DEPARTAMENTOS.\n";
				error = 1;
			}

			// una vez finalizadas las comprobaciones se pregunta si ha habido algún error
			if (error == 1) {
				// NO SE INSERTA
				mensaje = mensaje + "REGISTRO NO INSERTADO";
			} else {
				// A insertar el empleado
				// mensaje = mensaje + "DATOS CORRECTOS. A INSERTAR";
				mensaje = insertaremple(conexion, emp_no, apellido, oficio, dir, salario, comision, dept_no);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensaje;

	}

	private static String insertaremple(Connection conexion, int emp_no, String apellido, String oficio, int dir,
			float salario, float comision, int dept_no) {

		String mensaje = "";
		// cargar fecha
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		System.out.println(utilDate);
		System.out.println(sqlDate);

		String sql = "INSERT INTO empleados(emp_no, apellido, oficio, dir, fecha_alt, salario,"
				+ " comision, dept_no) VALUES (" + emp_no + ",'" + apellido + "', '" + oficio + "'," + dir + ",'"
				+ sqlDate + "', " + salario + ", " + comision + ", " + dept_no + ")";
		System.out.println("sql=>" + sql);

		String sql2 = "INSERT INTO empleados(emp_no, apellido, oficio, dir, fecha_alt, salario,"
				+ " comision, dept_no) VALUES (?, ?, ?,?, ?, ?,?, ?)";

		// Statement sentencia;
		try {
			// sentencia = conexion.createStatement();
			// int filas = sentencia.executeUpdate(sql);
			// System.out.printf("Fila insertada: %d %n", filas);

			PreparedStatement sentencia = conexion.prepareStatement(sql2);

			sentencia.setInt(1, emp_no);
			sentencia.setString(2, apellido);
			sentencia.setString(3, oficio);
			sentencia.setInt(4, dir);
			sentencia.setDate(5, sqlDate);
			sentencia.setFloat(6, salario);
			sentencia.setFloat(7, comision);
			sentencia.setInt(8, dept_no);

			int filas = sentencia.executeUpdate(); // filas afectadas
			mensaje = "Empleado insertado: " + emp_no;
			// conexion.close(); se cierra en main

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// System.out.println("ERROR NO SE INERTAsq
			mensaje = "ERRORR AL INSERTAR: " + e.getMessage();
		}

		return mensaje;

	}

	public static void verempleados() {
		Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		System.out.println("PRUEBA verempleadosdep MYSQL");
		verempleadosdep(conexion, 10);

		conexion = Conexiones.getDerby(".\\basedatos\\bdderby\\ejemplo");
		System.out.println("PRUEBA verempleadosdep DERBY");
		verempleadosdep(conexion, 10);

	}

	public static void pruebainsertar() {

		Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		System.out.println("PRUEBA INSERTAR DEP MYSQL");
		System.out.println(Actividad2_10_insertardepartamento(conexion, 12, "DEP 12", "TOLEDO"));

		conexion = Conexiones.getSQlite(".\\basedatos\\ejemplo.db");
		System.out.println("PRUEBA INSERTAR  SQLITE");
		System.out.println(Actividad2_10_insertardepartamento(conexion, 12, "DEP 12", "TOLEDO"));

		conexion = Conexiones.getOracle("EJEMPLO25", "ejemplo25");
		System.out.println("PRUEBA INSERTAR ORACLE");
		System.out.println(Actividad2_10_insertardepartamento(conexion, 12, "DEP 12", "TOLEDO"));

	}

	public static void verempleadosdep(Connection conexion, int dept) {

		try {

			String sql = "select emp_no, apellido, oficio, salario from empleados where dept_no = " + dept;
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery(sql);
			System.out.println("Datos de los empleados del dep : " + dept);
			while (resul.next()) {
				System.out.println("Emp-no:" + resul.getInt(1) + ". Apellido: " + resul.getString(2) + ". Oficio: "
						+ resul.getString(3) + ". Salario: " + resul.getFloat(4));
			}
			resul.close();
			sentencia.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Código de error: " + e.getErrorCode() + "\nMensaje de error: " + e.getMessage());

		}

	}

	public static void pruebacrearvista() {

		Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		// CREATE or replace
		System.out.println("PRUEBA crear vista MYSQL");
		String vista = "CREATE VIEW totales (dep, dnombre, nemp, media) AS "
				+ " SELECT d.dept_no, dnombre, COUNT(emp_no), " + "  AVG(salario)  "
				+ " FROM departamentos d LEFT JOIN empleados e " + " ON e.dept_no = d.dept_no "
				+ " GROUP BY d.dept_no, dnombre";
		crearvista(conexion, vista);

		conexion = Conexiones.getSQlite(".\\basedatos\\ejemplo.db");
		System.out.println("PRUEBA SUBIDA EN SQLITE");
		crearvista(conexion, vista);

		conexion = Conexiones.getOracle("EJEMPLO25", "ejemplo25");
		System.out.println("PRUEBA VISTA EN ORACLE");
		crearvista(conexion, vista);
	}

	public static String Actividad2_10_insertardepartamento(Connection conexion, int dept, String nom, String loc) {
		String mensaje = "";
		try {

			String sql = "INSERT INTO departamentos VALUES(" + dept + ", '" + nom + "', '" + loc + "')";
			System.out.println("SENTENCIA: " + sql);

			Statement sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(sql);

			mensaje = "Registro Insertado. Filas afectadas: " + filas;
			sentencia.close();
			conexion.close();
		} catch (SQLException e) {

			mensaje = "Código de error: " + e.getErrorCode() + "\nMensaje de error: " + e.getMessage();

		}

		return mensaje;
	}

	public static void pruebaupdate() {

		// Subir el salario a los empleados de un dep
		Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		System.out.println("PRUEBA SUBIDA EN MYSQL");
		subirsalario(conexion, 100, 10);

		conexion = Conexiones.getOracle("EJEMPLO25", "ejemplo25");
		System.out.println("PRUEBA SUBIDA EN ORACLE");
		subirsalario(conexion, 100, 10);

		conexion = Conexiones.getSQlite(".\\basedatos\\ejemplo.db");
		System.out.println("PRUEBA SUBIDA EN SQLITE");
		subirsalario(conexion, 100, 10);
	}

	private static void crearvista(Connection conexion, String vista) {

		Statement sentencia;
		try {
			System.out.println("Vista a crear");
			sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(vista);
			System.out.println("Vista creada");
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void subirsalario(Connection conexion, int subida, int dep) {

		String sql = "UPDATE empleados set salario = salario + " + subida + " where dept_no = " + dep;

		System.out.println(sql);

		Statement sentencia;
		try {

			sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(sql);
			System.out.printf("Empleados modificados: %d %n", filas);
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
