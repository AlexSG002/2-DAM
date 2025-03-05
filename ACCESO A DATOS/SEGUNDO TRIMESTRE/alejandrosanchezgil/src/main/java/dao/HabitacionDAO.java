package dao;

import modelos.Habitacion;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    public List<Habitacion> listarHabitaciones() throws SQLException, ClassNotFoundException {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitacion";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setCodHabitacion(rs.getInt("codhabitacion"));
                habitacion.setCodHotel(rs.getInt("codhotel"));
                habitacion.setNombreHabitacion(rs.getString("nombrehabitacion"));
                habitacion.setTipo(rs.getString("tipo"));
                habitaciones.add(habitacion);
            }
        }
        return habitaciones;
    }

    public boolean borrarHabitacion(int codHabitacion) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM habitacion WHERE codhabitacion = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codHabitacion);
            return stmt.executeUpdate() > 0;
        }
    }
}
