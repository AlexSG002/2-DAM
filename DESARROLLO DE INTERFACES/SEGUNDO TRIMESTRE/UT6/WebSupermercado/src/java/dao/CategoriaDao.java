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
    public static boolean registrar(Categoria cat) {
    if (categoriaExiste(cat.getCodigo())) {
        System.err.println("❌ Error: El código de categoría ya existe.");
        return false;
    }

    Connection con = null;
    PreparedStatement st = null;
    try {
        String SQL = "INSERT INTO categorias(codigo, nombre) VALUES(?, ?)";
        con = conexion.conectar();
        st = con.prepareStatement(SQL);
        st.setString(1, cat.getCodigo());
        st.setString(2, cat.getNombre());

        return st.executeUpdate() > 0;
    } catch (SQLException ex) {
        System.err.println("⚠️ Error SQL al insertar categoría: " + ex.getMessage());
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

    
    public static ArrayList<Categoria> listar() {
    ArrayList<Categoria> lista = new ArrayList<>(); // Inicialización correcta
    Connection con = null;
    PreparedStatement st = null;
    ResultSet resultado = null;
    try {
        String SQL = "SELECT * FROM categorias"; // Corrección del nombre de la tabla
        con = conexion.conectar();
        st = con.prepareStatement(SQL);
        resultado = st.executeQuery();

        while (resultado.next()) {
            Categoria cat = new Categoria();
            cat.setCodigo(resultado.getString("codigo"));
            cat.setNombre(resultado.getString("nombre"));
            lista.add(cat);
        }
    } catch (SQLException ex) {
        System.err.println("⚠️ Error SQL al listar categorías: " + ex.getMessage());
    } finally {
        try {
            if (resultado != null) resultado.close();
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión: " + ex.getMessage());
        }
    }
    return lista;
}
    
    public static boolean categoriaExiste(String codigo) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
        String SQL = "SELECT 1 FROM categorias WHERE codigo = ?";
        con = conexion.conectar();
        st = con.prepareStatement(SQL);
        st.setString(1, codigo);
        rs = st.executeQuery();
        return rs.next(); // Si hay resultados, la categoría ya existe
    } catch (SQLException ex) {
        System.err.println("⚠️ Error al verificar categoría: " + ex.getMessage());
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

    public static String getCategoria(String cod){
        Connection con = null;
        PreparedStatement st = null;
        try {
            String SQL = "SELECT * FROM categorias where codigo = ?;";
            con = conexion.conectar();
            if(con==null){
                return null;
            }
            st = con.prepareStatement(SQL);
            st.setString(1, cod);
            ResultSet resultado = st.executeQuery();
            if(resultado.next()){
                return resultado.getString("nombre");
            }
            return "--";
        } catch (SQLException ex) {
            return "--";
        }
    }
    
    public static boolean actualizar(Categoria cat) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        String SQL = "UPDATE categorias SET nombre = ? WHERE codigo = ?";
        con = conexion.conectar();
        if (con == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos (actualizar categoría).");
            return false;
        }
        st = con.prepareStatement(SQL);
        st.setString(1, cat.getNombre());
        st.setString(2, cat.getCodigo());
        int filas = st.executeUpdate();
        if (filas > 0) {
            System.out.println("✅ Categoría actualizada correctamente.");
            return true;
        } else {
            System.err.println("⚠️ No se actualizó la categoría.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("❌ Error SQL al actualizar categoría: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión en actualizar categoría: " + ex.getMessage());
        }
    }
}

public static boolean eliminar(String codigo) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        String SQL = "DELETE FROM categorias WHERE codigo = ?";
        con = conexion.conectar();
        if (con == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos (eliminar categoría).");
            return false;
        }
        st = con.prepareStatement(SQL);
        st.setString(1, codigo);
        int filas = st.executeUpdate();
        if (filas > 0) {
            System.out.println("✅ Categoría eliminada correctamente.");
            return true;
        } else {
            System.err.println("⚠️ No se eliminó la categoría.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("❌ Error SQL al eliminar categoría: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error cerrando conexión en eliminar categoría: " + ex.getMessage());
        }
    }
}

public static boolean tieneProductos(String codigoCategoria) {
    boolean tieneProductos = false;
    String sql = "SELECT COUNT(*) FROM productos WHERE codigo_categoria = ?";
    
    try (Connection conn = conexion.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
         ps.setString(1, codigoCategoria);
         try (ResultSet rs = ps.executeQuery()) {
             if (rs.next()) {
                 int count = rs.getInt(1);
                 tieneProductos = (count > 0);
             }
         }
    } catch (SQLException ex) {
         System.out.println("Error en tieneProductos: " + ex.getMessage());
    }
    
    return tieneProductos;
}


    
}
