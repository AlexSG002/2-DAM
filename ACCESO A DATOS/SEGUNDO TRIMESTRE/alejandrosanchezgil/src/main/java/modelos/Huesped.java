package modelos;

public class Huesped {
    private int codHuesped;
    private String nombre;
    private String nif;
    private String tlf;
    private String direccion;
    private String pais;

    // Getters y Setters
    public int getCodHuesped() { return codHuesped; }
    public void setCodHuesped(int codHuesped) { this.codHuesped = codHuesped; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getTlf() { return tlf; }
    public void setTlf(String tlf) { this.tlf = tlf; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}
