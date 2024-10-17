package nuevosDep;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"deptNo","dnombre","loc"})

public class Departamento {
		
	private int deptNo;
	private String dnombre;
	private String loc;
	
	public Departamento(int deptNo, String dnombre, String loc) {
		super();
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
	}
	public Departamento() {

	}
	@XmlElement (name = "deptno")
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	@XmlElement (name = "dnombre")
	public String getDnombre() {
		return dnombre;
	}
	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}
	@XmlElement (name = "localidad")
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
