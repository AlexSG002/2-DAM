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
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "INSERT INTO productos(codigo, nombre, descripcion, stock, pvp, codigo_categoria, nit_proveedor) values(?,?,?,?,?,?);";
            con = conexion.conectar();
            if(con==null){
                return false;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, p.getCodigo());
            st.setString(2, p.getNombre());
            st.setString(3, p.getDescripcion());
            st.setString(4, p.getStock());
            st.setString(5, p.getCodigoCategoria());
            st.setString(6, p.getNitProveedor());
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
        ArrayList<Producto> lista = null;
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "SELECT * FROM productos;";
            con = conexion.conectar();
            if(con==null){
                return null;
            }
            st = con.prepareStatement(SQL);
            //st.setString(1, cat.getNombre());
            ResultSet resultado = st.executeQuery();
            Producto p;
            while(resultado.next()){
                p = new Producto();
                p.setCodigo(resultado.getString("codigo"));
                p.setNombre(resultado.getString("nombre"));
                p.setDescripcion(resultado.getString("descripcion"));
                p.setStock(resultado.getString("stock"));
                p.setCodigoCategoria(resultado.getString("codigo_categoria"));
                p.setNitProveedor(resultado.getString("nit_proveedor"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
