/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tablemodels;

import dto.Habitaciones;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tarde
 */
public class HabitacionesTableModel extends AbstractTableModel{

    private List<Habitaciones>listHabs;
     private String[] columnas = {"Número de habitación","Tipo de habitación","Disponibilidad"};

    public HabitacionesTableModel(List<Habitaciones> listHabs) {
        this.listHabs = listHabs;
    }
     
     
    @Override
    public int getRowCount() {
        return listHabs.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch(i1){
            case 0:
                return listHabs.get(i).getNumeroHab();
            case 1:
                return listHabs.get(i).getTipoHab();
            case 2:
                return listHabs.get(i).getDisponibilidad();
        }
        return null;
    }
    @Override
    public String getColumnName(int i) {
        return columnas[i];
    }
}
