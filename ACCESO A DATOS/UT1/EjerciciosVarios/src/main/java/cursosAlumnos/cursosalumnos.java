package cursosAlumnos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cursosalumnos")
public class cursosalumnos {

	private ArrayList<Curso> listaCursos = new ArrayList<Curso>();

	public cursosalumnos(ArrayList<Curso> listaCursos) {
		super();
		this.listaCursos = listaCursos;
	}

	public cursosalumnos() {
		super();
	}
	@XmlElement(name="curso")
	public ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(ArrayList<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}
	
	
	
}
