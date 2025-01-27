/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Editorial;


/**
 *
 * @author Tarde
 */
public class EditorialDao {
    public static boolean registrar(Editorial edit){
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "INSERT INTO editoriales(nit, nombre, telefono, direccion, email, sitioweb) values(?,?,?,?,?,?);";
            con = conexion.conectar();
            if(con==null){
                return false;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, edit.getNit());
            st.setString(2, edit.getNombre());
            st.setString(3, edit.getTelefono());
            st.setString(4, edit.getDireccion());
            st.setString(5, edit.getEmail());
            st.setString(6, edit.getSitioweb());
            if(st.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }finally{
            try{
                if(st!=null){
                    st.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger(EditorialDao.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
    }
    
    public static ArrayList<Editorial> listar(){
        ArrayList<Editorial> lista = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;
        try {
            String SQL = "SELECT * FROM editoriales;";
            con = conexion.conectar();
            if(con==null){
                return null;
            }
            st = con.prepareStatement(SQL);
            //st.setString(1, cat.getNombre());
            resultado = st.executeQuery();
            Editorial edit;
            while(resultado.next()){
                edit = new Editorial();
                edit.setNit(resultado.getString("nit"));
                edit.setNombre(resultado.getString("nombre"));
                edit.setTelefono(resultado.getString("telefono"));
                edit.setDireccion(resultado.getString("direccion"));
                edit.setEmail(resultado.getString("email"));
                edit.setSitioweb(resultado.getString("sitioweb"));
                lista.add(edit);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
