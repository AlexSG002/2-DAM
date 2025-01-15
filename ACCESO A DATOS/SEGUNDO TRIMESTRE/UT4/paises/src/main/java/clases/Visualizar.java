package clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Visualizar {

	private static ODB odb = ODBFactory.open("equipos.db");

	public static void main(String[] args) {

//	    Objects<Jugadores> objects = 
//	                odb.getObjects(Jugadores.class);
//	    System.out.println(objects.size() + " Jugadores:");
//	    
//	  int i = 1;	
//	    while(objects.hasNext()){
//	     Jugadores jug = objects.next();
//	       System.out.println((i++) + "\t: " + jug.getNombre() + "*" + 
//	          jug.getDeporte()+ "*" + jug.getCiudad()+ "*" +
//	          jug.getEdad()+ "*"+ jug.getPais());
//	    }

//		for (Jugadores jug : objects) {
//			System.out.println((i++) + "\t: " + jug.getNombre() + "*" + 
//	                 jug.getDeporte() + "*" + jug.getCiudad() + "*"
//	   		+ jug.getEdad()+ "*" + jug.getPais());
//		}
//
//		OID oid = OIDFactory.buildObjectOID(3);
//		
//		Jugadores jug  = (Jugadores) odb.getObjectFromId(oid);
//
//		System.out.println(jug.getNombre() + "*" + 
//	         jug.getDeporte()+ "*" + jug.getCiudad()+ "*" + jug.getEdad()+ "*" + jug.getPais());
//		
//	  odb.close();

		//buscarPais("Rusia");
		
		sumaEdad();

	}

	private static void buscarPais(String pais) {
		IQuery query = new CriteriaQuery(Jugadores.class);

		Objects<Jugadores> objects2 = odb.getObjects(query);

		IQuery query2 = new CriteriaQuery(Paises.class, Where.equal("nombrepais", pais));
		Objects<Paises> objects3 = odb.getObjects(query2);

		int i = 1;
		for (Jugadores jug : objects2) {
			for (Paises p : objects3) {
				if (jug.getPais().getNombrepais().equals(pais)) {
					System.out.println((i++) + "\t: " + jug.getNombre() + "*" + jug.getDeporte() + "*" + jug.getCiudad()
							+ "*" + jug.getEdad());
				}
			}
		}

		odb.close();

	}
	
	private static void cambiarDeporte() {
		IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Maria"));
		Objects<Jugadores> objetos = odb.getObjects(query);
		// Obtiene solo el primer objeto encontrado
		Jugadores jug = (Jugadores) objetos.getFirst(); //Falta try catch
		jug.setDeporte("vóley-playa"); // Cambia el deporte
		odb.store(jug); // Actualiza el objeto
		odb.commit(); // Valida los cambios
	}
	
	private static void sumaEdad() {
		IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("edad", 15));
		Objects<Jugadores> objetos = odb.getObjects(query);
		int i = 1;
		for (Jugadores jug : objetos) {
			jug.setEdad(jug.getEdad() + 1);
			odb.store(jug);
			odb.commit();
					System.out.println((i++) + "\t: " + jug.getNombre() + "*" + jug.getDeporte() + "*" + jug.getCiudad()
							+ "*" + jug.getEdad());
				
			
		}
	}

}
