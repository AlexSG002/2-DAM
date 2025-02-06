<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.PeliculaDao" %>
<%@ page import="dao.conexion" %>
<%@ page import="model.Pelicula" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FilmCloud</title>
        <link rel="stylesheet" href="styles.css">
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>
    <body>
        <div class="header">
            <h1>FILMCLOUD</h1>
            <img src="img/LogoFilmCloud.png" width="200" height="200">
        </div>
        <div class="container">
            <div class="main">
                <h2>VISUALIZAR PELÍCULAS</h2>

                <table class="movie-table">
                    <thead>
                        <tr>
                            <th>Nombre de la Película</th>
                            <th>Género</th>
                            <th>Estado de Visualización</th>
                            <th>Cine Asociado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Conectar a la base de datos y obtener la lista de películas
                            List<Pelicula> peliculas = null;
                            PeliculaDao peliculaDao = null;
                            try {
                                Connection connection = conexion.conectar(); // Obtener conexión
                                if (connection != null) {
                                    peliculaDao = new PeliculaDao(connection);
                                    peliculas = peliculaDao.obtenerPeliculas(); // Obtener películas
                                    connection.close();
                                } else {
                                    out.println("<tr><td colspan='4' style='color: red; text-align: center;'>❌ Error: No se pudo establecer la conexión con la base de datos.</td></tr>");
                                }
                            } catch (Exception e) {
                                out.println("<tr><td colspan='4' style='color: red; text-align: center;'>❌ Error al cargar las películas: " + e.getMessage() + "</td></tr>");
                            }

                            // Mostrar las películas en la tabla
                            if (peliculas != null && !peliculas.isEmpty()) {
                                for (Pelicula pelicula : peliculas) {
                                    String nombreCine = "Desconocido";
                                    try {
                                        Connection connection = conexion.conectar();
                                        if (connection != null) {
                                            PeliculaDao tempDao = new PeliculaDao(connection);
                                            nombreCine = tempDao.obtenerNombreCine(pelicula.getCineId()); // Obtener el nombre del cine
                                            connection.close();
                                        }
                                    } catch (Exception ex) {
                                        nombreCine = "Error al cargar";
                                    }
                        %>
                        <tr>
                            <td><%= pelicula.getNombre()%></td>
                            <td><%= pelicula.getGenero()%></td>
                            <td><%= pelicula.isVisto() ? "Visto" : "No visto"%></td>
                            <td><%= nombreCine%></td> <!-- Mostrando el nombre del cine en lugar del ID -->
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="4" style="text-align: center;">No hay películas disponibles</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <div class="action-buttons">
                    <button class="action-button" onclick="window.location.href = 'insertarCine.jsp'">INSERTAR CINE</button>
                    <button class="download-button" onclick="descargarInforme()">DESCARGAR INFORME</button>
                    <button class="action-button" onclick="window.location.href = 'insertarPelicula.jsp'">INSERTAR PELÍCULA</button>
                </div>
            </div>
        </div>

        <script>
            function descargarInforme() {
                window.location.href = 'GenerarInformeServlet';
            }
        </script>
    </body>
</html>