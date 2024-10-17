package creardepartamentosxml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement()
public class Departamentos {

	private ArrayList<Dep> dep;

	public Departamentos() {}
	
	public Departamentos(ArrayList<Dep> dep) {
		super();
		this.dep = dep;
	}

	public ArrayList<Dep> getDep() {
		return dep;
	}

	public void setDep(ArrayList<Dep> dep) {
		this.dep = dep;
	}

	
	
	
	
	
	
	
}
