package datos;
// Generated 9 dic 2024, 22:52:13 by Hibernate Tools 6.5.1.Final

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Tramospuertos generated by hbm2java
 */
public class Tramospuertos implements java.io.Serializable {

	private BigInteger codigotramo;
	private Etapas etapas;
	private Ciclistas ciclistas;
	private String nombretramo;
	private BigDecimal km;
	private BigInteger categoria;
	private String pendiente;

	public Tramospuertos() {
	}

	public Tramospuertos(BigInteger codigotramo, Etapas etapas, Ciclistas ciclistas, String nombretramo, BigDecimal km,
			BigInteger categoria, String pendiente) {
		this.codigotramo = codigotramo;
		this.etapas = etapas;
		this.ciclistas = ciclistas;
		this.nombretramo = nombretramo;
		this.km = km;
		this.categoria = categoria;
		this.pendiente = pendiente;
	}

	public BigInteger getCodigotramo() {
		return this.codigotramo;
	}

	public void setCodigotramo(BigInteger codigotramo) {
		this.codigotramo = codigotramo;
	}

	public Etapas getEtapas() {
		return this.etapas;
	}

	public void setEtapas(Etapas etapas) {
		this.etapas = etapas;
	}

	public Ciclistas getCiclistas() {
		return this.ciclistas;
	}

	public void setCiclistas(Ciclistas ciclistas) {
		this.ciclistas = ciclistas;
	}

	public String getNombretramo() {
		return this.nombretramo;
	}

	public void setNombretramo(String nombretramo) {
		this.nombretramo = nombretramo;
	}

	public BigDecimal getKm() {
		return this.km;
	}

	public void setKm(BigDecimal km) {
		this.km = km;
	}

	public BigInteger getCategoria() {
		return this.categoria;
	}

	public void setCategoria(BigInteger categoria) {
		this.categoria = categoria;
	}

	public String getPendiente() {
		return this.pendiente;
	}

	public void setPendiente(String pendiente) {
		this.pendiente = pendiente;
	}

}
