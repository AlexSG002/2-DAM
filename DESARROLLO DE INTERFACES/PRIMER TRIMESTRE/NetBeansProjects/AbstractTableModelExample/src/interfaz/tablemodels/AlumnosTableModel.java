/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.tablemodels;

import beans.Alumno;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tarde
 */
public class AlumnosTableModel extends AbstractTableModel{
    private List<Alumno> listAlumno;
    private String[] columnas = {"Nombre","Curso"};
    
    public AlumnosTableModel(List<Alumno> listAlumno){
        this.listAlumno = listAlumno;
    }
    
    @Override
    public int getRowCount() {
        return listAlumno.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch(i1){
            case 0:
                return listAlumno.get(i).getNombre();
            case 1:
                return listAlumno.get(i).getCurso();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return columnas[i];
    }
    
    
}
