package modelos;

public class Habitacion {
    private int codHabitacion;
    private int codHotel;
    private String nombreHabitacion;
    private String tipo;

    // Getters y Setters
    public int getCodHabitacion() { return codHabitacion; }
    public void setCodHabitacion(int codHabitacion) { this.codHabitacion = codHabitacion; }

    public int getCodHotel() { return codHotel; }
    public void setCodHotel(int codHotel) { this.codHotel = codHotel; }

    public String getNombreHabitacion() { return nombreHabitacion; }
    public void setNombreHabitacion(String nombreHabitacion) { this.nombreHabitacion = nombreHabitacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
