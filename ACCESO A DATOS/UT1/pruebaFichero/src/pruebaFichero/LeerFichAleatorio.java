package pruebaFichero;

import java.io.*;

public class LeerFichAleatorio {

	public static void main(String[] args) {
		try {
			leerfichero();
			System.out.println("---------------------");
			consultarregistro(5); // reg existe
			System.out.println("---------------------");
			consultarregistro(10); // reg no existe
			// id, apellido,dep, salario

			System.out.println("---------------------");
			insertarregistro(6, "NUEVO 6", 10, 1000d); // Ya existe id
			System.out.println("---------------------");
			insertarregistro(20, "NUEVO 20", 10, 1000d); // Se inserta
			System.out.println("---------------------");

			insertarregistro(10, "NUEVO 10", 10, 1000d); // Hueco, se inserta

			System.out.println("---------------------");
			insertarregistro(15, "NUEVO 15", 15, 1000d); // Hueco, se inserta

			System.out.println("---------------------");
			System.out.println("------MODIFICAR---------------");

			// Sumar subida al salario de los emples de un dep
			System.out.println("------MODIFICAR EMPLES DE UN DEP---------------");
			modificartodoslosdeldep(10, 100d);

			modificartodoslosdeldep(100, 100d);
			
			System.out.println("------MODIFICAR UN EMPLE---------------");
			// modif un empleado sumar 100 a su salario
			modificaremple(5); // ok

			modificaremple(35); // No existe

			modificaremple(16); // No existe, es hueco
			
			System.out.println("--------------BORRAR---------------");
			
			borrarEmple(3); //OK
			borrarEmple(35); //No existe
			borrarEmple(16); //No existe. hueco
			
			System.out.println("--------------LISTAR---------------");
			leerfichero();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void borrarEmple(int iden) {
		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		
			try {
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");
				long posicion =(iden - 1)*36;
				
				if(posicion>=file.length())
					System.out.println("NO EXISTE EMPLEADO: (SOBREPASA) "+iden);
				else {
					file.seek(posicion);
					int idd = file.readInt();
					if(idd==iden) {
						System.out.println("ID: "+iden+" EXISTE, BORRADO LÓGICO");
						file.seek(posicion);
						//ponemos a 0 el id
						file.writeInt(0);
						String apellido="          ";
						file.writeChars(apellido);
						file.writeInt(0);
						file.writeDouble(0);
					}else {
						System.out.println("ID: "+iden+" EXISTE, ES HUECO, NO SE BORRA");
					}
				}
				
				file.close(); // cerrar fichero
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private static void modificaremple(int iden) {
		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		
			try {
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");
				long posicion =(iden - 1)*36;
				
				if(posicion>=file.length())
					System.out.println("NO EXISTE EMPLEADO: "+iden);
				else {
					file.seek(posicion);
					int idd = file.readInt();
					if(idd==iden) {
						System.out.println("ID: "+iden+" EXISTE, ACTUALIZO SALARIO");
						file.seek(posicion+4+20+4);
						Double sal = file.readDouble();
						sal = sal+100d;
						file.seek(posicion+4+20+4);
						file.writeDouble(sal);
					}else {
						System.out.println("ID: "+iden+" EXISTE, ES HUECO, NO ACTUALIZO SALARIO");
					}
				}
				
				file.close(); // cerrar fichero
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	private static void modificartodoslosdeldep(int depart, double subida) throws IOException {
		// leer secuencialmente todos
		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");
			int id, dep, posicion;
			Double salario;
			char apellido[] = new char[10], aux;
			posicion = 0; // para situarnos al principio
            int contador=0;
			for (;;) { // recorro el fichero
				file.seek(posicion); // nos posicionamos en posicion
				id = file.readInt(); // obtengo id de empleado
				for (int i = 0; i < apellido.length; i++) {
					aux = file.readChar();// recorro uno a uno los caracteres del apellido
					apellido[i] = aux; // los voy guardando en el array
				}
				String apellidoS = new String(apellido);// convierto a String el array
							
				dep = file.readInt();// obtengo dep
				
				if (dep==depart) {
					//actualizar
					salario = file.readDouble(); // obtengo salario
                    salario=salario+subida;
                    file.seek(posicion+4+20+4); 
                    file.writeDouble(salario);
                    System.out.println(
    						"ID actualizado: " + id + ", Apellido: " 
                    + apellidoS + ", Departamento: " + dep + ", Salario: " + salario);
                    contador=contador+1;
				}
				
				posicion = posicion + 36; 
				// Si he recorrido todos los bytes salgo del for
				if (posicion >= file.length() || file.getFilePointer() == file.length())
					break;

			} // fin bucle for
			file.close(); // cerrar fichero
			
			  System.out.println("Se han actualizado: "+contador +
						" empleados del dep: "+depart);
			
					} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertarregistro(int id, String apellido, int dep, double salario) throws IOException {

		File fiche = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		try {
			RandomAccessFile file = new RandomAccessFile(fiche, "rw");
			long posicion = (id - 1) * 36; // calculamos la posicion

			if (posicion >= file.length()) {
				// id no existe, se añade
				System.out.println("ID: " + id + ", NO EXISTE. Se inserta:");
				file.seek(posicion); // nos posicionamos
				file.writeInt(id); // se escribe id
				StringBuffer buffer = new StringBuffer(apellido);
				buffer.setLength(10); // 10 caracteres para el apellido
				file.writeChars(buffer.toString());// insertar apellido

				file.writeInt(dep); // insertar departamento

				file.writeDouble(salario);// insertar salario

			}

			else { // ident existe o es un hueco
				System.out.println("ID: " + id + ", EXISTE O ES HUECO.");
				// comprobamos el id
				file.seek(posicion);
				int iden = file.readInt();
				if (iden == 0) {
					// id es hueco, nuevo registro se graba
					file.seek(posicion);
					file.writeInt(id); // se escribe id

					StringBuffer buffer = new StringBuffer(apellido);
					buffer.setLength(10); // 10 caracteres para el apellido
					file.writeChars(buffer.toString());// insertar apellido

					file.writeInt(dep); // insertar departamento

					file.writeDouble(salario);// insertar salario
					System.out.println("ID: " + id + ", ES HUECO, SE INSERTA.");

				} else {
					// id ya existe, escribimos mensaje
					System.out.println("ID: " + id + ", YA EXISTE. NO SE INSERTA.");

				}

			}
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void consultarregistro(int identificador) throws IOException {

		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			int posicion = (identificador - 1) * 36; // calculo donde empieza el registro
			if (posicion >= file.length())
				System.out.println("ID: " + identificador + ", NO EXISTE EMPLEADO...");
			else {
				// nos posicionamos, leemos y mostramos
				System.out.println("ID: " + identificador + " LOCALIZADO:");
				file.seek(posicion);// nos posicionamos
				int id = file.readInt(); // obtengo id de empleado
				String ape = "";
				for (int i = 0; i < 10; i++) {
					ape = ape + file.readChar();// recorro uno a uno los caracteres del apellido
				}
				int dep = file.readInt();// obtengo dep
				Double salario = file.readDouble(); // obtengo salario

				System.out.println(
						"ID: " + id + ", Apellido: " + ape + ", Departamento: " + dep + ", Salario: " + salario);

				file.close();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void leerfichero() throws IOException {
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
}
