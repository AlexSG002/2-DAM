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
    private int codigo;
    private String nombre;
    private String descripcion;
    private int stock;
    private int pvp;
    private int codigoCategoria;
    private String nitProveedor;

    public Producto(int codigo, String nombre, String descripcion, int stock, int pvp, int codigoCategoria, String nitProveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.pvp = pvp;
        this.codigoCategoria = codigoCategoria;
        this.nitProveedor = nitProveedor;
    }

    public Producto() {
        this.codigo = 0;
        this.nombre = "";
        this.descripcion = "";
        this.stock = 0;
        this.pvp = 0;
        this.codigoCategoria = 0;
        this.nitProveedor = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPvp() {
        return pvp;
    }

    public void setPvp(int pvp) {
        this.pvp = pvp;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }
    
    
    
}
