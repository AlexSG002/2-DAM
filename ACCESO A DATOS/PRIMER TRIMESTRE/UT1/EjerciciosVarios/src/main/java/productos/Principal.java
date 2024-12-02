package productos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import xmlventas.Venta;
import xmlventas.Producto;
import xmlventas.Productos;

public class Principal {
	
	static double PVP;
	static String fichdep = (".\\Productos.dat");
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
				ejercicio2();
				break;
			case 3:
				ejercicio3();
				break;
			case 4:
				ejercicio4();
				break;
			case 5:
				ejercicio5();
				break;
			case 0:
				System.out.println("Adios!");
				break;
			default:
				System.out.println("Seleccione una opción válida!");
				break;
			}
		} while (opcion != 0);

		sc.close();
	}

	private static void ejercicio5() throws IOException {
		System.out.println("GENERAR XML DE DETALLE DE VENTAS DE PRODUCTOS");
		System.out.println("---------------------------------------------");
		ArrayList<xmlventas.Producto> lista = new ArrayList<xmlventas.Producto>();
		File fichero = new File(".\\DatosdeVentas.dat");

		int codigoPro = 0;
		double pvp = 0;
		String nombrePro;
		int existencias;

		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int posicion = 0; // para situarnos al principio
		int lonProd = 46;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			codigoPro = file.readInt();
			if (codigoPro != 0) {
				// leer resto y visualizar
				nombrePro = "";
				for (int i = 0; i < 15; i++) {
					nombrePro = nombrePro + file.readChar();
				}
				existencias = file.readInt();
				pvp = file.readDouble();
				String mensaje = "";
				// LLamar al método que devuelva la lista de ventas
				// uni=unidadesvedidas(codigoPro);
				// double impor = uni*pvp;
				ArrayList<xmlventas.Venta> listaventas = new ArrayList<xmlventas.Venta>();
				//asignar pvp
				PVP=pvp;
				listaventas = llenarventas(codigoPro);
				xmlventas.Producto pro = new xmlventas.Producto(codigoPro, nombrePro.trim(), existencias, pvp,
						listaventas);

				lista.add(pro);
			}
			posicion = posicion + lonProd;
			if (posicion >= file.length())
				break;
		} // fin for
		file.close();
		// GENERAR EL XML, creamos un objeto de la raiz y asignamos los productos
		xmlventas.Productos pp = new xmlventas.Productos();
		pp.setLista(lista);

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(xmlventas.Productos.class);

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(pp, System.out);
			m.marshal(pp, new File(".\\ProductosVentas.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static ArrayList<Venta> llenarventas(int codigoPro) throws IOException {
		
		ArrayList<xmlventas.Venta> listaventas = new ArrayList<xmlventas.Venta>();
		File fichero = new File(".\\DatosdeVentas.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int posicion = 0; // para situarnos al principio
		int lon = 28;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			int codigo = file.readInt();

			if (codigo == codigoPro) {
				int uniVen = file.readInt();
				String fecha = "";
				for (int i = 0; i >= 10; i++) {
					fecha = fecha + file.readChar();
				}
				//Calcular el importe
				double impor = PVP * uniVen;
				//Crear el objeto venta y añadirlo a la lista
				xmlventas.Venta venta = new xmlventas.Venta(uniVen, fecha, impor);
				listaventas.add(venta);
			}

			posicion = posicion + lon;

			if (posicion >= file.length())
				break;
		}

	
		return listaventas;
	}

	private static void ejercicio4() throws IOException {

		System.out.println("GENERAR XML DE PRODUCTOS");
		System.out.println("-------------------------");
		ArrayList<xmlproductos.Producto> lista = new ArrayList<xmlproductos.Producto>();

		int codigoPro = 0;
		double pvp = 0;
		String nombrePro;
		int existencias;
		File fichero = new File(".\\Productos.dat");

		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int posicion = 0; // para situarnos al principio
		int lonProd = 46;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			codigoPro = file.readInt();
			if (codigoPro != 0) {
				// leer resto y visualizar
				nombrePro = "";
				for (int i = 0; i < 15; i++) {
					nombrePro = nombrePro + file.readChar();
				}
				existencias = file.readInt();
				pvp = file.readDouble();
				String mensaje = "";
				int uni = 0;
				uni = unidadesvedidas(codigoPro);
				double impor = uni * pvp;
				int exis = existencias - uni;
				if (exis < 3) {
					mensaje = "* A REPONER *";
				}
				xmlproductos.Producto pro = new xmlproductos.Producto(codigoPro, nombrePro.trim(), existencias, pvp,
						uni, impor, mensaje);
				lista.add(pro);
				// System.out.printf("%6s %-30s %12s %10s %n", codigoPro, nombrePro,
				// existencias, pvp);
			}
			posicion = posicion + lonProd;
			if (posicion >= file.length())
				break;
		} // fin for
		file.close();
		// GENERAR EL XML, creamos un objeto de la raiz y asignamos los productos
		xmlproductos.Productos pp = new xmlproductos.Productos();
		pp.setLista(lista);

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(xmlproductos.Productos.class);

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(pp, System.out);
			m.marshal(pp, new File(".\\Productos.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int unidadesvedidas(int codigoPro) throws IOException {
		// Recorrer secuencialmente el fichero de ventas
		// Leer 1 código y preguntar si coincide con el parámetro
		// si coinicide se suma
		int sumauni = 0;

		File fichero = new File(".\\DatosdeVentas.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int posicion = 0; // para situarnos al principio
		int lon = 28;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			int codigo = file.readInt();

			if (codigo == codigoPro) {
				int uniVen = file.readInt();
				sumauni = sumauni + uniVen;
			}

			posicion = posicion + lon;

			if (posicion >= file.length())
				break;
		}

		return sumauni;
	}

	private static void ejercicio3() throws IOException {
		int codigoPro = 0;
		int uniVen = 0;
		File fichero = new File(".\\DatosdeVentas.dat");
		File productos = new File(".\\Productos.dat");

		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		RandomAccessFile fiprod = new RandomAccessFile(productos, "rw");
		int posicion = 0; // para situarnos al principio
		int posicionProd = 0;
		int lon = 28;
		int lonProd = 46;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			codigoPro = file.readInt();
			uniVen = file.readInt();
			// Comprobar que esté entre 1 y 99
			if (codigoPro < 1 || codigoPro > 99) {
				System.out.println("ERROR, CÓDIGO DE PRODUCTO ERRÓNEO, NO ESTÁ ENTRE 1 Y 99: " + codigoPro);
			} else {
				// Comprobar si el código existe en productos
				posicionProd = (codigoPro - 1) * lonProd;
				if (posicionProd >= fiprod.length()) {
					System.out.println("No localizado, sobrepasa al último, producto: " + codigoPro);
				} else {
					fiprod.seek(posicionProd); // nos posicionamos en posicion
					int codPro = fiprod.readInt();
					if (codPro == codigoPro) {
						// Localizado se actualiza
						fiprod.seek(posicionProd + 34);
						int exis = fiprod.readInt();
						exis = exis - uniVen;
						// Actualizar
						fiprod.seek(posicionProd + 34);
						fiprod.writeInt(exis);
						System.out.println(
								"Localizado producto: " + codigoPro + ", se actualiza, existencias actuales: " + exis);
					} else {

						System.out.println("No localizado el producto: " + codigoPro + " es hueco");
					}
				}
			}
			posicion = posicion + lon;
			if (posicion >= file.length())
				break;
		}
		file.close();
		fiprod.close();
	} // fin for

	private static void ejercicio2() throws IOException {
		int codigoPro = 0;
		int uniVen = 0;
		String fecha;

		System.out.println("LISTADO DE VENTAS");
		System.out.println("------------------------------------------------------");
		System.out.printf("%6s %17s %14s %n", "CODIGO", "UNIDADES VENDIDAS", "FECHA");
		System.out.printf("%6s %17s %14s %n", "------", "-----------------", "--------------");
		File fichero = new File(".\\DatosdeVentas.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int posicion = 0; // para situarnos al principio
		int lon = 28;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			codigoPro = file.readInt();
			uniVen = file.readInt();
			fecha = "";
			for (int i = 0; i >= 10; i++) {
				fecha = fecha + file.readChar();
			}
			System.out.printf("%6s %17s %14s %n", codigoPro, uniVen, fecha);
			posicion = posicion + lon;
			if (posicion >= file.length())
				break;
		}

	} // fin for

	private static void ejercicio1() throws IOException {
		int codigoPro = 0;
		double pvp = 0;
		String nombrePro;
		int existencias;
		File fichero = new File(".\\Productos.dat");
		System.out.println("LISTADO DE PRODUCTOS");
		System.out.println("-----------------------------------------");
		System.out.printf("%6s %-30s %12s %10s %n", "CODIGO", "NOMBRE", "EXISTENCIAS", "PVP");
		System.out.printf("%6s %-30s %12s %10s %n", "------", "------------------------------", "------------",
				"----------");

		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int posicion = 0; // para situarnos al principio
		int lonProd = 46;
		for (;;) { // recorro el fichero
			file.seek(posicion); // nos posicionamos en posicion
			codigoPro = file.readInt();
			if (codigoPro != 0) {
				// leer resto y visualizar
				nombrePro = "";
				for (int i = 0; i < 15; i++) {
					nombrePro = nombrePro + file.readChar();
				}
				existencias = file.readInt();
				pvp = file.readDouble();
				System.out.printf("%6s %-30s %12s %10s %n", codigoPro, nombrePro, existencias, pvp);
			}
			posicion = posicion + lonProd;
			if (posicion >= file.length())
				break;
		} // fin for
	}

	private static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("OPERACIONES CON ALUMNOS");
		System.out.println("  1. Ejercicio 1. Listar productos");
		System.out.println("  2. Ejercicio 2. Listar ventas");
		System.out.println("  3. Ejercicio 3. Actualizar");
		System.out.println("  4. Ejercicio 4. Crear XML Productos");
		System.out.println("  5. Ejercicio 5. Crear XML Detalle");
		System.out.println("  0. Salir");
		System.out.println("------------------------------------------------------");
	}
}
