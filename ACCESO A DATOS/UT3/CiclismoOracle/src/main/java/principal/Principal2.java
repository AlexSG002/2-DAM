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

public class Principal2 {
	
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
	    Transaction tx = null;

	    try {
	        tx = sesion.beginTransaction();

	        String hql = "FROM ResumenCamisetas";
	        Query<ResumenCamisetas> q = sesion.createQuery(hql, ResumenCamisetas.class);
	        List<ResumenCamisetas> lista = q.list();

	        for (ResumenCamisetas rc : lista) {
	            ResumenCamisetasId rcId = rc.getId();
	            BigInteger codEquipo = rcId.getCodigoequipo();
	            BigInteger codCiclista = rcId.getCodigociclista();
	            BigInteger codCamiseta = rcId.getCodigocamiseta();

	            Equipos equipo = sesion.get(Equipos.class, codEquipo);
	            Ciclistas ciclista = sesion.get(Ciclistas.class, codCiclista);
	            Camisetas camiseta = sesion.get(Camisetas.class, codCamiseta);

	            StringBuilder mensaje = new StringBuilder();
	            boolean error = false;

	            if (equipo == null) {
	                error = true;
	                mensaje.append("Error, el equipo no existe: ").append(codEquipo).append("\n");
	            }

	            if (camiseta == null) {
	                error = true;
	                mensaje.append("Error, la camiseta no existe: ").append(codCamiseta).append("\n");
	            }

	            if (ciclista == null) {
	                error = true;
	                mensaje.append("Error, el ciclista no existe: ").append(codCiclista).append("\n");
	            }

	            if (error) {
	                System.out.println(mensaje.toString());

	            } else {

	                System.out.println("Correcto: Equipo " + codEquipo 
	                    + ", Ciclista " + codCiclista 
	                    + ", Camiseta " + codCamiseta
	                    + " NVECES: " + rc.getNumveces()
	                    + " IMPORTEPREMIO: " + rc.getImportepremio());
	            }
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        sesion.close();
	    }
	}

	
}