package operacionesEmple;

import java.sql.Connection;
import java.sql.SQLException;


public class Principal {

	public static void main(String[] args) {

		OperacionesEmple ope = new OperacionesEmple();
		
		//Connection conexion = Conexiones.getMysql("ejemplo25", "root", "");
		Connection conexion = Conexiones.getOracle("ejemplo25", "ejemplo25");
		//Connection conexion = Conexiones.getSQlite(".\\basedatos\\ejemplo.db");

		if (conexion != null) {
			
			//probar comprobaremple
			int emp_no=1235;
			if (ope.comprobaremple(conexion,emp_no)){
				System.out.println("Empleado "+emp_no+" existe");
			}
			else {
				System.out.println("Empleado "+emp_no+" no existe");
			}
			
			// //probar borraremple
			System.out.println("----------------------------------");
			System.out.println(ope.borraremple(conexion, 123));
			System.out.println(ope.borraremple(conexion, 1238));
			System.out.println(ope.borraremple(conexion, 7566));
		
			// cargar fecha
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			// MODIFICAR
			System.out.println("----------------------------------");
			System.out.println(ope.modificaremple(conexion, 
					124, "NuevoNombre", "PROGRAMADOR", 1600f,
					0.0f, sqlDate, 20, 7369)); //OK
			
			System.out.println(ope.modificaremple(conexion, 
					1247, "NuevoNombre", "PROGRAMADOR", 1600f,
					0.0f, sqlDate, 20, 7369)); //error no se puede actualizar no existe
			
			
			System.out.println(ope.modificaremple(conexion, 
					125, "NuevoNombre5", "PROGRAMADOR5", 1600f,
					0.0f, sqlDate, 80, 7369)); //error dep no existe
		
			
			// INSERTAR
			System.out.println("----------------------------------");
			System.out.println(ope.insertaremple(conexion, 
					1234, "Nuevo1234", "PROGRAMADOR", 1600f,
					0.0f, sqlDate, 20, 7369)); //OK
			
			System.out.println(ope.insertaremple(conexion, 
					1234, "Nuevo1234", "PROGRAMADOR", 1600f,
					0.0f, sqlDate, 20, 7369)); // YA EXISTE
			
			System.out.println(ope.insertaremple(conexion, 
					1235, "Nuevo1235", "PROGRAMADOR", 1700f,
					0.0f, sqlDate, 80, 7369)); //ERROR FK
			
			System.out.println(ope.insertaremple(conexion, 
					1236, "Nuevo1236Nuevo1235Nuevo1235", "PROGRAMADOR", 1700f,
					0.0f, sqlDate, 80, 7369)); //ERROR campo
			
			
			// verdatos de todos
			ope.verempleados(conexion);
			
			// ver un empleado
			
			ope.verunempleado(conexion,1234); // existe
			
			ope.verunempleado(conexion,1222); // No existe
			
			
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}//fin if

	}

}
