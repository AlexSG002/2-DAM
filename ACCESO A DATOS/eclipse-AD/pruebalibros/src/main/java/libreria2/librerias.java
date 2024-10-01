package libreria2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {"nombre","lugar","listaLibro"})
public class librerias {

	private ArrayList<Libro> listaLibro;
	private String nombre;
	private String lugar;
	public librerias(ArrayList<Libro> listaLibro, String nombre, String lugar) {
		super();
		this.listaLibro = listaLibro;
		this.nombre = nombre;
		this.lugar = lugar;
	}
	public librerias() {
	}
	@XmlElementWrapper(name = "MiListaLibros") 

    @XmlElement(name = "Libro") 
	public ArrayList<Libro> getListaLibro() {
		return listaLibro;
	}
	public void setListaLibro(ArrayList<Libro> listaLibro) {
		this.listaLibro = listaLibro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	
	
}
