/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author berto
 */

public class Pelicula {
    private int id;
    private String nombre;
    private String genero;
    private boolean visto;
    private int cineId; // ID del cine asociado

    // Constructor
    public Pelicula(int id, String nombre, String genero, boolean visto, int cineId) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.visto = visto;
        this.cineId = cineId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public int getCineId() {
        return cineId;
    }

    public void setCineId(int cineId) {
        this.cineId = cineId;
    }
}

