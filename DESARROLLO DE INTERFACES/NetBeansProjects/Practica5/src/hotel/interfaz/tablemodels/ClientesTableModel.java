/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.interfaz.tablemodels;

import hotel.dto.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tarde
 */
public class ClientesTableModel extends AbstractTableModel{
    private List<Cliente> listCliente;
    private String[] columnas = {"Nombre","Apellidos","Check in","Check out","Número de habitación","Tipo de habitación","Equipaje"};
    
    public ClientesTableModel(List<Cliente> listCliente){
        this.listCliente = listCliente;
    }
    
    @Override
    public int getRowCount() {
        return listCliente.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch(i1){
            case 0:
                return listCliente.get(i).getNombre();
            case 1:
                return listCliente.get(i).getApellidos();
            case 2:
                return listCliente.get(i).getCheckin();
            case 3:
                return listCliente.get(i).getCheckout();
            case 4:
                return listCliente.get(i).getHab();
            case 5:
                return listCliente.get(i).getTipoHab();
            case 6:
                return listCliente.get(i).getEquipaje();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return columnas[i];
    }
    
    
}
