/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import dao.CineDao;
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
import model.Cine;

/**
 *
 * @author berto
 */
public class CineControl extends HttpServlet {

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
            out.println("<title>Servlet CineControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CineControl at " + request.getContextPath() + "</h1>");
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
        String nombreCine = request.getParameter("nombreCine");
        String direccionCine = request.getParameter("direccionCine");

        // Validar que los campos no estén vacíos
        if (nombreCine == null || nombreCine.trim().isEmpty()
                || direccionCine == null || direccionCine.trim().isEmpty()) {
            request.setAttribute("mensaje", "❌ Error: Todos los campos son obligatorios.");
            request.getRequestDispatcher("insertarCine.jsp").forward(request, response);
            return;
        }

        Connection connection = null;
        try {
            // Conectar a la base de datos
            connection = conexion.conectar();
            if (connection == null) {
                request.setAttribute("mensaje", "❌ Error de conexión a la base de datos.");
                request.getRequestDispatcher("insertarCine.jsp").forward(request, response);
                return;
            }

            // Verificar si el cine ya existe
            String sql = "SELECT COUNT(*) FROM cines WHERE nombre = ? AND direccion = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombreCine);
                statement.setString(2, direccionCine);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        request.setAttribute("mensaje", "❌ Este cine ya existe.");
                        request.getRequestDispatcher("insertarCine.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Insertar el cine en la base de datos
            CineDao cineDao = new CineDao(connection);
            Cine nuevoCine = new Cine(nombreCine, direccionCine);
            cineDao.agregarCine(nuevoCine);

            // Redirigir con mensaje de éxito
            request.setAttribute("mensaje", "✅ Cine agregado exitosamente.");
            request.getRequestDispatcher("insertarCine.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("mensaje", "❌ Error al guardar el cine: " + e.getMessage());
            request.getRequestDispatcher("insertarCine.jsp").forward(request, response);
        } finally {
            // Asegurarse de cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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
