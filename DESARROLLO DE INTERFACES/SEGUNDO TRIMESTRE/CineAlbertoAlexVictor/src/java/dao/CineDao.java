/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berto
 */

public class CineDao {
    private Connection connection;

    public CineDao(Connection connection) {
        this.connection = connection;
    }

    public void agregarCine(Cine cine) throws SQLException {
        String sql = "INSERT INTO cines (nombre, direccion) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cine.getNombre());
            statement.setString(2, cine.getDireccion());
            statement.executeUpdate();
        }
    }

    public List<Cine> obtenerCines() throws SQLException {
        List<Cine> cines = new ArrayList<>();
        String sql = "SELECT * FROM cines";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                cines.add(new Cine(id, nombre, direccion));
            }
        }
        return cines;
    }
    
    public List<Cine> obtenerCinesDistintos() throws SQLException {
        List<Cine> cines = new ArrayList<>();
        String sql = "SELECT DISTINCT * FROM cines";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                cines.add(new Cine(id, nombre, direccion));
            }
        }
        return cines;
    }
}

