package dao;

import modelos.Reserva;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public List<Reserva> obtenerReservasPorHabitacion(int codHabitacion) throws SQLException, ClassNotFoundException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE codhabitacion = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codHabitacion);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setCodReserva(rs.getInt("codreserva"));
                    reserva.setCodHuesped(rs.getInt("codhuesped"));
                    reserva.setCodHabitacion(rs.getInt("codhabitacion"));
                    reserva.setClase(rs.getString("clase"));
                    reserva.setFechaComienzo(rs.getDate("fecha_comienzo"));
                    reserva.setFechaFin(rs.getDate("fecha_fin"));
                    reserva.setNumeroHuespedes(rs.getInt("numero_huespedes"));
                    reserva.setPvp(rs.getDouble("pvp"));
                    reservas.add(reserva);
                }
            }
        }
        return reservas;
    }
}