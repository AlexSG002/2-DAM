<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Departamento" %>
<%
    ArrayList<Departamento> lista = (ArrayList<Departamento>) request.getAttribute("departamentos");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Seleccionar Departamento</title>
</head>
<body>
    <h3>Empleados por departamento</h3>
    <form action="Controlador" method="get">
        <label for="dept_no">Selecciona un departamento:</label>
        <select name="dept_no">
            <% if (lista != null) {
                for (Departamento dep : lista) { %>
                    <option value="<%= dep.getDeptno() %>">
                        <%= dep.getDeptno() %> - <%= dep.getDnombre() %>
                    </option>
            <% } } %>
        </select>
        <br><br>
        <input type="hidden" name="accion" value="empleados" />
        <input type="submit" value="Ver empleados" />
    </form>
</body>
</html>
