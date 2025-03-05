<%@ page import="modelos.Reserva" %>
<%@ page import="dao.ReservaDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="modelos.Habitacion" %>
<%@ page import="dao.HabitacionDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ver Reservas</title>
</head>
<body>
    <h1>Ver Reservas de una Habitación</h1>

    <%-- Lógica para obtener las reservas de una habitación --%>
    <%
        String codHabitacionParam = request.getParameter("codHabitacion");
        List<Reserva> reservas = null;
        if (codHabitacionParam != null) {
            int codHabitacion = Integer.parseInt(codHabitacionParam);
            ReservaDAO reservaDAO = new ReservaDAO();
            try {
                reservas = reservaDAO.obtenerReservasPorHabitacion(codHabitacion);
            } catch (SQLException | ClassNotFoundException e) {
                out.println("<p>Error al obtener las reservas: " + e.getMessage() + "</p>");
            }
        }
    %>

    <form action="verReservas.jsp" method="get">
        <label for="codHabitacion">Selecciona una habitación:</label>
        <select name="codHabitacion" id="codHabitacion">
            <%
                HabitacionDAO habitacionDAO = new HabitacionDAO();
                List<Habitacion> habitaciones = null;
                try {
                    habitaciones = habitacionDAO.listarHabitaciones();
                } catch (SQLException | ClassNotFoundException e) {
                    out.println("<p>Error al listar las habitaciones: " + e.getMessage() + "</p>");
                }
                if (habitaciones != null) {
                    for (Habitacion habitacion : habitaciones) {
                        String selected = "";
                        if (codHabitacionParam != null && Integer.parseInt(codHabitacionParam) == habitacion.getCodHabitacion()) {
                            selected = "selected";
                        }
                        out.println("<option value='" + habitacion.getCodHabitacion() + "' " + selected + ">Habitación " + habitacion.getCodHabitacion() + "</option>");
                    }
                }
            %>
        </select>
        <button type="submit">Ver Reservas</button>
    </form>

    <% if (reservas != null && !reservas.isEmpty()) { %>
        <h2>Reservas de la Habitación</h2>
        <table border="1">
            <tr>
                <th>Código Reserva</th>
                <th>Código Huésped</th>
                <th>Clase</th>
                <th>Fecha Inicio</th>
                <th>Fecha Fin</th>
                <th>Número de Huéspedes</th>
                <th>PVP</th>
            </tr>
            <% for (Reserva reserva : reservas) { %>
            <tr>
                <td><%= reserva.getCodReserva() %></td>
                <td><%= reserva.getCodHuesped() %></td>
                <td><%= reserva.getClase() %></td>
                <td><%= reserva.getFechaComienzo() %></td>
                <td><%= reserva.getFechaFin() %></td>
                <td><%= reserva.getNumeroHuespedes() %></td>
                <td><%= reserva.getPvp() %></td>
            </tr>
            <% } %>
        </table>
    <% } else if (codHabitacionParam != null) { %>
        <p>No hay reservas para esta habitación.</p>
    <% } %>

    <a href="index.jsp">Inicio</a>
</body>
</html>