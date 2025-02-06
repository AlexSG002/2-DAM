<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.CineDao" %>
<%@ page import="dao.PeliculaDao" %>
<%@ page import="dao.conexion" %>
<%@ page import="model.Cine" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insertar Película</title>
        <link rel="stylesheet" href="styles.css">
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>
    <body>
        <div class="header">
            <h1>Insertar Película</h1>
            <img src="img/LogoFilmCloud.png" width="200" height="200">
        </div>

        <%
            // Mostrar mensaje si existe
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
        <p class="message"><%= mensaje%></p>
        <%
            }
        %>

        <%
            // Conexión a la base de datos y obtención de cines y géneros únicos
            List<Cine> cines = null;
            List<String> generos = null;
            try {
                Connection connection = conexion.conectar(); // Usar la conexión de la clase conexion
                if (connection != null) {
                    CineDao cineDao = new CineDao(connection);
                    cines = cineDao.obtenerCinesDistintos(); // Obtener la lista de cines

                    PeliculaDao peliculaDao = new PeliculaDao(connection);
                    generos = peliculaDao.obtenerGenerosUnicos(); // Obtener géneros únicos

                    connection.close(); // Cerrar la conexión
                } else {
                    out.println("<p style='color: red;'>❌ Error: No se pudo establecer la conexión con la base de datos.</p>");
                }
            } catch (Exception e) {
                out.println("<p style='color: red;'>❌ Error al cargar los datos: " + e.getMessage() + "</p>");
            }
        %>

        <div class="form-container">
            <form action="PeliculaControl" method="post" class="styled-form">
                <label for="nombrePelicula">Nombre de la Película:</label>
                <input type="text" id="nombrePelicula" name="nombrePelicula" placeholder="Ingrese el nombre de la película" required>

                <label for="genero">Género:</label>
                <select id="genero" name="genero" required>
                    <option value="" disabled selected>Seleccione un género</option>
                    <%
                        if (generos != null && !generos.isEmpty()) {
                            for (String genero : generos) {
                    %>
                    <option value="<%= genero%>"><%= genero%></option>
                    <%
                        }
                    } else {
                    %>
                    <option value="No hay géneros disponibles">No hay géneros disponibles</option>
                    <% } %>
                </select>

                <label for="estado">¿Ya has visto esta película?</label>
                <select id="estado" name="estado" required>
                    <option value="" disabled selected>Seleccione una opción</option>
                    <option value="Sí">Sí</option>
                    <option value="No">No</option>
                </select>

                <label for="cineAsociado">Cine Asociado:</label>
                <input list="cines" id="cineAsociado" name="cineAsociado" placeholder="Seleccione o escriba un cine" required>
                <datalist id="cines">
                    <%
                        if (cines != null && !cines.isEmpty()) {
                            for (Cine cine : cines) {
                    %>
                    <option value="<%= cine.getNombre()%>">
                        <%
                            }
                        } else {
                        %>
                    <option value="No hay cines disponibles">
                        <% }%>
                </datalist>

                <div class="form-buttons">
                    <button type="submit" class="submit-button">Guardar Película</button>
                    <button type="button" class="back-button" onclick="window.location.href = 'index.jsp'">Volver</button>
                </div>
            </form>
        </div>
    </body>
</html>