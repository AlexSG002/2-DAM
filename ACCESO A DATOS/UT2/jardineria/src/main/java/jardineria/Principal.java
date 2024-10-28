package jardineria;

import java.sql.*;
import java.util.Scanner;

public class Principal {

	
	public static void main(String[] args) {
		OperacionesEmple ope = new OperacionesEmple();
		Connection conn = Conexiones.getOracle("JARDINERIA", "JARDINERIA");
		int opcion;
		Scanner sc = new Scanner(System.in);
		if (conn != null) {
			do {
				mostrarMenu();
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					int cod=1;
					String nom="";
					String ape1="";
					String ape2="";
					int extension;
					String email="";
					String codOfi="";
					int codJefe;
					String puesto="";
					if(ope.comprobaremple(conn, cod)) {
						System.out.println("Empleado: "+cod+" existe");
					}else {
						System.out.println("Empleado: "+cod+" no existe");
						
						
						
					}
					break;
				case 2:
					verPedidosCliente(conn, 2); //No existe
					verPedidosCliente(conn, 6); //No tiene pedidos
					verPedidosCliente(conn, 1); //Ok
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				}
			} while (opcion != 0);
		}
	}

	// String sql = "SELECT COUNT(*) FROM EMPLEADOS WHERE CODIGOEMPLEADO = ?";

	private static void verPedidosCliente(Connection conn, int codigoCliente) {
		String sql1 = "select nombrecliente, lineadireccion1 from clientes where codigocliente = ?";
		
		String sql2 = "select count(*) from pedidos where codigocliente = ?";
		
		PreparedStatement sentencia;

		try {
			sentencia = conn.prepareStatement(sql1);
			sentencia.setInt(1, codigoCliente);
			ResultSet resul = sentencia.executeQuery();
			if (resul.next()) {
				//Cliente existe
				PreparedStatement sentencia2;
				sentencia2 = conn.prepareStatement(sql2);
				sentencia2.setInt(1, codigoCliente);
				ResultSet resul2 = sentencia2.executeQuery();
				resul2.next();
				//Visualizamos
				
				System.out.println("COD-CLIENTE: "+codigoCliente+" NOMBRE: "+resul.getString(1));
				System.out.println("DIRECCIÓN1: "+resul.getString(2)+" Número de pedidos: "+resul2.getInt(1));
				System.out.println("------------------------------------------------------");
				
				if(resul2.getInt(1)>0) {
					listadoPedidosCliente(conn, codigoCliente);
					
				}
				
				
				sentencia2.close();
				resul2.close();
			}else {
				System.out.println("----------------------------------------------");
				System.out.println("Código de cliente no existe: "+codigoCliente);
				System.out.println("----------------------------------------------");
			}
			sentencia.close();
			resul.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void listadoPedidosCliente(Connection conn, int codigoCliente) {
		String sql1 = "select codigopedido, fechapedido, estado from pedidos where codigocliente = ? order by codigopedido";
		
		PreparedStatement sentencia;
		

		try {
			sentencia = conn.prepareStatement(sql1);
			sentencia.setInt(1, codigoCliente);
			ResultSet resul = sentencia.executeQuery();
			float importemax = 0;
			int pedidomax=0;
			int cantidadmax = 0;
			String nombremax="";
			String codmax = "";
			Date fechamax = null;
			while(resul.next()) {
				int sumacantidad = 0;
				float sumaprecio = 0;
				float sumaimporte = 0;
				System.out.println("   COD-PEDIDO :"+resul.getInt(1)+" FECHA PEDIDO: "+resul.getDate(2)
				+" ESTADO DEL PEDIDO: "+resul.getString(3));
				
				System.out.printf("      %9s %9s %-40s %10s %10s %10s %n", "NUM-LINEA"
						, "COD-PROD", "NOMBRE PRODUCTO", "CANTIDAD", "PREC-UNID", "IMPORTE");
				System.out.printf("      %9s %9s %-40s %10s %10s %10s %n", "---------"
						, "---------", "--------------------------------------------------"
						, "----------", "----------", "----------");
				
				//DETALLE DEL PEDIDO
				String sql2 = "Select numerolinea, codigoproducto, nombre, cantidad, "
						+ "preciounidad, (cantidad*preciounidad) from detallepedidos join productos using (codigoproducto)"
						+ " where codigopedido = ? order by numerolinea";
				
				PreparedStatement sentencia2 = conn.prepareStatement(sql2);
				sentencia2.setInt(1, resul.getInt(1));
				ResultSet resul2 = sentencia2.executeQuery();
				while(resul2.next()) {
					System.out.printf("      %9s %9s %-40s %10s %10s %10s %n", resul2.getInt(1)
							, resul2.getString(2), resul2.getString(3)
							, resul2.getInt(4), resul2.getFloat(5), resul2.getFloat(6));
					
					sumacantidad = sumacantidad + resul2.getInt(4);
					sumaprecio = sumaprecio + resul2.getFloat(5);
					sumaimporte = sumaimporte + resul2.getFloat(6);
					
					if(resul2.getInt(4) > cantidadmax) {
						cantidadmax = resul2.getInt(4);
						nombremax = resul2.getString(3);
						codmax = resul2.getString(2);
					}
					
					System.out.printf("      %9s %9s %-40s %10s %10s %10s %n", "---------"
							, "---------", "--------------------------------------------------"
							, "----------", "----------", "----------");
					
					
				}
				resul2.close();
				sentencia2.close();
				//totales por pedido
				System.out.printf("      %9s %9s %-40s %10s %10s %10s %n", ""
						, "", "Totales"
						, sumacantidad, sumaprecio, sumaimporte);
				
				if(sumaimporte > importemax) {
					pedidomax = resul.getInt(1);
					fechamax = resul.getDate(2);
					importemax = sumaimporte;
				}
				
			}
			
			System.out.printf("      %9s %9s %-40s %10s %10s %10s %n", "---------"
					, "---------", "--------------------------------------------------"
					, "----------", "----------", "----------");
			System.out.println("COD de Pedido y Fecha de pedido con total importe max: "+pedidomax+", "+fechamax);
			System.out.println("COD PRODUCTO Y NOMBRE PDRODUCTO, del producto más comrpado (producto con cantidad máxima): "+codmax+", "+nombremax);
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			sentencia.close();
			resul.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void mostrarMenu() {
		System.out.println("----------------------------------------------");
		System.out.println("  1. Insertar empleado");
		System.out.println("  2. Visualizar pedidos de un cliente");
		System.out.println("  3. Crear clientes sin pedido");
		System.out.println("  4. Actualizar clientes por empleado");
		System.out.println("  5. Crear STOCKACTUALIZADO");
		System.out.println("  6. Oficinas con función almacenada");
		System.out.println("  7. Ver los pedidos de todos los clientes");
		System.out.println("  8. Tratar nuevos empleados");
		System.out.println("  0. Salir");
		System.out.println("----------------------------------------------");
	}

}
