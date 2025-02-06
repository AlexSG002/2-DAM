/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import dao.PeliculaDao;
import dao.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pelicula;

/**
 *
 * @author berto
 */
public class PeliculaControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PeliculaControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PeliculaControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // Obtener los datos del formulario
        String nombrePelicula = request.getParameter("nombrePelicula");
        String genero = request.getParameter("genero");
        String estado = request.getParameter("estado");
        String cineAsociado = request.getParameter("cineAsociado");

        // Validar que los campos no estén vacíos
        if (nombrePelicula == null || nombrePelicula.trim().isEmpty() ||
            genero == null || genero.trim().isEmpty() ||
            estado == null || estado.trim().isEmpty() ||
            cineAsociado == null || cineAsociado.trim().isEmpty()) {
            request.setAttribute("mensaje", "❌ Error: Todos los campos son obligatorios.");
            request.getRequestDispatcher("insertarPelicula.jsp").forward(request, response);
            return;
        }

        Connection connection = null;
        int cineId = -1;  // Variable para almacenar el cine_id

        try {
            // Conectar a la base de datos
            connection = conexion.conectar();
            if (connection == null) {
                request.setAttribute("mensaje", "❌ Error de conexión a la base de datos.");
                request.getRequestDispatcher("insertarPelicula.jsp").forward(request, response);
                return;
            }

            // Verificar si el cine existe y obtener su id
            String sqlCine = "SELECT id FROM cines WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlCine)) {
                statement.setString(1, cineAsociado.trim());  // Nombre del cine

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    cineId = resultSet.getInt("id");
                } else {
                    request.setAttribute("mensaje", "❌ Error: El cine seleccionado no existe.");
                    request.getRequestDispatcher("insertarPelicula.jsp").forward(request, response);
                    return;
                }
            }

            // Crear un objeto Pelicula y guardarlo
            PeliculaDao peliculaDao = new PeliculaDao(connection);
            Pelicula nuevaPelicula = new Pelicula(0, nombrePelicula, genero, "Sí".equals(estado), cineId);
            peliculaDao.agregarPelicula(nuevaPelicula);

            // Redirigir con mensaje de éxito
            request.setAttribute("mensaje", "✅ Película agregada exitosamente.");
            request.getRequestDispatcher("insertarPelicula.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("mensaje", "❌ Error al guardar la película: " + e.getMessage());
            request.getRequestDispatcher("insertarPelicula.jsp").forward(request, response);
        } finally {
            // Asegurarse de cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
