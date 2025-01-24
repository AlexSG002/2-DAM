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
import model.Categoria;

/**
 *
 * @author Tarde
 */
public class CategoriaDao {
    public static boolean registrar(Categoria cat){
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "INSERT INTO categorías(nombre) values('?');";
            con = conexion.conectar();
            if(con==null){
                return false;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, cat.getNombre());
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
                Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
    }
    
    public static ArrayList<Categoria> listar(){
        ArrayList<Categoria> lista = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;
        try {
            String SQL = "SELECT * FROM categorías;";
            con = conexion.conectar();
            if(con==null){
                return null;
            }
            st = con.prepareStatement(SQL);
            //st.setString(1, cat.getNombre());
            resultado = st.executeQuery();
            Categoria cat;
            while(resultado.next()){
                cat = new Categoria();
                cat.setCodigo(resultado.getInt("codigo"));
                cat.setNombre(resultado.getString("nombre"));
                lista.add(cat);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
