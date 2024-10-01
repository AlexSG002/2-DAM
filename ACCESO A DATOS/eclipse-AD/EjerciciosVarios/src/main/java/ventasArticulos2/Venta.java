package ventasArticulos2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"numVenta","unidades","cliente","fecha"})
public class Venta {

	private int numVenta;
	private int unidades;
	private String cliente;
	private String fecha;
	
	public Venta(int numVenta, int unidades, String cliente, String fecha) {
		super();
		this.numVenta = numVenta;
		this.unidades = unidades;
		this.cliente = cliente;
		this.fecha = fecha;
	}
	public Venta() {
		
	}
	public int getNumVenta() {
		return numVenta;
	}
	@XmlElement(name="numventa")
	public void setNumVenta(int numVenta) {
		this.numVenta = numVenta;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	@XmlElement(name="nombrecliente")
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
