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

	private static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("  1. Insertar empleado");
		System.out.println("  2. Visualizar pedidos de un cliente");
		System.out.println("  3. Crear clientes sin pedido");
		System.out.println("  4. Actualizar clientes por empleado");
		System.out.println("  5. Crear STOCKACTUALIZADO");
		System.out.println("  6. Oficinas con función almacenada");
		System.out.println("  7. Ver los pedidos de todos los clientes");
		System.out.println("  8. Tratar nuevos empleados");
		System.out.println("  0. Salir");
		System.out.println("------------------------------------------------------");
	}

}
