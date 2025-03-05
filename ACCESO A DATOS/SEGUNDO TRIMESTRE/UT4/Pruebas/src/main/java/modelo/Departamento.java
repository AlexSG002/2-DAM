package modelo;



// Simulación de una clase Departamento (debes tenerla en tu proyecto)
public class Departamento {
    private int deptno;
    private String dnombre;

    public Departamento(int deptno, String dnombre) {
        this.deptno = deptno;
        this.dnombre = dnombre;
    }

    public int getDeptno() {
        return deptno;
    }

    public String getDnombre() {
        return dnombre;
    }
}


