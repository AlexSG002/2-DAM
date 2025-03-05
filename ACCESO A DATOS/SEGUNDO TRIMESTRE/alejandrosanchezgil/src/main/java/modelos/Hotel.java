package modelos;

public class Hotel {
    private int codHotel;
    private String nombre;
    private String ciudad;
    private String pais;
    private int tasa;

    // Getters y Setters
    public int getCodHotel() { return codHotel; }
    public void setCodHotel(int codHotel) { this.codHotel = codHotel; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public int getTasa() { return tasa; }
    public void setTasa(int tasa) { this.tasa = tasa; }
}
