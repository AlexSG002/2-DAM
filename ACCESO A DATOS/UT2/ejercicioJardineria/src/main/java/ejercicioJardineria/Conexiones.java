package ejercicioJardineria;


import java.sql.*;
public class Conexiones {

	public static Connection getMySQL(String bd, String usuario, String pass) {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd, usuario, pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
		}
		return conexion;
	}
	
	
	public static Connection getOracle(String usuario, String pass) {
		Connection conexion = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
		}
		return conexion;
	}
	
	public static Connection getDerby(String bd) {
		Connection conexion = null;
		
		//modelo de bd C:\\Users\\Tarde\\Documents\\2DAM\\ACCESO A DATOS\\UT2\\bdembebidas\\bdderby\\ejemplo"
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conexion = DriverManager.getConnection(
					"jdbc:derby:"+bd);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
		}
		return conexion;
	}
	
	public static Connection getSQLITE(String bd) {
		Connection conexion = null;
		
		//modelo de bd C:\\Users\\Tarde\\Documents\\2DAM\\ACCESO A DATOS\\UT2\\bdembebidas\\sqlite\\ejemplo.db
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(
					"jdbc:sqlite:file:"+bd);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
		}
		return conexion;
	}
}
