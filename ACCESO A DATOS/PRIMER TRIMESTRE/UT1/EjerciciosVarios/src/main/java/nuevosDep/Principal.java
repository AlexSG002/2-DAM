package nuevosDep;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class Principal {

	public static void main(String[] args) {
		leerXML();
	}
	private static void leerXML() {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(NuevosDepartamentos.class);
			Unmarshaller unmars = context.createUnmarshaller();
			NuevosDepartamentos objeto = (NuevosDepartamentos) unmars.unmarshal(new File("NuevosDep.xml"));
			// recuperar los datos de las librerias
			ArrayList<Departamento> departamentos = objeto.getDepartamentos();
			// recorrer las librerias
			for (Departamento dep : departamentos) { 
				
		
				
				System.out.println("Cod: "+dep.getDeptNo()+", nombre: "+dep.getDnombre()+", localidad: "+dep.getLoc());
			
				
				
	      } 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
