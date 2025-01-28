package principal;

import java.sql.Date;

public class Principal {

	public void insertarEstudiante(String nombre, String direccion, String telefono, Date fechaAlta) {
	    if (nombre == null || nombre.trim().isEmpty() || 
	        direccion == null || direccion.trim().isEmpty() ||
	        telefono == null || telefono.trim().isEmpty()) {
	        System.out.println("Los datos no pueden estar vacíos, comprueba datos.");
	        return;
	    }

	    Session session = factory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();

	        Query<Long> qCount = session.createQuery("select count(e) from Estudiantes e where e.nombre = :nom", Long.class);
	        qCount.setParameter("nom", nombre);
	        long count = qCount.uniqueResult();
	        if (count > 0) {
	            System.out.println("Ya existe un estudiante con ese nombre, no se ha podido insertar.");
	            return;
	        }

	        Query<Integer> qMax = session.createQuery("select max(e.codEstudiante) from Estudiantes e", Integer.class);
	        Integer maxCod = qMax.uniqueResult();
	        int nuevoCod = (maxCod == null) ? 1 : maxCod + 1;

	        if (fechaAlta == null) {
	            fechaAlta = new Date();
	        }

	        Estudiantes est = new Estudiantes();
	        est.setCodEstudiante(nuevoCod);
	        est.setNombre(nombre);
	        est.setDireccion(direccion);
	        est.setTelefono(telefono);
	        est.setFechaAlta(fechaAlta);

	        session.persist(est);
	        tx.commit();

	        System.out.println("Estudiante insertado correctamente con el código " + nuevoCod);
	    } catch (SQLException e) {
	        if (tx != null) tx.rollback();
	        System.out.println("Error SQL: " + e.getMessage());
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        session.close();
	    }
	}

	public void listarProyecto(int codProyecto) {
	    Session session = factory.openSession();
	    try {
	        Proyectos p = session.get(Proyectos.class, codProyecto);
	        if (p == null) {
	            System.out.println("No existe el proyecto con código: " + codProyecto);
	            return;
	        }

	        System.out.println("COD-PROYECTO: " + p.getCodProyecto() + "  NOMBRE: " + p.getNombre());
	        System.out.println("FECHA INICIO: " + p.getFechaInicio() + ", FECHA FIN: " + p.getFechaFin());
	        System.out.println("PRESUPUESTO: " + p.getPresupuesto() + ", EXTRAAPORTACIÓN: " + p.getExtraAportacion());
	        System.out.println("--------------------------------------------------------------------");

	        Set<Patrocina> patrocinios = p.getPatrocinios();
	        if (patrocinios == null || patrocinios.isEmpty()) {
	            System.out.println("NINGUNA ENTIDAD PATROCINA ESTE PROYECTO.");
	        } else {
	            System.out.println("LISTA DE ENTIDADES QUE PATROCINAN EL PROYECTO");
	            System.out.println("Código   Descripción     Importe Aportación    Fecha Aportación");
	            double totalAportaciones = 0.0;
	            for (Patrocina pat : patrocinios) {
	                Entidades ent = pat.getEntidad();
	                System.out.printf("%-8d %-15s %-20.2f %s%n", 
	                    ent.getCodEntidad(), ent.getDescripcion(), 
	                    pat.getImporteAportacion(), pat.getFechaAportacion());
	                totalAportaciones += pat.getImporteAportacion();
	            }
	            double presupuestoTotal = p.getPresupuesto() + totalAportaciones;
	            System.out.println("--------------------------------------------------------------------");
	            System.out.println("TOTAL APORTACIONES: " + totalAportaciones);
	            System.out.println("PRESUPUESTO TOTAL: " + presupuestoTotal);
	        }

	        Set<Participa> participaciones = p.getParticipaciones(); // según mapeo
	        if (participaciones == null || participaciones.isEmpty()) {
	            System.out.println("NINGÚN ESTUDIANTE PERTENECE A ESTE PROYECTO.");
	        } else {
	            System.out.println("LISTA DE ESTUDIANTES QUE PARTICIPAN EN EL PROYECTO");
	            System.out.println("CodPar  NumApt  NombreEstudiante  Direccion     ...");
	            int totalNumApt = 0;
	            double totalAportEst = 0.0;
	            for (Participa part : participaciones) {
	                Estudiantes est = part.getEstudiante();
	                int numApt = part.getNumAportaciones();
	                totalNumApt += numApt;


	                double totAport = numApt * p.getExtraAportacion();
	                totalAportEst += totAport;

	                System.out.printf("%d     %d     %s     %s   ...%n", 
	                    part.getCodParticipacion(), numApt, est.getNombre(), est.getDireccion());
	            }

	            System.out.println("--------------------------------------------------------------------");
	            System.out.println("TOTALES");
	            System.out.println("Total num aportaciones estudiantes: " + totalNumApt);
	            System.out.println("Total aportaciones estudiantes (TotAport): " + totalAportEst);
	        }

	    } finally {
	        session.close();
	    }
	}

	
	
}
