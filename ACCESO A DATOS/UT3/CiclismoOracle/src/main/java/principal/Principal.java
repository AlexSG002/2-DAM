package principal;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clases.Camisetas;
import clases.Ciclistas;
import clases.Equipos;
import clases.ResumenCamisetas;
import clases.ResumenCamisetasId;

public class Principal {
	
	private static SessionFactory factory;

	public static void main(String[] args) {

		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		factory = Conexion.getSession(); // Creo la sessionFactory una única vez.
		
		
		llenarResumenCamisetas();

	}

	private static void llenarResumenCamisetas() {
		Session sesion = factory.openSession();
		
		String con ="From RESUMEN_CAMISETAS";
		Query<ResumenCamisetasId> q = sesion.createQuery(con, ResumenCamisetasId.class);
		List<ResumenCamisetasId> lista = q.list();
		Transaction tx = sesion.beginTransaction();
		for(ResumenCamisetasId rc : lista) {
			Equipos codEquipo = sesion.get(Equipos.class, rc.getCodigoequipo());
			int error = 0;
			Camisetas codCamisetas = sesion.get(Camisetas.class, rc.getCodigocamiseta());
			Ciclistas ciclistas = sesion.get(Ciclistas.class, rc.getCodigociclista());
			String mensaje="";
			
			
			if(codEquipo==null) {
				error = 1;
				mensaje = mensaje + "Error, el equipo no existe: "+codEquipo.getCodigoequipo() +"\n";
			}
			
			if(codCamisetas == null) {
				error = 1;
				mensaje = mensaje + "Error, la camiseta no existe: "+codCamisetas.getCodigocamiseta()+"\n";
			}
			
			if(ciclistas == null) {
				error = 1;
				mensaje = mensaje + "Error, el ciclista no existe: "+ciclistas.getCodigociclista()+"\n";
			}
			
			if(error==0) {
				ResumenCamisetasId nuevoResumen = new ResumenCamisetasId();
				nuevoResumen.setCodigocamiseta(codCamisetas.getCodigocamiseta());
			}
			
		}
	}
}