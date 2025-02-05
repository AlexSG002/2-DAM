package dep_dao2_prueba;

import empledep.DAOFactory;
import empledep.Departamento;
import empledep.DepartamentoDAO;
import empledep.Empleado;
import empledep.EmpleadoDAO;

import java.util.Scanner;

public class DEP_DAO2_PRUEBA {

    public static void main(String[] args) {
//        System.out.println("------------------------------");
//        System.out.println("PRUEBA MYSQL");
//
//        pruebamysql();
//        System.out.println("------------------------------");
//        System.out.println("PRUEBA NEODATIS");
//
//        pruebaneodatis();
        
        System.out.println("------------------------------");
        System.out.println("PRUEBA ORACLE");
        
        //pruebaOracle();
        
        pruebaOracleEmpleados();
    }



//    public static void pruebaneodatis() {
//        DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.NEODATIS);
//        DepartamentoDAO depDAO = bd.getDepartamentoDAO();
//
//        //crear departamento
//        Departamento dep = new Departamento(17, "NÓMINAS", "SEVILLA");
//        depDAO.InsertarDep(dep);
//
//        Scanner sc = new Scanner(System.in);
//        int entero = 1;
//      //Visualizar departamentos leidos por teclado
//        while (entero > 0) {
//            System.out.println("Teclea Departamento a visualizar (0 sale): ");
//            entero = sc.nextInt();
//            dep = depDAO.ConsultarDep(entero);
//            System.out.printf("Dep: %d, Nombre: %s, Loc: %s %n", dep.getDeptno(),
//                    dep.getDnombre(), dep.getLoc());
//        }
//    }
//
//    public static void pruebamysql() {
//        DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
//        DepartamentoDAO depDAO = bd.getDepartamentoDAO();
//
//        //crear departamento
//        Departamento dep = new Departamento(17, "NÓMINAS", "SEVILLA");
//        depDAO.InsertarDep(dep);
//
//        Scanner sc = new Scanner(System.in);
//        int entero = 1;
//        //Visualizar departamentos leidos por teclado
//        while (entero > 0) {
//            System.out.println("Teclea Departamento a visualizar (0 sale): ");
//            entero = sc.nextInt();
//            dep = depDAO.ConsultarDep(entero);
//            System.out.printf("Dep: %d, Nombre: %s, Loc: %s %n", dep.getDeptno(),
//                    dep.getDnombre(), dep.getLoc());
//        }
//    }
    
    public static void pruebaOracle() {
    	DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
    	DepartamentoDAO depDAO = bd.getDepartamentoDAO();
    	
    	Departamento dep = new Departamento(17, "NÓMINAS", "SEVILLA");
    	depDAO.InsertarDep(dep);
    	
    	Scanner sc = new Scanner(System.in);
    	int entero = 1;
    	while (entero > 0) {
    		System.out.println("Teclea Departamento a visualizar (0 sale): ");
    		entero = sc.nextInt();
    		dep = depDAO.ConsultarDep(entero);
    		System.out.printf("Dep: %d, Nombre: %S, Loc: %s %n", dep.getDeptno(), dep.getDnombre(), dep.getLoc());
    	}
    }
    
    private static void pruebaOracleEmpleados() {
		DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
		EmpleadoDAO empDAO = bd.getEmpleadoDAO();
		
		Empleado emp = new Empleado(1, "SALVATELLA", "OBRERO", 7902, null, 1000.0f, 200.0f, 20);
		empDAO.InsertarEmp(emp);
		
		Scanner sc = new Scanner(System.in);
		int entero = 1;
		while (entero > 0) {
    		System.out.println("Teclea Empleado a visualizar (0 sale): ");
    		entero = sc.nextInt();
    		emp = empDAO.ConsultarEmp(entero);
    		System.out.printf("Empleado: %d, Apellido: %S, Oficio: %s, Director: %s, Fecha_alta: %s, Salario: %s, Comisión: %s, Departamento: %s %n", emp.getEmpno(), emp.getApe(), emp.getDir(), emp.getOficio(), emp.getFecha(), emp.getSalario(), emp.getComision(), emp.getDepno());
    	}
		
	}

}