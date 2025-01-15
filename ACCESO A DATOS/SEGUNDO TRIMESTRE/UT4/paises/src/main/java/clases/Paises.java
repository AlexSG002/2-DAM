package clases;

public class Paises {

	private int id; 
	private String nombrepais;
	
	public Paises(int id, String nombrepais) {
		this.id = id;
		this.nombrepais = nombrepais;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombrepais() {
		return nombrepais;
	}
	public void setNombrepais(String nombrepais) {
		this.nombrepais = nombrepais;
	}
	@Override
	public String toString() {
		{return nombrepais;}
	}
	
}
