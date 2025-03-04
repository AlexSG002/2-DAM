package pruebaFichero;

import java.io.*;
import java.util.Scanner;

public class EjercicioMenu {

	static String fichdep = (".\\AleatorioDepar.dat");
	static int LON = 72;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				ejercicio1();
				break;
			case 2:
				int dep = 0;
				System.out.println("Ejercicio 2. Cosultar si existe");
				dep = sc.nextInt();

				if (ejercicio2(dep)) {
					System.out.println("DEPARTAMENTO " + dep + " EXISTE");
				} else {
					System.out.println("DEPARTAMENTO " + dep + " NO EXISTE");
				}
				break;
			case 3:
				int cod = 10, num = 3;
				float mediasal = 1500f;
				String nombre = "MARKETING", loc = "MADRID";
				System.out.println(ejercicio3(cod, nombre, loc, num, mediasal));
				cod = 20;
				num = 4;
				mediasal = 1200f;
				nombre = "VENTAS";
				loc = "TALAVERA";
				System.out.println(ejercicio3(cod, nombre, loc, num, mediasal));
				break;
			case 4:
				int cod2 = 10, cod3 = 3;
				ejercicio4(cod2); // ok
				ejercicio4(cod3); // no existe
				break;
			case 5:
				int cod4 = 10;
				String loc2 = "TOLEDO";
				float mediasal2 = 3f;
				ejercicio5(cod4, loc2, mediasal2);

				cod4 = 15;
				loc2 = "TOLEDO";
				mediasal2 = 3f;
				ejercicio5(cod4, loc2, mediasal2);
				break;
			case 6:
				int cod5 = 20;
				int cod6 = 10;
				System.out.println(ejercicio6(cod5));
				System.out.println(ejercicio6(cod6));
				break;
			case 7:
				ejercicio7();
				break;
			case 8:
				listarEmpleados();
				break;
			case 9:
				actualizarDepartamentos();
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
	}

	private static void actualizarDepartamentos() throws IOException {
		// actualizar, Recorremos secuencialmente el empleado y vamos actualizando de
		// manera directa en depars
		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		//
		int id, dep, posicion;
		Double salario;
		char apellido[] = new char[10], aux;

		posicion = 0; // para situarnos al principio

		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			id = file.readInt(); // obtengo id de empleado
			if (id != 0) {
				for (int i = 0; i < apellido.length; i++) {
					aux = file.readChar();// recorro uno a uno los caracteres del apellido
					apellido[i] = aux; // los voy guardando en el array
				}
				String apellidoS = new String(apellido);// convierto a String el array
				dep = file.readInt();// obtengo dep
				salario = file.readDouble(); // obtengo salario

				// Comprobar si el dep existe para actualizar
				if (ejercicio2(dep)) {
					// El departamento si existe en el fichdep

					actualizarDep(dep, salario.floatValue());
					System.out.println("Actualizado dep: "+dep);
				}
			}
			
			posicion = posicion + 36; // me posiciono para el sig empleado
										// Cada empleado ocupa 36 bytes (4+20+4+8)
			// Si he recorrido todos los bytes salgo del for
			if (posicion >= file.length())
				break;

		} // fin bucle for
		file.close(); // cerrar fichero
	}

	private static void actualizarDep(int dep, Float salario) {
		File fichero = new File(fichdep);
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");
			long posicion = (dep - 1) * LON;
			file.seek(posicion + 64); // Nos posicionamos en contador
			int con = file.readInt();
			con = con + 1;
			file.seek(posicion + 64);
			file.write(con + 1); // sumo 1 al número de empleados

			file.seek(posicion + 68); // Nos posicionamos en media sal y grabamos
			float sal = file.readFloat();
			float mediasal = (sal + salario) / 2;
			file.seek(posicion + 68);
			file.writeFloat(mediasal);

			file.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void listarEmpleados() throws IOException {
		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		//
		int id, dep, posicion;
		Double salario;
		char apellido[] = new char[10], aux;

		posicion = 0; // para situarnos al principio

		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			id = file.readInt(); // obtengo id de empleado
			for (int i = 0; i < apellido.length; i++) {
				aux = file.readChar();// recorro uno a uno los caracteres del apellido
				apellido[i] = aux; // los voy guardando en el array
			}
			String apellidoS = new String(apellido);// convierto a String el array
			dep = file.readInt();// obtengo dep
			// dep=0;
			salario = file.readDouble(); // obtengo salario

			// salario=0d;
			System.out.println(
					"ID: " + id + ", Apellido: " + apellidoS + ", Departamento: " + dep + ", Salario: " + salario);
			posicion = posicion + 36; // me posiciono para el sig empleado
										// Cada empleado ocupa 36 bytes (4+20+4+8)
			// Si he recorrido todos los bytes salgo del for
			if (posicion >= file.length() || file.getFilePointer() == file.length())
				break;

		} // fin bucle for
		file.close(); // cerrar fichero
	}

	private static void ejercicio7() {
		File fichero = new File(fichdep);
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");
			long posicion = 0;
			System.out.printf("%-6s %-15s %-15s %-6s %-9s %n", "CODEP", "NOMBRE DEP", "LOC DEP", "NUMEMP", "MEDIASAL");
			System.out.printf("%-6s %-15s %-15s %-6s %-9s %n", "------", "---------------", "---------------", "------",
					"---------");
			for (;;) {
				file.seek(posicion);
				int cod = file.readInt();
				if (cod != 0) {
					String nom = "";
					for (int i = 0; i < 15; i++) {
						nom = nom + file.readChar();
					}
					String loc = "";
					for (int i = 0; i < 15; i++) {
						loc = loc + file.readChar();
					}

					int num = file.readInt();

					float med = file.readFloat();

					System.out.printf("%-6s %-15s %-15s %-6s %-9s %n", cod, nom, loc, num, med);
				}
				posicion = posicion + LON;
				if (posicion >= file.length())
					break;

			}
			file.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static String ejercicio6(int cod) throws IOException {
		String mensaje = "";
		if (!ejercicio2(cod)) {
			System.out.println("ERR0R EL DEPARTAMENT0 N0 EXISTE, N0 SE BORRA: " + cod);

		} else {
			// existe el departamento, se modifica
			File fichero = new File(fichdep);
			try {
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");
				long posicion = (cod - 1) * LON;
				file.seek(posicion);
				// Grabar 0's.
				file.writeInt(0);
				StringBuffer buffer = new StringBuffer("   ");
				buffer.setLength(15);
				file.writeChars(buffer.toString());

				buffer = new StringBuffer("  ");
				buffer.setLength(15);
				file.writeChars(buffer.toString());

				file.writeInt(0);
				file.writeFloat(0);

				file.close();
				mensaje = "\nEl DEPARTAMENTO SE HA BORRADO: " + cod;
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return mensaje;
	}

	private static String ejercicio5(int cod, String loc, float mediasal) throws IOException {

		String mensaje = "";
		if (!ejercicio2(cod)) {
			System.out.println("ERR0R EL DEPARTAMENT0 N0 EXISTE: " + cod);

		} else {
			// existe el departamento, se modifica
			File fichero = new File(fichdep);
			try {
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");
				long posicion = (cod - 1) * LON;
				file.seek(posicion + 34); // Nos posicionamos en loc
				StringBuffer buffer = new StringBuffer(loc);
				buffer.setLength(15);
				file.writeChars(buffer.toString());

				file.seek(posicion + 68); // Nos posicionamos en media sal y grabamos
				file.writeFloat(mediasal);

				file.close();
				mensaje = "\nEl DEPARTAMENTO SE HA ACTUALIZADO: " + cod;
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return mensaje;
	}

	private static boolean ejercicio4(int cod) throws IOException {
		boolean existe = false;
		File fichero = new File(fichdep);

		if (!ejercicio2(cod)) {
			System.out.println("ERR0R EL DEPARTAMENT0 N0 EXISTE: " + cod);

		} else {
			System.out.println("DEPARTAMENTO EXISTE");
			try {
				RandomAccessFile file = new RandomAccessFile(fichero, "r");

				long posicion = (cod - 1) * LON;
				// dep si existe leemos y visualizamox
				file.seek(posicion);
				cod = file.readInt();
				if (cod != 0) {
					String nom = "";
					for (int i = 0; i < 15; i++) {
						nom = nom + file.readChar();
					}
					String loc = "";
					for (int i = 0; i < 15; i++) {
						loc = loc + file.readChar();
					}

					int num = file.readInt();

					float med = file.readFloat();
					System.out.printf("%-6s %-15s %-15s %-6s %-9s %n", "CODEP", "NOMBRE DEP", "LOC DEP", "NUMEMP",
							"MEDIASAL");
					System.out.printf("%-6s %-15s %-15s %-6s %-9s %n", "------", "---------------", "---------------",
							"------", "---------");
					System.out.printf("%-6s %-15s %-15s %-6s %-9s %n", cod, nom, loc, num, med);
					existe = true;
					file.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		return existe;

	}

	private static String ejercicio3(int cod, String nombre, String loc, int num, float sal) {
		String mensaje = "";
		if (cod < 1 || cod > 100) {
			return "ERROR EL DEPARTAMENTO DEBE ESTAR ENTRE 1 Y 100: " + cod;
		}

		try {
			if (ejercicio2(cod)) {
				return "ERROR EL DEPARTAMENTO YA EXISTE NO SE PUEDE INSERTAR: " + cod;

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		File fichero = new File(fichdep);
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");
			long posicion = (cod - 1) * LON;
			file.seek(posicion);
			file.writeInt(cod);
			StringBuffer buffer = new StringBuffer(nombre);
			buffer.setLength(15);
			file.writeChars(buffer.toString());

			buffer = new StringBuffer(loc);
			buffer.setLength(15);
			file.writeChars(buffer.toString());

			file.writeInt(num);
			file.writeFloat(sal);

			mensaje += "REGISTRO INSERTADO, Cod: " + cod + ", " + nombre;

			file.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return mensaje;
	}

	private static boolean ejercicio2(int id) throws IOException {
		File fichero = new File(fichdep);
		boolean existe = false;
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "r");
			int posicion = (id - 1) * LON; // calculo donde empieza el registro
			if (posicion >= file.length())
				existe = false;
			else {
				file.seek(posicion);
				int ident = file.readInt();
				if (ident == id)
					existe = true;
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return existe;
	}

	private static void ejercicio1() throws IOException {
		File fichero = new File(fichdep);
		if (fichero.exists()) {
			System.out.println("Fichero ya está creado");
		} else
			try {
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");
				System.out.println("Fichero creado");
				file.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

	}

	private static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("OPERACIONES CON ALUMNOS");
		System.out.println("  1. Ejercicio 1. Crear fichero");
		System.out.println("  2. Ejercicio 2. Consultar registro");
		System.out.println("  3. Ejercicio 3. Insertar registro");
		System.out.println("  4. Ejercicio 4. Visualizar registro");
		System.out.println("  5. Ejercicio 5. Modificar registro");
		System.out.println("  6. Ejercicio 6. Borrar registro");
		System.out.println("  7. Ejercicio 7. Listar departamentos");
		System.out.println("  8. Ejercicio 8. Listar empleados");
		System.out.println("  9. Ejercicio 9. Actualizar");
		System.out.println("  0. Salir");
		System.out.println("------------------------------------------------------");
	}
}
