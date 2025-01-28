/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tarde
 */
public class Proveedor {
    private String nit;
    private String nombre;
    private String tlf;
    private String direccion;
    private String correoElectronico;

    public Proveedor(String nit, String nombre, String tlf, String direccion, String correoElectronico) {
        this.nit = nit;
        this.nombre = nombre;
        this.tlf = tlf;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
    }

    public Proveedor() {
        this.nit = "";
        this.nombre = "";
        this.tlf = "";
        this.direccion = "";
        this.correoElectronico = "";
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    
    
}
