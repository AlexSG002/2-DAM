/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductoDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;

/**
 *
 * @author Tarde
 */
public class ProductoControl extends HttpServlet {

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
            out.println("<title>Servlet ProductoControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoControl at " + request.getContextPath() + "</h1>");
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
        String cod = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String stock = request.getParameter("stock");
        String pvp = request.getParameter("pvp");
        String codCat = request.getParameter("codigo_categoria");
        String nitProv = request.getParameter("nit_proveedor");
        System.out.println("📌 Enviando producto: " + cod + " | " + nombre + " | " + descripcion + " | " + stock + " | " + pvp + " | " + codCat + " | " + nitProv);
        if (!ProductoDao.categoriaExiste(Integer.parseInt(codCat))) {
            request.setAttribute("mensaje", "Error: La categoría especificada no existe.");
            request.getRequestDispatcher("registroProducto.jsp").forward(request, response);
            return;
        }

        try {
            int stockNum = Integer.parseInt(stock);
            double pvpNum = Double.parseDouble(pvp);

            Producto p = new Producto();
            p.setCodigo(cod);
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setStock(String.valueOf(stockNum));
            p.setPvp(String.valueOf(pvpNum));
            p.setCodigoCategoria(codCat);
            p.setNitProveedor(nitProv);

            if (ProductoDao.registrar(p)) {
                request.setAttribute("mensaje", "✅ El producto fue registrado");
            } else {
                request.setAttribute("mensaje", "❌ El producto NO fue registrado");
            }
        } catch (NumberFormatException e) {
            System.err.println("❌ Error: Stock o PVP no son valores numéricos válidos.");
            request.setAttribute("mensaje", "Error: Stock y PVP deben ser números.");
        }
        request.getRequestDispatcher("registroProducto.jsp").forward(request, response);

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
