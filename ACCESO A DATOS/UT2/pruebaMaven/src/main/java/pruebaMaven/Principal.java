package pruebaMaven;

import java.sql.*;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Connection conexion = null;

		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Prueba MySQL!");
				try {
					// Cargar el driver
					Class.forName("com.mysql.jdbc.Driver");

					// Establecemos la conexion con la BD
					conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo", "root", "");

					consultarDepar(conexion);
					conexion.close(); // Cerrar conexión

				} catch (ClassNotFoundException cn) {
					cn.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			case 2:
				System.out.println("Prueba Derby!");
				try {
					// Cargar el driver
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					conexion = DriverManager.getConnection(
							"jdbc:derby:C:\\Users\\Tarde\\Documents\\2DAM\\ACCESO A DATOS\\UT2\\bdembebidas\\bdderby\\ejemplo");

					consultarDepar(conexion);
					conexion.close(); // Cerrar conexión

				} catch (ClassNotFoundException cn) {
					cn.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			case 3:
				System.out.println("Prueba HSQLDB!");
				try {
					// Cargar el driver
					Class.forName("org.hsqldb.jdbcDriver");
					conexion = DriverManager.getConnection(
							"jdbc:hsqldb:file:C:\\Users\\Tarde\\Documents\\2DAM\\ACCESO A DATOS\\UT2\\bdembebidas\\bdhsql\\ejemplo");

					consultarDepar(conexion);
					conexion.close(); // Cerrar conexión

				} catch (ClassNotFoundException cn) {
					cn.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Prueba SQlite!");
				try {
					// Cargar el driver
					Class.forName("org.sqlite.JDBC");
					conexion = DriverManager.getConnection(
							"jdbc:sqlite:file:C:\\Users\\Tarde\\Documents\\2DAM\\ACCESO A DATOS\\UT2\\bdembebidas\\sqlite\\ejemplo.db");

					consultarDepar(conexion);
					conexion.close(); // Cerrar conexión

				} catch (ClassNotFoundException cn) {
					cn.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Prueba Oracle!");
				try {
					// Cargar el driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EJEMPLO", "EJEMPLO");

					consultarDepar(conexion);
					conexion.close(); // Cerrar conexión

				} catch (ClassNotFoundException cn) {
					cn.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			case 0:
				System.out.println("Buenas noches");
				break;
			default:
				System.out.println("Seleccione una opción válida!");
				break;
			}
		} while (opcion != 0);

		sc.close();

	}// fin de main

	public static void consultarDepar(Connection conexion) throws SQLException {
		// Preparamos la consulta
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT * FROM departamentos";
		ResultSet resul = sentencia.executeQuery(sql);

		// Recorremos el resultado para visualizar cada fila
		// Se hace un bucle mientras haya registros y se van mostrando

		while (resul.next()) {
			System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
		}

		resul.close(); // Cerrar ResultSet
		sentencia.close(); // Cerrar Statement
	}
	
	
	private static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("  1. MySQL");
		System.out.println("  2. Derby");
		System.out.println("  3. HSQLDB");
		System.out.println("  4. SQLITE");
		System.out.println("  5. ORACLE");
		System.out.println("  0. Salir");
		System.out.println("------------------------------------------------------");
	}
}// fin de la clase
