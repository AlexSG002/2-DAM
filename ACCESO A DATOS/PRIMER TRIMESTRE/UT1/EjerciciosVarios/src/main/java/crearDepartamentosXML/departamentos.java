package crearDepartamentosXML;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="departamentos")
public class departamentos {

	private ArrayList<dep> listadep = new ArrayList<dep>();

	public departamentos(ArrayList<dep> listadep) {
		super();
		this.listadep = listadep;
	}
	
	public departamentos() {
		
	}

	public ArrayList<dep> getListadep() {
		return listadep;
	}

	public void setListadep(ArrayList<dep> listadep) {
		this.listadep = listadep;
	}
	
}
