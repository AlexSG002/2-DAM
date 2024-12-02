package xmlventas;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder= {"codigo","nombre","existencias","precio","lista"})
public class Producto {

	private int codigo;
	private String nombre;
	private int existencias;
	private double precio;
	private ArrayList<Venta> lista;
	
	public Producto(int codigo, String nombre, int existencias, double precio, ArrayList<Venta> lista) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.existencias = existencias;
		this.precio = precio;
		this.lista = lista;
		
	}

	public Producto() {
		
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@XmlElementWrapper(name = "ventas")
	@XmlElement(name = "venta")
	public ArrayList<Venta> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Venta> lista) {
		this.lista = lista;
	}
	
	
	
}
