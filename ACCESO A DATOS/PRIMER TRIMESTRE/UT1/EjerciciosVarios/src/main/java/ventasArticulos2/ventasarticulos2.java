package ventasArticulos2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ventasarticulos")
public class ventasarticulos2 {
	
	private ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
	
	public ventasarticulos2() {
	
	}
	public ventasarticulos2(ArrayList<Articulo> listaArticulos, ArrayList<Venta> listaVentas) {
		super();
		this.listaArticulos = listaArticulos;
	
	}
	@XmlElementWrapper(name="articulo")
	@XmlElement(name="artic")
	public ArrayList<Articulo> getArticulo() {
		return listaArticulos;
	}
	public void setArticulo(ArrayList<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}
}
	
	
	