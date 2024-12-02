/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionclientes.logica;

import gestionclientes.dto.Cliente;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Tarde
 */
public class LogicaNegocio {
    private static List <Cliente> listaClientes = new ArrayList();
    
    public static void anadirCliente(Cliente c){
        listaClientes.add(c);
    }

    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }
    
    
}
