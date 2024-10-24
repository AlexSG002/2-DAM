package jardineria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperacionesEmple {
	public static boolean comprobaremple(Connection conexion, int id) {

		boolean existe = false;
		String sql = "select * from empleados where CODIGOEMPLEADO = ?";

		PreparedStatement sentencia;

		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, id);
			ResultSet resul = sentencia.executeQuery();
			if (resul.next()) {
				existe = true;
			}
			sentencia.close();
			resul.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;

	}
	
	private static boolean comprobarJefe(Connection conexion, int codJefe) {

		boolean existe = false;
		String sql = "select * from empleados where CODIGOJEFE = ?";

		PreparedStatement sentencia;

		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, codJefe);
			ResultSet resul = sentencia.executeQuery();
			if (resul.next()) {
				existe = true;
			}
			sentencia.close();
			resul.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;

	}
	
	public static String insertaremple(Connection conexion, int cod, String nom, String ape1, String ape2, int extension,
			String email, String codOfi, int codJefe, String puesto) {
		String mensaje = "";

		if (!comprobaremple(conexion, cod)) {
			// NO existe, se puede INSERTAR

			String sql = "insert into empleados (CODIGOEMPLEADO ,NOMBRE , APELLIDO1, APELLIDO2, EXTENSION, EMAIL, CODIGOOFICINA, CODIGOJEFE, PUESTO) values(?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setInt(1, cod);
				sentencia.setString(2, nom);
				sentencia.setString(3, ape1);
				sentencia.setString(4, ape2);
				sentencia.setInt(5, extension);
				sentencia.setString(6, email);
				sentencia.setString(7, codOfi);
				sentencia.setInt(8, codJefe);
				sentencia.setString(9, puesto);

				int linea = sentencia.executeUpdate();
				mensaje = "EMPLEADO INSERTADO: " + cod;
				sentencia.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				mensaje = e.getMessage();
			}
		} else {
			// empleado no existe
			mensaje = "EMPLEADO YA EXISTE: " + cod + ", NO SE INSERTA";
		}

		return mensaje;
	}
	
}
