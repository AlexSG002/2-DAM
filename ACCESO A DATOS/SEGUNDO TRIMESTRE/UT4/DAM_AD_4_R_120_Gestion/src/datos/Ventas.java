package datos;

public class Ventas {

	private int codventa;
	private Articulos codarti;
    private Clientes numcli ;
	private int univen; 
	private String fecha; 
	private float preciofinal;

	public Ventas(){}
	
	public Ventas(int codventa, Articulos codarti, Clientes numcli, int univen,
			String fecha, float preciofinal) {
		super();
		this.codventa = codventa;
		this.codarti = codarti;
		this.numcli = numcli;
		this.univen = univen;
		this.fecha = fecha;
		this.preciofinal = preciofinal;
	}

	public int getCodventa() {
		return codventa;
	}

	public void setCodventa(int codventa) {
		this.codventa = codventa;
	}

	public Articulos getCodarti() {
		return codarti;
	}

	public void setCodarti(Articulos codarti) {
		this.codarti = codarti;
	}

	public Clientes getNumcli() {
		return numcli;
	}

	public void setNumcli(Clientes numcli) {
		this.numcli = numcli;
	}

	public int getUniven() {
		return univen;
	}

	public void setUniven(int univen) {
		this.univen = univen;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getPreciofinal() {
		return preciofinal;
	}

	public void setPreciofinal(float preciofinal) {
		this.preciofinal = preciofinal;
	}
	
	
	
}
