package ventasArticulos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ventasarticulos")
public class ventasarticulos {
	
	private Articulo articulo;
	private ArrayList<Venta> listaVentas = new ArrayList<Venta>();
	
	public ventasarticulos() {
	
	}
	public ventasarticulos(Articulo articulo, ArrayList<Venta> listaVentas) {
		super();
		this.articulo = articulo;
		this.listaVentas = listaVentas;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	@XmlElementWrapper(name="ventas")
	@XmlElement(name="venta")
	public ArrayList<Venta> getListaVentas() {
		return listaVentas;
	}
	public void setListaVentas(ArrayList<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}
	
	
	
}
