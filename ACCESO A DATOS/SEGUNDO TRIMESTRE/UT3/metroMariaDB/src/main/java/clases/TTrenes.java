package clases;
// Generated 9 dic 2024, 16:11:18 by Hibernate Tools 5.6.15.Final

/**
 * TTrenes generated by hbm2java
 */
public class TTrenes implements java.io.Serializable {

	private int codTren;
	private TCocheras TCocheras;
	private TLineas TLineas;
	private String nombre;
	private String tipo;

	public TTrenes() {
	}

	public TTrenes(int codTren, TCocheras TCocheras, TLineas TLineas, String nombre, String tipo) {
		this.codTren = codTren;
		this.TCocheras = TCocheras;
		this.TLineas = TLineas;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getCodTren() {
		return this.codTren;
	}

	public void setCodTren(int codTren) {
		this.codTren = codTren;
	}

	public TCocheras getTCocheras() {
		return this.TCocheras;
	}

	public void setTCocheras(TCocheras TCocheras) {
		this.TCocheras = TCocheras;
	}

	public TLineas getTLineas() {
		return this.TLineas;
	}

	public void setTLineas(TLineas TLineas) {
		this.TLineas = TLineas;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
