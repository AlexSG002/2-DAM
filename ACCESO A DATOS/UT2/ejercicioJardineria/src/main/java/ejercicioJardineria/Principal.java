package ejercicioJardineria;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	// static Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
	static Connection conexion = Conexiones.getOracle("JARDINERIA", "JARDINERIA");

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		do {
			mostrarMenu();

			try {
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				opcion = 99;
				sc.next();
			}
			switch (opcion) {
			case 1:
				// nombre, apellido1, apellido2, extensión, email, codigooficina,
				// codigojefe, y puesto
				insertarempleado("Nombre1", "apellido1", "apellido2", "Ext 1", "nombre1@correo.es", "ofi1", 9999,
						"Puesto1");
				// OK
				insertarempleado("Nombre1", "apellido1", "apellido2", "Ext 1", "nombre1@correo.es", "TAL-ES", 1,
						"Puesto1");

				break;
			case 2:
				verpedidoscliente(2); // no existe

				verpedidoscliente(6); // 8 no tiene pedidos

				verpedidoscliente(1); // 9, 3, 23, OK

				verpedidoscliente(4); // 9, 3, 23, OK

				break;
			case 3:

				crearclientessinpedido();

				break;
			case 4:
				clientesporempleado();
				break;

			case 5:
				stockactualizado();
				break;

			case 6:
				ejeciciooficinas("BCN-ES"); // existe y tienen empleados
				ejeciciooficinas("oooooo"); // oficina no existe
				ejeciciooficinas("NUEVA"); // oficina existe sin empleados
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
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejeciciooficinas(String codofi) {
		String llamad = "{? = call veroficina(?,?,?,?,?)}";
		System.out.printf("12%s %-30s %-50s %-50s %-50s %10s %n, COD OFI, CIUDAD, PAIS, REGION, DIRECC1, NUM EMPLE");
		System.out.printf("12%s %-30s %-50s %-50s %-50s %10s %n, -------------, ---------------------------------------,"
				+ " ---------------------------------------,"
				+ " ---------------------------------------, ---------------------------------------, -----------");

		try {
			CallableStatement llamada = conexion.prepareCall(llamad);
			llamada.registerOutParameter(1, Types.INTEGER); // Contador
			llamada.setString(2, codofi); // Oficina
			llamada.registerOutParameter(3, Types.VARCHAR); // Ciudad
			llamada.registerOutParameter(4, Types.VARCHAR); // pais
			llamada.registerOutParameter(5, Types.VARCHAR); // región
			llamada.registerOutParameter(6, Types.VARCHAR); // direccion

			llamada.executeUpdate();

			System.out.printf("12%s %-30s %-50s %-50s %-50s %10s %n", codofi, llamada.getString(3), llamada.getString(4), llamada.getString(5), llamada.getString(6), llamada.getInt(1));
			llamada.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void stockactualizado() {
		String crearcolumna = "ALTER TABLE productos ADD STOCKACTUALIZADO number(5)";
		String actualiza = "update productos p set STOCKACTUALIZADO = "
				+ "CANTIDADENSTOCK - (select coalesce(sum(cantidad),0) from detallepedidos where codigoproducto = p.codigoproducto)";
		String consulta = "select codigoproducto, cantidadenstock , STOCKACTUALIZADO "
				+ " from productos where STOCKACTUALIZADO2< 5 ";

		try {
			PreparedStatement sent = conexion.prepareStatement(crearcolumna);
			sent.executeUpdate();
			System.out.println("---------------------------------");
			System.out.println("Columna creada.");
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención ya existe la columna.");
			// System.out.println(e.getMessage());

		}

		try {
			PreparedStatement sent = conexion.prepareStatement(actualiza);
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
			// LISTADO
			PreparedStatement sent = conexion.prepareStatement(consulta);
			ResultSet res = sent.executeQuery();
			System.out.printf("%15s %15s %16s%n", "COD PRODUCTO", "CANTIDADENSTOCK", "STOCKACTUALIZADO");
			System.out.printf("%15s %15s %16s%n", "---------------", "---------------", "----------------");
			while (res.next()) {
				System.out.printf("%15s %15s %16s%n", res.getString(1), res.getInt(2), res.getInt(3));

			}

			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}

	}

	private static void clientesporempleado() {
		String crearcolumna = "ALTER TABLE empleados ADD NUMCLIENTES number(5)";
		String actualiza = "update empleados emple set NUMCLIENTES = "
				+ " ( select count(*) from clientes where codigoempleadorepventas = emple.codigoempleado)";

		try {
			PreparedStatement sent = conexion.prepareStatement(crearcolumna);
			sent.executeUpdate();
			System.out.println("---------------------------------");
			System.out.println("Columna creada.");

			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención ya existe la columna.");
			// System.out.println(e.getMessage());

		}

		try {
			PreparedStatement sent = conexion.prepareStatement(actualiza);
			int lin = sent.executeUpdate();
			System.out.println("Columnas actualizadas, reg: " + lin);

			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Atención error en la actualización: ");
			System.out.println(e.getMessage());

		}

	}

	private static void crearclientessinpedido() {
		// crear tabla sin pedidos y con los clientes
		// create table clientessinpedido as
		// select * from clientes where codigocliente
		// not in ( select codigocliente from pedidos );

		// Añadir la PK a la tabla
		// ALTER TABLE clientessinpedido ADD CONSTRAINT AAA_PK PRIMARY KEY (
		// CODIGOCLIENTE );

		// borrar de clientes
		// delete from clientes where codigocliente
		// not in ( select codigocliente from pedidos );
		String crear = " create table clientessinpedido as select * from clientes where codigocliente "
				+ " not in ( select codigocliente  from pedidos ) order by codigocliente";

		String alter = "ALTER TABLE clientessinpedido ADD CONSTRAINT CSP_PK PRIMARY KEY ( CODIGOCLIENTE )";

		String borrar = " delete from clientes where codigocliente not in ( select codigocliente  from pedidos )";

		try {
			PreparedStatement sent = conexion.prepareStatement(crear);
			sent.executeUpdate();
			System.out.println("---------------------------------");
			System.out.println("Tabla clientessinpedido creada con los registros");
			// añadimos la PK
			sent = conexion.prepareStatement(alter);
			sent.executeUpdate();
			System.out.println("Añadida la PK en clientessinpedido");

			// borrar esos clientes
			sent = conexion.prepareStatement(borrar);
			int l = sent.executeUpdate();
			System.out.println("Clientes sin pedido, borrados de clientes: " + l);

			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Tabla CLIENTESSINPEDIDO ya creada, y clientes sin pedidos borrados de CLIENTES.");
			// System.out.println(e.getMessage());

		}

	}

	private static void verpedidoscliente(int codigocliente) {

		String sql1 = "select nombrecliente, lineadireccion1 from clientes where codigocliente = ?";
		String sql2 = "select count(*) from pedidos where codigocliente = ?";

		try {
			PreparedStatement sent = conexion.prepareStatement(sql1);
			sent.setInt(1, codigocliente);
			ResultSet res = sent.executeQuery();
			if (res.next()) {
				// cliente existe, contamos pedidos
				PreparedStatement sent2 = conexion.prepareStatement(sql2);
				sent2.setInt(1, codigocliente);
				ResultSet re2 = sent2.executeQuery();
				re2.next();
				// Visualizamos
				System.out.println("COD-CLIENTE: " + codigocliente + ".           NOMBRE: " + res.getString(1));
				System.out.println("DIRECCIÓN1: " + res.getString(2) + ".     Número de pedidos: " + re2.getInt(1));
				System.out.println("---------------------------------------------------------------");

				if (re2.getInt(1) > 0) {
					// Ir al método para visualizar los pedidos
					listadopedidoscliente(codigocliente);
				}

				sent.close();
				sent2.close();
				res.close();
				re2.close();

			} else {
				System.out.println("----------------------------------");
				System.out.println("Codigo de cliente no existe: " + codigocliente);
				System.out.println("----------------------------------");

			}
			res.close();
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void listadopedidoscliente(int codigocliente) {
		// COD-PEDIDO: xxxxxxx FECHA PEDIDO: xxxxxxxxxxx ESTADO DEL PEDIDO: xxxxxxxxxxx
		String sql1 = "SELECT codigopedido,fechapedido,estado FROM pedidos where codigocliente = ? order by codigopedido";
		try {
			PreparedStatement sent = conexion.prepareStatement(sql1);
			sent.setInt(1, codigocliente);
			ResultSet res = sent.executeQuery();

			float importemax = 0;
			int pedidomax = 0;
			Date fechamax = null;
			int cantidadmaxima = 0;
			String nombremax = "", codmax = "";

			while (res.next()) {

				System.out.println("   COD-PEDIDO: " + res.getInt(1) + "  FECHA PEDIDO: " + res.getDate(2)
						+ "   ESTADO DEL PEDIDO: " + res.getString(3));

				System.out.printf("      %9s  %9s %-40s %10s %10s %10s %n", "NUM-LINEA", "COD-PROD", "NOMBRE PRODUCTO",
						"CANTIDAD", "PREC-UNID", "IMPORTE");
				System.out.printf("      %9s  %9s %-40s %10s %10s %10s %n", "---------", "---------",
						"----------------------------------------", "----------", "----------", "----------");

				// detalle del pedido
				String sql2 = "SELECT  numerolinea ,  codigoproducto, nombre, cantidad, preciounidad, cantidad*preciounidad"
						+ " FROM  detallepedidos join productos using(codigoproducto) "
						+ " where codigopedido = ? order by numerolinea ";

				int sumacantidad = 0;
				float sumaprecio = 0, sumaimporte = 0;

				PreparedStatement sent2 = conexion.prepareStatement(sql2);
				sent2.setInt(1, res.getInt(1));
				ResultSet res2 = sent2.executeQuery();
				while (res2.next()) {
					System.out.printf("      %9s  %9s %-40s %10s %10s %10s %n", res2.getInt(1), res2.getString(2),
							res2.getString(3), res2.getInt(4), res2.getFloat(5), res2.getFloat(6));
					sumacantidad = sumacantidad + res2.getInt(4);
					sumaprecio = sumaprecio + res2.getFloat(5);
					sumaimporte = sumaimporte + res2.getFloat(6);

					if (res2.getInt(4) > cantidadmaxima) {
						cantidadmaxima = res2.getInt(4);
						nombremax = res2.getString(3);
						codmax = res2.getString(2);
					}

				}
				System.out.printf("      %9s  %9s %-40s %10s %10s %10s %n", "---------", "---------",
						"----------------------------------------", "----------", "----------", "----------");
				// TOTALES POR PEDIDO
				System.out.printf("      %61s %10s %10s %10s %n", "TOTALES POR PEDIDO", sumacantidad, sumaprecio,
						sumaimporte);
				System.out.println();

				if (sumaimporte > importemax) {
					pedidomax = res.getInt(1);
					fechamax = res.getDate(2);
					importemax = sumaimporte;
				}

				res2.close();
				sent2.close();

			} // fin pedidos

			System.out.printf("      %9s  %9s %-40s %10s %10s %10s %n", "---------", "---------",
					"----------------------------------------", "----------", "----------", "----------");

			System.out.println("COD de PEDIDO y FECHA PEDIDO CON TOTAL IMPORTE MÁXIMO: " + pedidomax + ", " + fechamax);
			System.out
					.println("COD PRODUCTO y NOMBRE PRODUCTO, del producto más comprado: " + codmax + ", " + nombremax);
			System.out.println(
					"-----------------------------------------------------------------------------------------------");

			System.out.println();

			res.close();
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertarempleado(String nombre, String apellido1, String apellido2, String extension,
			String email, String codigooficina, int codigojefe, String puesto) {

		String mensaje = "";
		int error = 0;
		// comprobar oficina
		if (!comprobaroficina(codigooficina)) {
			// no existe la oficina
			error = 1;
			mensaje = mensaje + "LA OFICINA NO EXISTE: " + codigooficina;
		}

		// Comprobar si existe empleado jefe
		if (!comprobarempleado(codigojefe)) {
			// no existe la oficina
			error = 1;
			mensaje = mensaje + "\nEL JEFE NO EXISTE: " + codigojefe;
		}

		if (error == 0) {
			// a insertar
			String sql1 = "insert into empleados (codigoempleado,  nombre,  apellido1,  apellido2,  extension,  email,   codigooficina,  codigojefe, puesto)"
					+ " values (?, ?,?, ?,?, ?,?, ?,? )";
			// crear el código de empleado

			String sql2 = "select max(codigoempleado)+1 from empleados";
			PreparedStatement sent;
			try {
				sent = conexion.prepareStatement(sql2);
				ResultSet res = sent.executeQuery();
				res.next();
				int codemple = res.getInt(1);

				sent = conexion.prepareStatement(sql1);
				sent.setInt(1, codemple);
				sent.setString(2, nombre);
				sent.setString(3, apellido1);
				sent.setString(4, apellido2);
				sent.setString(5, extension);
				sent.setString(6, email);
				sent.setString(7, codigooficina);
				sent.setInt(8, codigojefe);
				sent.setString(9, puesto);

				int filas = sent.executeUpdate();

				System.out.println("Empleado insertado con el código " + codemple);

				res.close();
				sent.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("ERROR. NO SE PUEDE INSERTAR: ");
			System.out.println(mensaje);

		}

	}

	private static boolean comprobarempleado(int codigojefe) {
		boolean existe = false;

		String sql = "select * from empleados where codigoempleado= ?";

		try {
			PreparedStatement sent = conexion.prepareStatement(sql);
			sent.setInt(1, codigojefe);
			ResultSet res = sent.executeQuery();
			if (res.next()) {
				existe = true;
			}
			res.close();
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;
	}

	private static boolean comprobaroficina(String codigooficina) {

		boolean existe = false;

		String sql = "select count(*) from oficinas where codigooficina= ?";

		try {
			PreparedStatement sent = conexion.prepareStatement(sql);
			sent.setString(1, codigooficina);
			ResultSet res = sent.executeQuery();
			res.next();
			if (res.getInt(1) == 1) {
				existe = true; // oficina existe
			}
			res.close();
			sent.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;

	}

	private static void mostrarMenu() {

		System.out.println("------------------------------------------------------");
		System.out.println("  .  1 Insertar Empleado ");
		System.out.println("  .  2 Visualizar pedidos de un cliente.");
		System.out.println("  .  3 Crear clientes sin pedido. ");
		System.out.println("  .  4 Actualizar Clientes por empleado.");
		System.out.println("  .  5 Crear STOCKACTUALIZADO.");
		System.out.println("  .  6 Oficinas con función almacenada.");
		System.out.println("  .  7 Ver los pedidos de todos los clientes.");
		System.out.println("  .  8 Tratar nuevos empleados.");
		System.out.println("  .  0 SALIR.");
		System.out.println("------------------------------------------------------");
	}

}
