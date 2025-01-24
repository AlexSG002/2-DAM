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
import model.Proveedor;

/**
 *
 * @author Tarde
 */
public class ProveedorDao {
    public static boolean registrar(Proveedor p){
        try {
            String SQL = "INSERT INTO Proveedores(nombre) values('?');";
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
    
    public static ArrayList<Proveedor> listar(){
        try {
            String SQL = "SELECT * FROM proveedores;";
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            //st.setString(1, cat.getNombre());
            ResultSet resultado = st.executeQuery();
            ArrayList<Proveedor> lista = null;
            Proveedor p;
            while(resultado.next()){
                p = new Proveedor();
                p.setNit(resultado.getString("nit"));
                p.setNombre(resultado.getString("nombre"));
                p.setTlf(resultado.getInt("tlf"));
                p.setCorreoElectronico("correo_electronico");
                p.setDireccion("direccion");
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
