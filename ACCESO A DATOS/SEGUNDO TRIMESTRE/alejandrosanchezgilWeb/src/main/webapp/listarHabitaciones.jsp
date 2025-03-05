<%@ page import="modelos.Habitacion" %>
<%@ page import="dao.HabitacionDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listar Habitaciones</title>
</head>
<body>
    <h1>Listado de Habitaciones</h1>

    <%-- L�gica para borrar una habitaci�n --%>
    <% 
        String codHabitacionParam = request.getParameter("codHabitacion");
        if (codHabitacionParam != null) {
            int codHabitacion = Integer.parseInt(codHabitacionParam);
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            try {
                boolean borrado = habitacionDAO.borrarHabitacion(codHabitacion);
                if (borrado) {
                    out.println("<p>Habitaci�n borrada correctamente.</p>");
                } else {
                    out.println("<p>No se pudo borrar la habitaci�n.</p>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                if (e instanceof SQLException && e.getMessage().contains("ORA-02292")) {
                    out.println("<p>No se ha podido borrar la habitaci�n ya que existen reservas asociadas.</p>");
                } else {
                    out.println("<p>Error al borrar la habitaci�n: " + e.getMessage() + "</p>");
                }
            }

        }
    %>

    <%-- L�gica para listar las habitaciones --%>
    <%
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<Habitacion> habitaciones = null;
        try {
            habitaciones = habitacionDAO.listarHabitaciones();
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<p>Error al listar las habitaciones: " + e.getMessage() + "</p>");
        }
    %>

    <table border="1">
        <tr>
            <th>C�digo</th>
            <th>Nombre</th>
            <th>Hotel</th>
            <th>Tipo</th>
            <th>Acciones</th>
        </tr>
        <% if (habitaciones != null) { %>
            <% for (Habitacion habitacion : habitaciones) { %>
            <tr>
                <td><%= habitacion.getCodHabitacion() %></td>
                <td><%= habitacion.getNombreHabitacion() %></td>
                <td><%= habitacion.getCodHotel() %></td>
                <td><%= habitacion.getTipo() %></td>
                <td>
                    <a href="listarHabitaciones.jsp?codHabitacion=<%= habitacion.getCodHabitacion() %>">Borrar</a>
                </td>
            </tr>
            <% } %>
        <% } %>
    </table>
    <a href="index.jsp">Inicio</a>
</body>
</html>