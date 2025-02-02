package cursosAlumnos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre","listaAlumnos"})
public class Curso {

	private String nombre;
	private ArrayList<Alumno>listaAlumnos = new ArrayList<Alumno>();
	public Curso(String nombre, ArrayList<Alumno> listaAlumnos) {
		super();
		this.nombre = nombre;
		this.listaAlumnos = listaAlumnos;
	}
	public Curso() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElementWrapper(name="alumnos")
	@XmlElement(name="alumno")
	public ArrayList<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}
	public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	
	
	
}
