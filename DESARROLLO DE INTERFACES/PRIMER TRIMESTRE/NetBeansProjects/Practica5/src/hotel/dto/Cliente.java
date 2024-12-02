/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dto;

/**
 *
 * @author Tarde
 */
public class Cliente {
    private String nombre;
    private String apellidos;
    private String checkin;
    private String checkout;
    private int hab;
    private String tipoHab;
    private String equipaje;

    public Cliente(String nombre, String apellidos, String checkin, String checkout, int hab, String tipoHab, String equipaje) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.checkin = checkin;
        this.checkout = checkout;
        this.hab = hab;
        this.tipoHab = tipoHab;
        this.equipaje = equipaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getHab() {
        return hab;
    }

    public void setHab(int hab) {
        this.hab = hab;
    }

    public String getTipoHab() {
        return tipoHab;
    }

    public void setTipoHab(String tipoHab) {
        this.tipoHab = tipoHab;
    }

    public String getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(String equipaje) {
        this.equipaje = equipaje;
    }
    
    
}
