/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dto.Habitaciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class LogicaNegocio {
    
    public List<Habitaciones> listaHabitaciones = new ArrayList<>();
    
    public LogicaNegocio(){
        listaHabitaciones = new ArrayList<>();
        listaHabitaciones.add(new Habitaciones(1,"Sencilla",""));
        listaHabitaciones.add(new Habitaciones(2,"Doble",""));
        listaHabitaciones.add(new Habitaciones(3,"Suite",""));
        listaHabitaciones.add(new Habitaciones(4,"Sencilla",""));
        listaHabitaciones.add(new Habitaciones(5,"Doble",""));
    }

    public List<Habitaciones> getListaHabitaciones() {
        return listaHabitaciones;
    }
    
    
    
}
