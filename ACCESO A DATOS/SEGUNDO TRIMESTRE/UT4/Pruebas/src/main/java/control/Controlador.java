package control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import modelo.Departamento;
import modelo.Empleado;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("empleados".equals(accion)) {
            int deptNo = Integer.parseInt(request.getParameter("dept_no"));

            // Lista de empleados de prueba
            ArrayList<Empleado> empleados = new ArrayList<>();
            empleados.add(new Empleado(1, "Juan Pérez", "Analista", 1));
            empleados.add(new Empleado(2, "Ana Gómez", "Gerente", 2));
            empleados.add(new Empleado(3, "Carlos Ruiz", "Programador", 1));
            empleados.add(new Empleado(4, "María López", "Diseñadora", 3));

            // Filtrar empleados por el departamento seleccionado
            ArrayList<Empleado> empleadosDepto = new ArrayList<>();
            for (Empleado e : empleados) {
                if (e.getDeptNo() == deptNo) {
                    empleadosDepto.add(e);
                }
            }

            request.setAttribute("empleados", empleadosDepto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("empleados.jsp");
            dispatcher.forward(request, response);
        } else {
            // Lógica de departamentos
            ArrayList<Departamento> departamentos = new ArrayList<>();
            departamentos.add(new Departamento(1, "Ventas"));
            departamentos.add(new Departamento(2, "Marketing"));
            departamentos.add(new Departamento(3, "IT"));

            request.setAttribute("departamentos", departamentos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("departamentos.jsp");
            dispatcher.forward(request, response);
        }
    }
}
