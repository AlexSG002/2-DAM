package libreria2;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBException;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Principal {

	public static void main(String[] args) throws JAXBException, IOException {

		crearXML();

		leerXML();
	}

	private static void leerXML() {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(NuevosDepartamentos.class);
			Unmarshaller unmars = context.createUnmarshaller();
			NuevosDepartamentos objeto = (NuevosDepartamentos) unmars.unmarshal(new File("Librerias.xml"));
			// recuperar los datos de las librerias
			ArrayList<librerias> listalibrerias = objeto.getLibrerias();
			// recorrer las librerias
			for (librerias lib : listalibrerias) { 
				
				ArrayList<Libro> listaLibros = lib.getListaLibro();
				String lugar = lib.getLugar();
				String nombre = lib.getNombre();
				
				System.out.println("Nombre libreria: "+nombre+", lugar: "+lugar);
				System.out.println("Numero de libros: "+listaLibros.size());
				// Mostrar libros
				for (Libro libro : listaLibros) {
					System.out.println("Nombre : "+libro.getNombre()+", autor: "+libro.getAutor());
				}
	      } 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static final String MIARCHIVO_XML = "./libreria.xml";

	public static void crearXML()

			throws JAXBException, IOException {

		NuevosDepartamentos mislibrerias = new NuevosDepartamentos();
		ArrayList<librerias> librerias = new ArrayList<librerias>();

		// Se crea la lista de libros

		ArrayList<Libro> libroLista = new ArrayList<Libro>();

		// Creamos dos libros y los añadimos

		Libro libro1 = new Libro("Entornos de Desarrollo",

				"Alicia Ramos", "Garceta", "978-84-1545-297-3");

		libroLista.add(libro1);

		Libro libro2 = new Libro("Acceso a Datos", "Maria Jesús Ramos",

				"Garceta", "978-84-1545-228-7");

		libroLista.add(libro2);

		// Se crea La libreria y se le asigna la lista de libros

		librerias milibreria1 = new librerias(libroLista, "Prueba de libreria JAXB", "Talavera, como no");

		librerias milibreria2 = new librerias(libroLista, "La Capital", "Madrid");

		librerias.add(milibreria1);

		librerias.add(milibreria2);

		mislibrerias.setLibrerias(librerias);
		// Creamos el contexto indicando la clase raíz

		JAXBContext context = JAXBContext.newInstance(NuevosDepartamentos.class);

		// Creamos el Marshaller, convierte el java bean en una cadena XML

		Marshaller m = context.createMarshaller();

		// Formateamos el xml para que quede bien

		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Lo visualizamos con system out

		m.marshal(mislibrerias, System.out);

		// Escribimos en el archivo

		m.marshal(mislibrerias, new File("Librerias.xml"));

	}

}
