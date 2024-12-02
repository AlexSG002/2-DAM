package xmlventas;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productos")
public class Productos {
	
	private ArrayList<Producto> lista;

	public Productos(ArrayList<Producto> lista) {
		super();
		this.lista = lista;
	}

	public Productos() {
		super();
	}

	public ArrayList<Producto> getLista() {
		return lista;
	}
	@XmlElement(name = "producto")
	public void setLista(ArrayList<Producto> lista) {
		this.lista = lista;
	}
	
	
	
}
