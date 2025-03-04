package clases;
// Generated 9 dic 2024, 16:14:40 by Hibernate Tools 5.5.9.Final

import java.util.HashSet;
import java.util.Set;

/**
 * TEstaciones generated by hbm2java
 */
public class TEstaciones implements java.io.Serializable {

	private int codEstacion;
	private String nombre;
	private String direccion;
	private Set TAccesoses = new HashSet(0);
	private Set TLineaEstacions = new HashSet(0);

	public TEstaciones() {
	}

	public TEstaciones(int codEstacion, String nombre, String direccion) {
		this.codEstacion = codEstacion;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public TEstaciones(int codEstacion, String nombre, String direccion, Set TAccesoses, Set TLineaEstacions) {
		this.codEstacion = codEstacion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.TAccesoses = TAccesoses;
		this.TLineaEstacions = TLineaEstacions;
	}

	public int getCodEstacion() {
		return this.codEstacion;
	}

	public void setCodEstacion(int codEstacion) {
		this.codEstacion = codEstacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set getTAccesoses() {
		return this.TAccesoses;
	}

	public void setTAccesoses(Set TAccesoses) {
		this.TAccesoses = TAccesoses;
	}

	public Set getTLineaEstacions() {
		return this.TLineaEstacions;
	}

	public void setTLineaEstacions(Set TLineaEstacions) {
		this.TLineaEstacions = TLineaEstacions;
	}

}
