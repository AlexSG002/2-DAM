package pruebaProcedimientos;



import java.sql.*;

public class Conexiones {

	public static Connection getMysql(String bd, String usuario, String pass) {

		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + bd, usuario, pass);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ERROR DRIVER: "+e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ERROR SQLException: "+e.getMessage());
		}
		return conexion;

	}

	public static Connection getOracle(String usuario, String pass) {

		Connection conexion = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, pass);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;

	}

	
}
