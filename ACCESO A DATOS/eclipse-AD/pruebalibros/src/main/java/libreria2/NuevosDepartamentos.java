package libreria2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MisLibrerias")
public class NuevosDepartamentos {

	private ArrayList<librerias> librerias;

	public NuevosDepartamentos(ArrayList<librerias> librerias) {
		super();
		this.librerias = librerias;
	}

	public NuevosDepartamentos() {
		
	}
	
	@XmlElement(name = "Libreria") 
	public ArrayList<librerias> getLibrerias() {
		return librerias;
	}

	public void setLibrerias(ArrayList<librerias> librerias) {
		this.librerias = librerias;
	}
	
	
	
}
