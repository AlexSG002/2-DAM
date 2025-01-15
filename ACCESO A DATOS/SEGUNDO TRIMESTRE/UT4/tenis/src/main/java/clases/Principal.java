package clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.IQuery;

public class Principal {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("neodatis.test");// Abrir BD
		IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("deporte", "tenis"));

		query.orderByAsc("nombre, edad"); // Ordena ascendentemente
											// por nombre y edad

		// Obtiene todos los jugadores DE LA CONSULTA
		Objects<Jugadores> objects2 = odb.getObjects(query);
		int i=0;
		for (Jugadores jug : objects2) {
			System.out.println((i++) + "\t: " + jug.getNombre() + "*" + jug.getDeporte() + "*" + jug.getCiudad() + "*"
					+ jug.getEdad());
		}
		
		Jugadores j = (Jugadores) odb.getObjects(query).getFirst();
		
		try{
		    IQuery query2 = new CriteriaQuery(Jugadores.class, 
			             Where.equal("deporte", "tenis"));
		     Jugadores j2 = (Jugadores) odb.getObjects(query2).getFirst();
		     System.out.println("Datos Buscados: " + j2.getNombre() + "*" 
			    + j2.getDeporte() + "*" + j2.getCiudad() + "*"
				+ j2.getEdad()); 
		 }catch(IndexOutOfBoundsException e){
			 System.out.printf("OBJETO NO LOCALIZADO");  
		  }
		
	}

}
