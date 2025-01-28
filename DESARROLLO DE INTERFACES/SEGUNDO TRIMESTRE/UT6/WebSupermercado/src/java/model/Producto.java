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
public class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String stock;
    private String pvp;
    private String codigoCategoria;
    private String nitProveedor;

    public Producto(String codigo, String nombre, String descripcion, String stock, String pvp, String codigoCategoria, String nitProveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.pvp = pvp;
        this.codigoCategoria = codigoCategoria;
        this.nitProveedor = nitProveedor;
    }

    public Producto() {
        this.codigo = "";
        this.nombre = "";
        this.descripcion = "";
        this.stock = "";
        this.pvp = "";
        this.codigoCategoria = "";
        this.nitProveedor = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }
    
    
    
}
