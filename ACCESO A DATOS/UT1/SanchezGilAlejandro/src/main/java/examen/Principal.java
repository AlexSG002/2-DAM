package examen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				actualizarViajes();
				break;
			case 2:
				listarViajes();
				break;

			case 3:
				break;

			case 0:
				System.out.println("¡Hasta luego!");
				break;
			}
		} while (opcion != 0);
	}

	private static void listarViajes() throws IOException {
		File fichero = new File(".\\Viajes.dat");

		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "r");
			int codViaje;
			String nombre;
			int pvp;
			int plazas;
			String situacion;
			int posicion;
			posicion = 0;
			System.out.printf("%8s %-30s %7s %7s %9s %n", "CodViaje", "Nombre", "PVP", "Plazas", "Situación");
			System.out.printf("%8s %-30s %7s %7s %9s %n", "--------", "------------------------------", "-------",
					"-------", "---------");

			for (;;) {
				file.seek(posicion);
				codViaje = file.readInt();
				if (codViaje != 0) {
					nombre = "";
					for (int i = 0; i < 30; i++) {
						nombre = nombre + file.readChar();
					}
					pvp = file.readInt();
					plazas = file.readInt();
					situacion = "";
					for (int i = 0; i < 1; i++) {
						situacion = situacion + file.readChar();
					}
					System.out.printf("%8s %-30s %7s %7s %9s %n", codViaje, nombre, pvp, plazas, situacion);
				}
				posicion = posicion + 74;
				if (posicion >= file.length()) {
					break;
				}

			}
			System.out.printf("%8s %-30s %7s %7s %9s %n", "--------", "------------------------------", "-------",
					"-------", "---------");
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void actualizarViajes() throws IOException {
		int codViajeV = 0;
		int codViajeM = 0;
		String nombreV;
		String nombreM;
		int pvpV;
		int pvpM;
		int plazasV;
		int plazasM;
		String situacion;
		String operacion;
		File fichero = new File(".\\Viajes.dat");
		File fichero2 = new File(".\\Movimientos.dat");
		try {
			RandomAccessFile file2 = new RandomAccessFile(fichero2, "r");
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");

			int posicion = 0;
			int posicionMov = 0;
			int lon = 74;
			boolean altaE = false;
			boolean bajaE = false;
			boolean modE = false;
			for (;;) {
				file2.seek(posicionMov);
				codViajeM = file2.readInt();
				operacion = "";
				file2.seek(posicionMov + 72);
				operacion = operacion + file2.readChar();
				for (;;) {
					file.seek(posicion);
					codViajeV = file.readInt();
					if (codViajeV == codViajeM) {
						if (codViajeV != 0) {
							if (operacion.equals("A")) {
								
								System.out.println(
										"ERROR. VIAJE YA EXISTE, NO SE PUEDE DAR DE ALTA AL VIAJE: " + codViajeM);
								altaE = true;
							}

							if (operacion.equals("B")) {
								System.out.println("VIAJE LOCALIZADO EN VIAJES, SE DA DE BAJA EL VIAJE: " + codViajeM);
								bajaE = true;
								file.write(0);
								
							}
							if (operacion.equals("M")) {

								System.out.println("VIAJE LOCALIZADO, SE MODIFICA EL VIAJE: " + codViajeM);
								modE = true;
							}
						}

					}
					posicion = posicion + lon;
					if (posicion >= file.length()) {
						if(operacion.equals("A") && !altaE) {
							System.out.println("VIAJE NO LOCALIZADO EN VIAJES, SE DA DE ALTA CON CÓDIGO: " + codViajeM);
							file.writeInt(codViajeM);
							
						}else {
							if(operacion.equals("B") && !bajaE) {
								System.out.println(
										"ERROR. VIAJE NO EXISTE, NO SE PUEDE DAR DE BAJA AL VIAJE: " + codViajeM);
							}else
								if(operacion.equals("M") && !modE) {
									System.out.println(
											"ERROR. VIAJE NO EXISTE, NO SE PUEDE MODIFICAR AL VIAJE: " + codViajeM);
								}
						}
						
						break;
					}
				}
				posicion = 0;
				posicionMov = posicionMov + lon;

				if (posicionMov >= file2.length()) {
					break;
				}
			}
			file.close();
			file2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarMenu() {
		System.out.println("OPERACIONES CON VIAJES");
		System.out.println("1. Actualizar Viajes.dat");
		System.out.println("2. Listar el fichero Viajes.dat");
		System.out.println("3. Crear XML viajes.xml");
		System.out.println("0. Salir");

	}
}
