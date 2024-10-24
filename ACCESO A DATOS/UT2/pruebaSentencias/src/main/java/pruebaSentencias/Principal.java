package pruebaSentencias;

import java.sql.*;

public class Principal {

	public static void main(String[] args) {
		Connection conexion = Conexiones.getOracle("EJEMPLO", "EJEMPLO");
		if(conexion!=null) {
			
			System.out.println(actividad2_11(conexion, 10)); //Si existe con empleados
			
			System.out.println(actividad2_11(conexion, 99)); //Dep no existe
			
			System.out.println(actividad2_11(conexion, 40)); //Dep existe, pero sin emples
				
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("ERROR. NO HAY CONEXIÓN CON LA BDD. COMPRUÉBALO");
		}
	}
	
	private static String actividad2_11(Connection conexion, int dept_no) {
	    String mensaje = "";

	    String sql1 = "SELECT COUNT(*) FROM DEPARTAMENTOS WHERE dept_no = ?";
	    try {
	       
	        PreparedStatement sentencia = conexion.prepareStatement(sql1);
	        sentencia.setInt(1, dept_no);
	        ResultSet resul = sentencia.executeQuery();
	        resul.next();
	        int cuenta = resul.getInt(1);
	        int error = 0;

	        if (cuenta == 0) {
	            mensaje = "ERROR. EL DEPARTAMENTO NO EXISTE";
	            error = 1;
	        }

	        
	        if (error == 0) { 
	            String sql2 = "SELECT COUNT(*) FROM EMPLEADOS WHERE DEPT_NO = ?";
	            PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
	            sentencia2.setInt(1, dept_no);
	            ResultSet resul2 = sentencia2.executeQuery();
	            resul2.next();
	            cuenta = resul2.getInt(1);

	            if (cuenta == 0) {
	                mensaje = "ERROR. EL DEPARTAMENTO NO TIENE EMPLEADOS";
	                error = 1;
	            }

	            resul2.close();
	            sentencia2.close();
	        }

	        
	        if (error == 0) {
	            String sql3 = "SELECT dnombre FROM DEPARTAMENTOS WHERE dept_no = ?";
	            PreparedStatement sentencia3 = conexion.prepareStatement(sql3);
	            sentencia3.setInt(1, dept_no);
	            ResultSet resul3 = sentencia3.executeQuery();
	            resul3.next();
	            String nombreDep = resul3.getString(1);
	            mensaje = "EMPLEADOS DEL DEPARTAMENTO: " + nombreDep;

	            resul3.close();
	            sentencia3.close();
	        }

	        resul.close();
	        sentencia.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        mensaje = "ERROR AL EJECUTAR LA CONSULTA: " + e.getMessage();
	    }

	    return mensaje;
	}


	public static void pruebasInsertar() {
		Connection conexion = Conexiones.getOracle("EJEMPLO", "EJEMPLO");

		if (conexion != null) {

			System.out.println("PRUEBA INSERTAR EMPLEADO EN MYSQL");
			// ERROR EN EMPLEADO, DIRECTOR Y DEP
			System.out.println(insertarempleado(conexion, 7369, "", "", 222, -1500.0f, 100.0f, 45));
			System.out.println("-------------------------------");
			// ERROR EN EMPLEADO
			System.out.println(insertarempleado(conexion, 7369, null, "INFORMÁTICO", 7499, 1500.0f, 100.0f, 10));
			System.out.println("-------------------------------");
			// ERROR EN DIRECTOR
			System.out.println(insertarempleado(conexion, 123, "EMPLE123", "INFORMÁTICO", 222, 1500.0f, 100.0f, 10));
			System.out.println("-------------------------------");
			// ERROR EN DEP
			System.out.println(insertarempleado(conexion, 123, "EMPLE123", null, 7499, 1500.0f, 100.0f, 45));
			System.out.println("-------------------------------");
			// EMPLEADO YA INSERTADO
			System.out.println(insertarempleado(conexion, 123, "EMPLE123", "EMPLEADO", 7499, 1500.0f, 100.0f, 10));
			System.out.println("-------------------------------");
			System.out.println(insertarempleado(conexion, 124, "EMPLE124", "EMPLEADO", 7499, 1500.0f, 100.0f, 10));

		} else {
			System.out.println("ERROR. NO HAY CONEXIÓN CON LA BDD. COMPRUÉBALO");
		}
		
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String insertaremple(Connection conexion, int emp_no, String apellido, String oficio, int dir,
			float salario, float comision, int dep) {

		String mensaje = "";
		// Cargar fecha

		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		System.out.println(utilDate);
		System.out.println(sqlDate);
		
		String sql = "INSERT INTO EMPLEADOS (emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no) "
				+ "VALUES ("+emp_no+", '"+apellido+"', '"+oficio+"', "+dir+", '"+sqlDate+"', "+salario+", "+comision+", "+dep+")";
		
		System.out.println(sql);
		
		String sql2 = "INSERT INTO EMPLEADOS (emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			//Statement sentencia = conexion.createStatement();
			//int filas = sentencia.executeUpdate(sql);
			
			PreparedStatement sentencia = conexion.prepareStatement(sql2);
			
			sentencia.setInt(1, emp_no);
			sentencia.setString(2, apellido);
			sentencia.setString(3, oficio);
			sentencia.setInt(4, dir);
			sentencia.setDate(5, sqlDate);
			sentencia.setFloat(6, salario);
			sentencia.setFloat(7, comision);
			sentencia.setInt(8, dep);
			
			int filas = sentencia.executeUpdate();
			
			//System.out.printf("Fila insertada: %d %n", filas);
			mensaje = "Empleado insertado: "+emp_no;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje = "ERROR AL INSERTAR: "+ e.getMessage();
		}
		
		

		return mensaje;
	}

	private static String insertarempleado(Connection conexion, int emp_no, String apellido, String oficio, int dir,
			float salario, float comision, int dep) {
		String mensaje = "";
		int error = 0; // Si ocurre algún error la ponemos a 1

		if (salario <= 0) {
			error = 1;
			mensaje = mensaje + "EL SALARIO ES NEGATIVO, ERROR NO PUEDE SER NEGATIVO.\n";
		}

		if (apellido == null || apellido.equals("")) {
			error = 1;
			mensaje = mensaje + "EL APELLIDO NO PUEDE SER NULO. \n";
		}

		if (oficio == null || oficio.equals("")) {
			error = 1;
			mensaje = mensaje + "EL OFICIO NO PUEDE SER NULO. \n";
		}

		// Comprobar si el empleado existe
		String sql1 = "SELECT COUNT(*) FROM EMPLEADOS WHERE emp_no=" + emp_no;
		Statement sentencia;
		try {
			sentencia = conexion.createStatement();

			ResultSet resul = sentencia.executeQuery(sql1);
			resul.next();
			int cuenta = resul.getInt(1);
			if (cuenta == 1) {
				// Empleado existe
				mensaje = mensaje + "EL NUM DE EMPLEADO YA EXISTE: " + emp_no + ", ERROR AL INSERTAR.\n";
				error = 1;
			}
			// Comprobar si el director existe
			sql1 = "SELECT COUNT(*) FROM EMPLEADOS WHERE emp_no=" + dir;
			sentencia = conexion.createStatement();
			resul = sentencia.executeQuery(sql1);
			resul.next();
			cuenta = resul.getInt(1);
			if (cuenta == 0) {
				// Director no existe
				mensaje = mensaje + "EL DIRECTOR (" + dir + ") NO EXISTE EN EMPLEADOS, ERROR AL INSERTAR.\n";
				error = 1;
			}
			// Comprobar si el departamento existe
			sql1 = "SELECT COUNT(*) FROM DEPARTAMENTOS WHERE dept_no=" + dep;
			sentencia = conexion.createStatement();
			resul = sentencia.executeQuery(sql1);
			resul.next();
			cuenta = resul.getInt(1);
			if (cuenta == 0) {
				// Departamento no existe
				mensaje = mensaje + "EL DEPARTAMENTO (" + dep + ") NO EXISTE EN DEPARTAMENTOS, ERROR AL INSERTAR.\n";
				error = 1;
			}

			// Una vez finalizadas las comprobaciones se pregunta si ha habido algún error
			if (error == 1) {
				// NO SE INSERTA
				mensaje = mensaje + "REGISTRO NO INSERTADO";
			} else {
				// Procede inserción
				// mensaje = mensaje + "DATOS CORRECTOS. PROCEDE INSERCIÓN";
				mensaje = insertaremple(conexion, emp_no, apellido, oficio, dir, salario, comision, dep);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}

	public static void verEmpleados(String[] args) throws SQLException {
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
			System.out.println("SENTENCIA: " + sql);
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
