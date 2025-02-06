<%-- 
    Document   : insertarCine
    Created on : 30-ene-2025, 18:38:10
    Author     : berto
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insertar Cine</title>
        <link rel="stylesheet" href="styles.css">
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>
    <body>
        <div class="header">
            <h1>Insertar Cine</h1>
            <img src="img/LogoFilmCloud.png" width="200" height="200">
        </div>

        <%-- Mostrar mensaje si existe --%>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
        <p class="message"><%= mensaje%></p>
        <%
            }
        %>

        <div class="form-container">
            <form action="CineControl" method="post" class="styled-form">
                <label for="nombreCine">Nombre del Cine:</label>
                <input type="text" id="nombreCine" name="nombreCine" placeholder="Ingrese el nombre del cine" required>

                <label for="direccionCine">Dirección del Cine:</label>
                <input type="text" id="direccionCine" name="direccionCine" placeholder="Ingrese la dirección" required>

                <div class="form-buttons">
                    <button type="submit" class="submit-button">Guardar Cine</button>
                    <button type="button" class="back-button" onclick="window.location.href = 'index.jsp'">Volver</button>
                </div>
            </form>
        </div>
    </body>
</html>