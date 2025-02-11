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
        ArrayList<Proveedor> lista = new ArrayList<>();
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
    
    public static String getProveedor(String nit){
        Connection con = null;
        PreparedStatement st = null;
        try {
            System.out.println("Buscando proveedor para nit: " + nit);
            String SQL = "SELECT * FROM proveedores where nit = ?;";
            con = conexion.conectar();
            if(con==null){
                return null;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, nit);
            ResultSet resultado = st.executeQuery();
            if(resultado.next()){
                String nombreProveedor = resultado.getString("nombre");
                System.out.println("Proveedor encontrado: " + nombreProveedor);
                return resultado.getString("nombre");
            }
            return "--";
        } catch (SQLException ex) {
            return "--";
        }
    }

    public static boolean actualizar(Proveedor p) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        String SQL = "UPDATE Proveedores SET nombre = ?, tlf = ?, correo_electronico = ?, direccion = ? WHERE nit = ?";
        con = conexion.conectar();
        if (con == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos (actualizar proveedor).");
            return false;
        }
        st = con.prepareStatement(SQL);
        st.setString(1, p.getNombre());
        st.setString(2, p.getTlf());
        st.setString(3, p.getCorreoElectronico());
        st.setString(4, p.getDireccion());
        st.setString(5, p.getNit());
        int filas = st.executeUpdate();
        if (filas > 0) {
            System.out.println("✅ Proveedor actualizado correctamente.");
            return true;
        } else {
            System.err.println("⚠️ No se actualizó el proveedor.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("❌ Error SQL al actualizar proveedor: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión en actualizar proveedor: " + ex.getMessage());
        }
    }
}

public static boolean eliminar(String nit) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        String SQL = "DELETE FROM Proveedores WHERE nit = ?";
        con = conexion.conectar();
        if (con == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos (eliminar proveedor).");
            return false;
        }
        st = con.prepareStatement(SQL);
        st.setString(1, nit);
        int filas = st.executeUpdate();
        if (filas > 0) {
            System.out.println("✅ Proveedor eliminado correctamente.");
            return true;
        } else {
            System.err.println("⚠️ No se eliminó el proveedor.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("❌ Error SQL al eliminar proveedor: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión en eliminar proveedor: " + ex.getMessage());
        }
    }
}

public static boolean tieneProductos(String nitProveedor) {
    boolean tieneProductos = false;
    String sql = "SELECT COUNT(*) AS total FROM productos WHERE nit = ?";
    
    try (Connection conn = conexion.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
         ps.setString(1, nitProveedor);
         try (ResultSet rs = ps.executeQuery()) {
             if (rs.next()) {
                 int count = rs.getInt("total");
                 System.out.println("Número de productos asociados al proveedor " + nitProveedor + ": " + count);
                 tieneProductos = (count > 0);
             }
         }
    } catch (SQLException ex) {
         System.out.println("Error en tieneProductos: " + ex.getMessage());
    }
    
    return tieneProductos;
}


    
}
