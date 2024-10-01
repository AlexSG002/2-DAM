package xmlproductos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productos")
public class Productos {

	private ArrayList<Producto> lista = new ArrayList<Producto>();

	public Productos(ArrayList<Producto> lista) {
		super();
		this.lista = lista;
	}

	public Productos() {
	}

	@XmlElement(name = "producto")
	public ArrayList<Producto> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Producto> lista) {
		this.lista = lista;
	}
	
	
	
}
