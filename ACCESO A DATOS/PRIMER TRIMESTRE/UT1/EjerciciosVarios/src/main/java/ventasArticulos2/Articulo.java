package ventasArticulos2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"codigo","denominacion","stock","precio","ventas"})
public class Articulo {

	private String codigo;
	private String denominacion;
	private int stock;
	private int precio;
	private ArrayList<Venta> ventas = new ArrayList<Venta>();
	
	public Articulo(String codigo, String denominacion, int stock, int precio, ArrayList<Venta> ventas) {
		super();
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.stock = stock;
		this.precio = precio;
		this.ventas = ventas;
	}
	public Articulo() {
		
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	@XmlElementWrapper(name="ventas")
	@XmlElement(name="venta")
	public ArrayList<Venta> getVentas() {
		return ventas;
	}
	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	
}
