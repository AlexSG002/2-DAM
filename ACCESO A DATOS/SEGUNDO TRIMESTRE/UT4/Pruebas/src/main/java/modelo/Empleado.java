package modelo;

public class Empleado {
    private int id;
    private String nombre;
    private String puesto;
    private int deptNo; // Relación con Departamento

    public Empleado(int id, String nombre, String puesto, int deptNo) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.deptNo = deptNo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPuesto() { return puesto; }
    public int getDeptNo() { return deptNo; }
}
