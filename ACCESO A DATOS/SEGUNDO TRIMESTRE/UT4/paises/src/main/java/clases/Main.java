package clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Main {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("equipos.db");
		
		Paises España = new Paises(1,"España");
		Paises Rusia = new Paises(2, "Rusia");
		Paises Italia = new Paises(3, "Italia");
		
		Jugadores j1 = new Jugadores("Maria","voleibol", "Madrid",14, España);
		Jugadores j2 = new Jugadores("Miguel","tenis", "Madrid",15, España);
		Jugadores j3 = new Jugadores("Mario", "baloncesto","Guadalajara",15, Rusia);
		Jugadores j4 = new Jugadores("Alicia","tenis","Madrid",14, Italia);
		
		odb.store(j1); 
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		
		odb.close();
		
	}
}
