package empledep;

import java.io.Serializable;
import java.sql.Date;

public class Empleado implements Serializable{
	private int empno;
	private String ape;
	private String oficio;
	private int dir;
	private Date fecha;
	private float salario;
	private float comision;
	private int depno;
	
	
	
	public Empleado() {

	}
	public Empleado(int empno, String ape, String oficio, int dir, Date fecha, float salario, float comision,
			int depno) {
		super();
		this.empno = empno;
		this.ape = ape;
		this.oficio = oficio;
		this.dir = dir;
		this.fecha = fecha;
		this.salario = salario;
		this.comision = comision;
		this.depno = depno;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getApe() {
		return ape;
	}
	public void setApe(String ape) {
		this.ape = ape;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public int getDepno() {
		return depno;
	}
	public void setDepno(int depno) {
		this.depno = depno;
	}
	@Override
	public String toString() {
		return "Empleado [empno=" + empno + ", ape=" + ape + ", oficio=" + oficio + ", dir=" + dir + ", fecha=" + fecha
				+ ", salario=" + salario + ", comision=" + comision + ", depno=" + depno + "]";
	}
	
	
	
}
