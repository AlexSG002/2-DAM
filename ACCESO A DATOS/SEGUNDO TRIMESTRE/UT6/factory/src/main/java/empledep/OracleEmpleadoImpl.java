package empledep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleEmpleadoImpl implements EmpleadoDAO {

	Connection conexion;

    public OracleEmpleadoImpl() {
        conexion = OracleDAOFactory.crearConexion();
    }
	
	@Override
	public boolean InsertarEmp(Empleado emp) {
        if (existeEmpleado(emp.getEmpno())) {
            System.out.println("Error: El empleado con número " + emp.getEmpno() + " ya existe.");
            return false;
        }
        if (!existeDepartamento(emp.getDepno())) {
            System.out.println("Error: El departamento " + emp.getDepno() + " no existe.");
            return false;
        }
        if (emp.getDir() != 0 && !existeEmpleado(emp.getDir())) {
            System.out.println("Error: El director " + emp.getDir() + " no existe.");
            return false;
        }
		boolean valor = false;
        String sql = "INSERT INTO empleados VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, emp.getEmpno());
            sentencia.setString(2, emp.getApe());
            sentencia.setString(3, emp.getOficio());
            sentencia.setInt(4, emp.getDir());
            sentencia.setDate(5, emp.getFecha());
            sentencia.setFloat(6, emp.getSalario());
            sentencia.setFloat(7, emp.getComision());
            sentencia.setInt(8, emp.getDepno());
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                valor = true;
                System.out.printf("Empleado %d insertado%n",
                                 emp.getEmpno());
            }
            sentencia.close();

        } catch (SQLException e) { MensajeExcepcion(e); }

        return valor;
	}

	private boolean tieneEmpleadosACargo(int empno) {
        String sql = "SELECT COUNT(*) FROM empleados WHERE dir = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, empno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt(1);
                rs.close();
                return cantidad > 0;
            }
            rs.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return false;
    }
	
	private boolean existeDepartamento(int deptno) {
        String sql = "SELECT COUNT(*) FROM departamentos WHERE dept_no = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, deptno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt(1);
                rs.close();
                return cantidad > 0;
            }
            rs.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return false;
    }
	
	private boolean existeEmpleado(int empno) {
        String sql = "SELECT COUNT(*) FROM empleados WHERE emp_no = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, empno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt(1);
                rs.close();
                return cantidad > 0;
            }
            rs.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return false;
    }
	
	@Override
	public boolean EliminarEmp(int empno) {
        if (tieneEmpleadosACargo(empno)) {
            System.out.println("Error: No se puede eliminar al empleado " + empno + " porque tiene empleados a cargo.");
            return false;
        }
        if (!existeEmpleado(empno)) {
            System.out.println("Error: El empleado " + empno + " no existe.");
            return false;
        }
		boolean valor = false;
        String sql = "DELETE FROM empleados WHERE emp_no = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empno);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
              valor = true;
              System.out.printf("Empleado %d eliminado%n",empno);
            }
            sentencia.close();
        } catch (SQLException e) { MensajeExcepcion(e); }

        return valor;
	}

	@Override
	public boolean ModificarEmp(int empno, Empleado emp) {
		if (!existeDepartamento(emp.getDepno())) {
            System.out.println("Error: El departamento " + emp.getDepno() + " no existe.");
            return false;
        }
        if (emp.getDir() != 0 && !existeEmpleado(emp.getDir())) {
            System.out.println("Error: El director " + emp.getDir() + " no existe.");
            return false;
        }
        if (!existeEmpleado(empno)) {
            System.out.println("Error: El empleado " + empno + " no existe y no se puede modificar.");
            return false;
        }
		
		boolean valor = false;
        String sql = "UPDATE empleados SET apellidos= ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, comision = ?, dept_no = ? WHERE emp_no = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(8, empno);
            sentencia.setString(1, emp.getApe());
            sentencia.setString(2, emp.getOficio());
            sentencia.setInt(3, emp.getDir());
            sentencia.setDate(4, emp.getFecha());
            sentencia.setFloat(5, emp.getSalario());
            sentencia.setFloat(6, emp.getComision());
            sentencia.setInt(7, emp.getDepno());
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                valor = true;
                System.out.printf("Departamento %d modificado%n", empno);
            }
            sentencia.close();
        } catch (SQLException e) { MensajeExcepcion(e); }

        return valor;
	}

	@Override
	public Empleado ConsultarEmp(int empno) {
		String sql = "SELECT emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no FROM empleados WHERE emp_no = ?";
        PreparedStatement sentencia;
        Empleado emp = new Empleado();        
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empno);
            ResultSet rs = sentencia.executeQuery();          
            if (rs.next()) {
                emp.setEmpno(rs.getInt("emp_no"));
                emp.setApe(rs.getString("apellido"));
                emp.setOficio(rs.getString("oficio"));
                emp.setDir(rs.getInt("dir"));
                emp.setSalario(rs.getFloat("salario"));
                emp.setComision(rs.getFloat("comision"));
                emp.setDepno(rs.getInt("dept_no"));
            }
            else
                System.out.printf("Empleado: %d No existe%n", 
                                       empno);
            
            rs.close();// liberar recursos
            sentencia.close();
         
        } catch (SQLException e) { MensajeExcepcion(e); }
        
        return emp;
	
	}
	
	private void MensajeExcepcion(SQLException e) {
	       System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
	       System.out.printf("Mensaje   : %s %n", e.getMessage());
	       System.out.printf("SQL estado: %s %n", e.getSQLState());
	       System.out.printf("Cód error : %s %n", e.getErrorCode());
	    }


}
