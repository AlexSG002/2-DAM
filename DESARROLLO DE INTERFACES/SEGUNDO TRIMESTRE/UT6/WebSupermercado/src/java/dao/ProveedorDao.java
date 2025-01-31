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

    public static boolean registrar(Proveedor p) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "INSERT INTO Proveedores(nit, nombre, tlf, correo_electronico, direccion) VALUES(?,?,?,?,?);";
            con = conexion.conectar();
            if (con == null) {
                return false;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, p.getNit());
            st.setString(2, p.getNombre());
            st.setString(3, p.getTlf());
            st.setString(4, p.getCorreoElectronico());
            st.setString(5, p.getDireccion());

            return st.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, "Error al registrar proveedor", ex);
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, "Error cerrando la conexión", ex);
            }
        }
    }

    public static ArrayList<Proveedor> listar() {
        Connection con = null;
        PreparedStatement st = null;
        ArrayList<Proveedor> lista = null;
        try {
            String SQL = "SELECT * FROM proveedores;";
            con = conexion.conectar();
            if (con == null) {
                return null;
            }
            st = con.prepareStatement(SQL);
            //st.setString(1, cat.getNombre());
            ResultSet resultado = st.executeQuery();
            Proveedor p;
            while (resultado.next()) {
                p = new Proveedor();
                p.setNit(resultado.getString("nit"));
                p.setNombre(resultado.getString("nombre"));
                p.setTlf(resultado.getString("tlf"));
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
