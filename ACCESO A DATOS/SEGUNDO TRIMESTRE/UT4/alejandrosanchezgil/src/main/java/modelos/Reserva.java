package modelos;

import java.util.Date;

public class Reserva {
    private int codReserva;
    private int codHuesped;
    private int codHabitacion;
    private String clase;
    private Date fechaComienzo;
    private Date fechaFin;
    private int numeroHuespedes;
    private double pvp;

    // Getters y Setters
    public int getCodReserva() { return codReserva; }
    public void setCodReserva(int codReserva) { this.codReserva = codReserva; }

    public int getCodHuesped() { return codHuesped; }
    public void setCodHuesped(int codHuesped) { this.codHuesped = codHuesped; }

    public int getCodHabitacion() { return codHabitacion; }
    public void setCodHabitacion(int codHabitacion) { this.codHabitacion = codHabitacion; }

    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }

    public Date getFechaComienzo() { return fechaComienzo; }
    public void setFechaComienzo(Date fechaComienzo) { this.fechaComienzo = fechaComienzo; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public int getNumeroHuespedes() { return numeroHuespedes; }
    public void setNumeroHuespedes(int numeroHuespedes) { this.numeroHuespedes = numeroHuespedes; }

    public double getPvp() { return pvp; }
    public void setPvp(double pvp) { this.pvp = pvp; }
}
