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
    public static boolean registrar(Producto p) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        String SQL = "INSERT INTO productos(codigo, nombre, descripcion, stock, pvp, codigo_categoria, nit_proveedor) VALUES(?,?,?,?,?,?,?);";
        con = conexion.conectar();
        if (con == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos.");
            return false;
        }
        st = con.prepareStatement(SQL);
        st.setString(1, p.getCodigo());
        st.setString(2, p.getNombre());
        st.setString(3, p.getDescripcion());
        st.setInt(4, Integer.parseInt(p.getStock())); // Asegura que es un número
        st.setDouble(5, Double.parseDouble(p.getPvp())); // Convierte a double
        st.setInt(6, Integer.parseInt(p.getCodigoCategoria()));
        st.setString(7, p.getNitProveedor());

        int filasAfectadas = st.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("✅ Producto insertado correctamente.");
            return true;
        } else {
            System.err.println("⚠️ No se insertó ningún producto.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("❌ Error SQL: " + ex.getMessage());
        ex.printStackTrace();
        return false;
    } catch (NumberFormatException ex) {
        System.err.println("❌ Error: Conversión de número inválida. Verifica stock y pvp.");
        ex.printStackTrace();
        return false;
    } finally {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión: " + ex.getMessage());
        }
    }
}

    public static boolean actualizar(Producto p) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "UPDATE productos SET nombre=?, descripcion=?, stock=?, pvp=?, codigo_categoria=?, nit_proveedor=? WHERE codigo=?";
            con = conexion.conectar();
            if (con == null) {
                System.err.println("❌ Error: No se pudo conectar a la base de datos.");
                return false;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, p.getNombre());
            st.setString(2, p.getDescripcion());
            st.setInt(3, Integer.parseInt(p.getStock()));  // Conversión a entero
            st.setDouble(4, Double.parseDouble(p.getPvp()));  // Conversión a double
            st.setInt(5, Integer.parseInt(p.getCodigoCategoria()));
            st.setString(6, p.getNitProveedor());
            st.setString(7, p.getCodigo());
            
            int filasAfectadas = st.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Producto actualizado correctamente.");
                return true;
            } else {
                System.err.println("⚠️ No se actualizó el producto.");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("❌ Error SQL al actualizar: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } catch (NumberFormatException ex) {
            System.err.println("❌ Error: Conversión de número inválida en actualizar.");
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("⚠️ Error cerrando conexión en actualizar: " + ex.getMessage());
            }
        }
    }
    
    public static boolean eliminar(String codigo) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "DELETE FROM productos WHERE codigo=?";
            con = conexion.conectar();
            if (con == null) {
                System.err.println("❌ Error: No se pudo conectar a la base de datos.");
                return false;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, codigo);
            
            int filasAfectadas = st.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Producto eliminado correctamente.");
                return true;
            } else {
                System.err.println("⚠️ No se eliminó el producto.");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("❌ Error SQL al eliminar: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("⚠️ Error cerrando conexión en eliminar: " + ex.getMessage());
            }
        }
    }
    
    public static ArrayList<Producto> listar(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
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
                p.setPvp(resultado.getString("pvp"));
                p.setCodigoCategoria(resultado.getString("codigo_categoria"));
                p.setNitProveedor(resultado.getString("nit_proveedor"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static boolean categoriaExiste(int codigoCategoria) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
        String SQL = "SELECT 1 FROM categorias WHERE codigo = ?";
        con = conexion.conectar();
        st = con.prepareStatement(SQL);
        st.setInt(1, codigoCategoria);
        rs = st.executeQuery();
        return rs.next(); // Si hay resultados, la categoría existe
    } catch (SQLException ex) {
        System.err.println("❌ Error SQL al verificar categoría: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión: " + ex.getMessage());
        }
    }
}

    
    
    
}
