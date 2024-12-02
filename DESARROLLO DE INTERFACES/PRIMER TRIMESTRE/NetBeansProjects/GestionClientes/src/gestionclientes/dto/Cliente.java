/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionclientes.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tarde
 */
public class Cliente {
    private String nombre;
    private String apellidos;
    private Date fechaAlta;
    private String provincia;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");

    public Cliente(String nombre, String apellidos, Date fechaAlta, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaAlta = fechaAlta;
        this.provincia = provincia;
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String[] toArrayString(){
        String[] s = new String[4];
        s[0] = nombre;
        s[1] = apellidos;
        s[2] = sdf.format(fechaAlta);
        s[3] = provincia;
        return s;
    }
    
}
