package pruebaMaven;

import java.sql.*;

import java.util.Scanner;

public class Databasemetadata {
	static String consulta = "SELECT * FROM empleados join departamentos using (dept_no)";

	public static void main(String[] args) {

		try {
			Connection conexion = null;
			Scanner sc = new Scanner(System.in);
			int opcion = 0;

			do {
				mostrarMenu();
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					// Mysql
					System.out.println("Prueba Mysql!");
					Class.forName("com.mysql.jdbc.Driver");
					conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/ejemplo25", "root", "");
					// catalogo, esquema, patronDeTabla, tipos[]
					vermetadatos(conexion, "ejemplo25", null, null, null);

					verresulsetmetadata(conexion);

					conexion.close();

					break;
				case 2:
					System.out.println("Prueba Derby!");
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					conexion = DriverManager.getConnection("jdbc:derby:.\\basedatos\\bdderby\\ejemplo");

					// catalogo, esquema, patronDeTabla, tipos[]
					vermetadatos(conexion, null, "APP", null, null);
					verresulsetmetadata(conexion);
					conexion.close();
					break;
				case 3:
					System.out.println("Prueba HSQLDB!");
					Class.forName("org.hsqldb.jdbcDriver");
					// Class.forName("org.hsqldb.JDBCDriver");
					// Class.forName("org.hsqldb.jdbc.JDBCDriver");
					// org.hsqldb.jdbc.JDBCDriver
					// conexion =
					// DriverManager.getConnection("jdbc:hsqldb:file:.\\hsqldb\\ejemplo\\ejemplo");
					conexion = DriverManager.getConnection("jdbc:hsqldb:.\\hsqldb\\ejemplo\\ejemplo");
					vermetadatos(conexion, "PUBLIC", "PUBLIC", null, null);
					verresulsetmetadata(conexion);
					conexion.close();
					break;
				case 4:
					System.out.println("Prueba SQLITE!");
					Class.forName("org.sqlite.JDBC");
					conexion = DriverManager.getConnection("jdbc:sqlite:.\\basedatos\\ejemplo.db");
					vermetadatos(conexion, null, null, null, null);
					verresulsetmetadata(conexion);
					conexion.close();
					break;

				case 5:
					System.out.println("Prueba Oracle!");
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo25",
							"ejemplo25");
					vermetadatos(conexion, null, "EJEMPLO25", null, null);
					verresulsetmetadata(conexion);
					conexion.close();
					break;

				case 6:
					System.out.println("Prueba MariaDB!");
					Class.forName("org.mariadb.jdbc.Driver");
					conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ejemplo", "root", "");

					vermetadatos(conexion, "ejemplo", null, null, null);
					verresulsetmetadata(conexion);
					conexion.close();
					break;

				case 0:
					System.out.println("FIN DE MENÚ!");
					break;
				default:
					System.out.println("Seleccione una opción válida!");
					break;
				}
			} while (opcion != 0);

			sc.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void verresulsetmetadata(Connection conexion) {

		Statement sentencia;
		try {
			System.out.println("----------------------");
			System.out.println("METADATOS DE LA CONSULTA: "+ consulta);
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(consulta);

			ResultSetMetaData rsmd = rs.getMetaData();

			int nColumnas = rsmd.getColumnCount();
			String nula;
			System.out.printf("Número de columnas recuperadas: %d%n", nColumnas);
			for (int i = 1; i <= nColumnas; i++) {
				System.out.printf("  Columna %d: %n ", i);
				System.out.printf("  Nombre: %s %n   Tipo: %s %n ", rsmd.getColumnName(i), rsmd.getColumnTypeName(i));

				if (rsmd.isNullable(i) == 0)
					nula = "NO";
				else
					nula = "SI";

				System.out.printf("  Puede ser nula?: %s %n ", nula);
				System.out.printf("  Máximo ancho de la columna: %d %n", rsmd.getColumnDisplaySize(i));
				System.out.printf("  Nombre de la tabla: %s %n",rsmd.getTableName(i));
					} // for

			sentencia.close();
			rs.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void vermetadatos(Connection conexion, String catalogo, String esquema, String patronDeTabla,
			String tipos[]) {

		DatabaseMetaData dbmd;
		try {
			dbmd = conexion.getMetaData();

			ResultSet resul = null;

			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();

			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
			System.out.println("===================================");
			System.out.printf("Nombre : %s %n", nombre);
			System.out.printf("Driver : %s %n", driver);
			System.out.printf("URL    : %s %n", url);
			System.out.printf("Usuario: %s %n", usuario);
			System.out.printf("MajorVersion: %s %n", dbmd.getDatabaseMajorVersion());

			// Obtener información de las tablas y vistas que hay

			resul = dbmd.getTables(catalogo, esquema, patronDeTabla, tipos);
			// getTables(catalogo, esquema, patronDeTabla, tipos[])

			System.out.println("INFORMACIÓN getTables():");
			System.out.println("========================");
			while (resul.next()) {
				String catalogo2 = resul.getString(1);// columna 1
				String esquema2 = resul.getString(2); // columna 2
				String tabla = resul.getString(3); // columna 3
				String tipo = resul.getString(4); // columna 4
				// SELF_REFERENCING_COL_NAME, colum 9
				// String ref = resul.getString(9); // columna 9
				System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo2, esquema2, tabla);

				// mostrar las columnas de la tabla
				System.out.println("    COLUMNAS TABLA: " + tabla);
				System.out.println("    ===================================");
				ResultSet columnas = dbmd.getColumns(catalogo, esquema, tabla, null);
				while (columnas.next()) {
					String nombCol = columnas.getString("COLUMN_NAME"); // getString(4)
					String tipoCol = columnas.getString("TYPE_NAME"); // getString(6)
					String tamCol = columnas.getString("COLUMN_SIZE"); // getString(7)
					String nula = columnas.getString("IS_NULLABLE"); // getString(18)
					System.out.printf("    Columna: %s, Tipo: %s, Tamaño: %s ," + " ¿Puede ser Nula:? %s %n", nombCol,
							tipoCol, tamCol, nula);
				}

				columnas.close();

				if (tipo.equals("TABLE")) {
					System.out.println("    Clave primaria de: " + tabla);
					System.out.println("    ==============================");
					ResultSet pk = dbmd.getPrimaryKeys(catalogo, esquema, tabla);
					String pkDep = "", separador = "";
					while (pk.next()) {
						pkDep = pkDep + separador + pk.getString("COLUMN_NAME");// getString(4)
						separador = "+";
					}
					System.out.println("    Clave Primaria: " + pkDep);

					pk.close();

					System.out.println("    Claves exportadas de: " + tabla);
					System.out.println("    ================================");

					ResultSet fk = dbmd.getExportedKeys(catalogo, esquema, tabla);
					while (fk.next()) {
						String fk_name = fk.getString("FKCOLUMN_NAME");
						String pk_name = fk.getString("PKCOLUMN_NAME");
						String pk_tablename = fk.getString("PKTABLE_NAME");
						String fk_tablename = fk.getString("FKTABLE_NAME");
						System.out.printf("    Tabla PK: %s, Clave Primaria: %s %n", pk_tablename, pk_name);
						System.out.printf("    Tabla FK: %s, Clave Ajena: %s %n", fk_tablename, fk_name);
					}
					fk.close();

				}

			}

			resul.close();
			//conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("OPERACIONES DATABASEMETADATA");
		System.out.println("  1. Prueba mysql.");
		System.out.println("  2. Prueba Derby.");
		System.out.println("  3. Prueba HSQLDB.");
		System.out.println("  4. Prueba Sqlite.");
		System.out.println("  5. Prueba Oracle.");
		System.out.println("  6. Prueba MariaDB.");
		System.out.println("  0. Salir");
		System.out.println("------------------------------------------------------");
	}

}
