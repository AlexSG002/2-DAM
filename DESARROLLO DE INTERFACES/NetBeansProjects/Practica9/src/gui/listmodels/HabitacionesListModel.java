/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.listmodels;

import dto.Habitaciones;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class HabitacionesListModel extends AbstractListModel{
    private List<Habitaciones>listHabs;

    public HabitacionesListModel(List<Habitaciones> listHabs) {
        this.listHabs = listHabs;
    }
    
    public List<Habitaciones> getListHabs() {
        return listHabs;
    }
    
    
    @Override
    public int getSize() {
       return listHabs.size();
    }

    @Override
    public Object getElementAt(int i) {
        return listHabs.get(i);
    }
    
    
}
