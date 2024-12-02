/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Habitaciones {
    private int tipoHabRand = (int)(Math.random()*(3-0));
    private int dispRand = (int)(Math.random()*(2-0));
    private int NumeroHab;
    private String tipoHab;
    private String[] disponibilidad={"Disponible","Ocupada"};

    public Habitaciones(int NumeroHab, String tipoHab, String disponibilidad) {
        this.NumeroHab = NumeroHab;
        this.tipoHab = tipoHab;
        this.disponibilidad = this.disponibilidad;
    }

    public int getNumeroHab() {
        return NumeroHab;
    }

    public String getTipoHab() {
        return tipoHab;
    }

    public String getDisponibilidad() {
        return disponibilidad[dispRand];
    }
    
    
    
}
