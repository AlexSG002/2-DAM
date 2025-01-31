/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProveedorDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Proveedor;

/**
 *
 * @author Tarde
 */
public class ProveedorControl extends HttpServlet {

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
            out.println("<title>Servlet ProveedorControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProveedorControl at " + request.getContextPath() + "</h1>");
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
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String tel = request.getParameter("tlf");
        String dir = request.getParameter("direccion");
        String email = request.getParameter("correo_electronico");

        // Verificar que el NIT y el teléfono sean numéricos antes de continuar
        if (!tel.matches("\\d+")) {
            request.setAttribute("mensaje", "❌ Error: El teléfono debe ser numérico.");
            request.getRequestDispatcher("registroProveedor.jsp").forward(request, response);
            return;
        }

        Proveedor p = new Proveedor();
        p.setNit(nit);
        p.setNombre(nombre);
        p.setTlf(tel);
        p.setDireccion(dir);
        p.setCorreoElectronico(email);

        if (ProveedorDao.registrar(p)) {
            request.setAttribute("mensaje", "✅ Proveedor registrado correctamente.");
        } else {
            request.setAttribute("mensaje", "❌ Error al registrar el proveedor. Puede que el NIT ya exista.");
        }

        request.getRequestDispatcher("registroProveedor.jsp").forward(request, response);
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
