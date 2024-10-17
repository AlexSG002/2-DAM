package pruebaSentencias;

import java.sql.*;

public class Principal {

	public static void main(String[] args) throws SQLException {
		Connection conexion = Conexiones.getMySQL("ejemplo", "root", "");
		System.out.println("PRUEBA INSERTAR DEP EN MYSQL");
		System.out.println(insertardepartamento(conexion, 12, "DEP 12", "TOLEDO"));
		
		conexion.close();
	}

	public static void pruebaCrearVistas(String[] args) {
		Connection conexion = Conexiones.getMySQL("ejemplo", "root", "");
		System.out.println("PRUEBA CREAR VISTA EN MYSQL");
		String vista = "CREATE OR REPLACE VIEW totales (dep, dnombre, nemp, media) AS "
				+ "SELECT d.dept_no, dnombre, COUNT(emp_no), AVG(salario) "
				+ "FROM departamentos d LEFT JOIN empleados e "
				+ "ON e.dept_no = d.dept_no GROUP BY d.dept_no, dnombre";
		crearVista(conexion, vista);

		conexion = Conexiones.getOracle("EJEMPLO", "EJEMPLO");
		System.out.println("PRUEBA CREAR VISTA EN ORACLE");
		crearVista(conexion, vista);
	}

	public static void main2(String[] args) throws SQLException {

		// Subir el salario a los empleados de un dep.
		Connection conexion = Conexiones.getOracle("EJEMPLO", "EJEMPLO");
		System.out.println("PRUEBA SUBIDA EN ORACLE");
		subirsalario(conexion, 100, 10);

		conexion = Conexiones.getMySQL("ejemplo", "root", "");
		System.out.println("PRUEBA SUBIDA EN MYSQL");
		subirsalario(conexion, 100, 10);

		conexion = Conexiones
				.getSQLITE("C:\\Users\\Tarde\\Documents\\2DAM\\ACCESO A DATOS\\UT2\\bdembebidas\\sqlite\\ejemplo.db");
		System.out.println("PRUEBA SUBIDA EN SQLITE");
		subirsalario(conexion, 100, 10);
	}

	public static String insertardepartamento(Connection conexion, int dept, String nom, String loc) {
		String mensaje = "";
		try {

			String sql = "INSERT INTO departamentos VALUES(" + dept + ", '" + nom + "', '" + loc + "')";
			System.out.println("SENTENCIA: "+sql);
			Statement sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(sql);
			mensaje = "Registro Insertado. Filas afectadas: " + filas;
			sentencia.close();

		} catch (SQLException e) {
			mensaje = "Código de error: " + e.getErrorCode() + "\nMensaje de error: " + e.getMessage();

		}

		return mensaje;
	}

	private static void crearVista(Connection conexion, String vista) {
		Statement sentencia;
		System.out.println("Vista a crear");

		try {
			sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(vista);
			System.out.printf("Vista creada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void subirsalario(Connection c, int subida, int dep) throws SQLException {

		String sql = "UPDATE empleados set salario = salario + " + subida + " where dept_no = " + dep;
		System.out.println(sql);

		Statement sentencia = c.createStatement();
		int filas = sentencia.executeUpdate(sql);
		System.out.printf("Empleados modificados: %d %n", filas);
	}

}
