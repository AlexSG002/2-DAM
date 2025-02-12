/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriaDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;

/**
 *
 * @author Tarde
 */
public class CategoriaControl extends HttpServlet {

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
            out.println("<title>Servlet CategoriaControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaControl at " + request.getContextPath() + "</h1>");
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
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String accion = request.getParameter("accion").toLowerCase();

        Categoria c = new Categoria();
        c.setCodigo(codigo);
        c.setNombre(nombre);

        if (accion.equals("registrar")) {
            if (CategoriaDao.registrar(c)) {
                request.setAttribute("mensaje", "✅ Categoría registrada correctamente.");
            } else {
                request.setAttribute("mensaje", "❌ Error al registrar la categoría. Puede que el código ya exista.");
            }
        } else if (accion.equals("actualizar")) {
            if (CategoriaDao.actualizar(c)) {
                request.setAttribute("mensaje", "✅ Categoría actualizada correctamente.");
            } else {
                request.setAttribute("mensaje", "❌ Error al actualizar la categoría.");
            }
        } else if (accion.equals("eliminar")) {
            if (CategoriaDao.eliminar(codigo)) {
                request.setAttribute("mensaje", "✅ Categoría eliminada correctamente.");
            } else {
                if (CategoriaDao.tieneProductos(codigo)) {
                    request.setAttribute("mensaje", "❌ No se ha podido borrar la categoría porque hay productos asociados.");
                } else {
                    request.setAttribute("mensaje", "❌ Error al eliminar la categoría.");
                }
            }
        }

        request.getRequestDispatcher("registroCategoria.jsp").forward(request, response);
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
