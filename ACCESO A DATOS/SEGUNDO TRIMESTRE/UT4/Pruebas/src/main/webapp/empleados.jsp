<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Empleado" %>
<%
    ArrayList<Empleado> empleados = (ArrayList<Empleado>) request.getAttribute("empleados");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Empleados</title>
</head>
<body>
    <h3>Lista de empleados</h3>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Puesto</th>
        </tr>
        <% if (empleados != null && !empleados.isEmpty()) {
            for (Empleado emp : empleados) { %>
                <tr>
                    <td><%= emp.getId() %></td>
                    <td><%= emp.getNombre() %></td>
                    <td><%= emp.getPuesto() %></td>
                </tr>
        <% } } else { %>
            <tr><td colspan="3">No hay empleados en este departamento</td></tr>
        <% } %>
    </table>
    <br>
    <a href="/Pruebas/Controlador">Volver</a>
</body>
</html>
