/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.logica;

import hotel.dto.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarde
 */
public class LogicaNegocio {
    
    public List<Cliente> listaClientes = new ArrayList<>();
    
    public LogicaNegocio(){
        listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente("Roberto","Martín Roldán","10/10/2024","17/10/2024",3,"Individual","Maleta pequeña"));
        listaClientes.add(new Cliente("Luisa","García Fernandez","06/10/2024","11/10/2024",4,"Doble","Dos maletas grandes"));
        listaClientes.add(new Cliente("Enrique","Pérez Sánchez","10/10/2024","13/10/2024",11,"Individual","Maletín"));
        listaClientes.add(new Cliente("Ernesta","Hernández Jiménez","03/10/2024","08/10/2024",1,"Doble","Maleta grande"));
        listaClientes.add(new Cliente("Agustín","Rojas Rojas","10/10/2024","11/10/2024",2,"Individual","Nada"));
        listaClientes.add(new Cliente("Julián","Chamartín Andrade","10/10/2024","17/10/2024",12,"Suite","Tres maletas grandes"));
        listaClientes.add(new Cliente("Angélica","González Morán","06/10/2024","18/10/2024",5,"Doble","Dos maletas grandes y mochila de viaje"));
        listaClientes.add(new Cliente("Martín","Gregorio Fuster","12/10/2024","19/10/2024",7,"Individual","Maleta pequeña"));
        listaClientes.add(new Cliente("Lucía","Fernández Olmedo","10/10/2024","13/10/2024",8,"Individual","Maletín y mochila de viaje"));
        listaClientes.add(new Cliente("Gianluca","Spezia Baresi","10/10/2024","20/10/2024",10,"Suite","Tres maletas grandes y maletín"));
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    
    
    
}
