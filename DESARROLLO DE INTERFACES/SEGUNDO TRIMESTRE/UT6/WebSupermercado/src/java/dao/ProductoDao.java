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
import model.Producto;

/**
 *
 * @author Tarde
 */
public class ProductoDao {
    public static boolean registrar(Producto p){
        try {
            String SQL = "INSERT INTO productos(nombre) values('?');";
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, p.getNombre());
            if(st.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static ArrayList<Producto> listar(){
        try {
            String SQL = "SELECT * FROM categorías;";
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            //st.setString(1, cat.getNombre());
            ResultSet resultado = st.executeQuery();
            ArrayList<Producto> lista = null;
            Producto p;
            while(resultado.next()){
                p = new Producto();
                p.setCodigo(resultado.getInt("codigo"));
                p.setNombre(resultado.getString("nombre"));
                p.setDescripcion(resultado.getString("descripcion"));
                p.setStock(resultado.getInt("stock"));
                p.setCodigoCategoria(resultado.getInt("codigo_categoria"));
                p.setNitProveedor(resultado.getString("nit_proveedor"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
