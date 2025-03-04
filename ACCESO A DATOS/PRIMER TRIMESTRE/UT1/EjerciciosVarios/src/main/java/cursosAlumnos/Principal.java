package cursosAlumnos;

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
			context = JAXBContext.newInstance(cursosalumnos.class);
			Unmarshaller unmars = context.createUnmarshaller();
			cursosalumnos objeto = (cursosalumnos) unmars.unmarshal(new File("cursosalumnosVer2.xml"));
			// recuperar los datos de las librerias
			ArrayList<Curso> listaCursos = objeto.getListaCursos();
			for(Curso c : listaCursos) {
				float total=0;
				int numAlumnos=0;
				System.out.println("CURSO: "+c.getNombre());
				ArrayList<Alumno> listaAlumnos = c.getListaAlumnos();
				System.out.printf("%18s %10s %n","NOMBRE","NOTA MEDIA");
				System.out.printf("%18s %10s %n","--------------------","----------");
				for(Alumno a : listaAlumnos) {
					System.out.printf("%18s %10s %n",a.getNombre(), a.getNotamedia());
					numAlumnos++;
					total+=a.getNotamedia();
				}
				System.out.printf("%18s %10s %n","--------------------","----------");
				System.out.printf("%29s %n","MEDIA: "+Math.round((total/numAlumnos)*100.0)/100.0);
				
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}