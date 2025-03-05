package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    static String URLDB = "jdbc:oracle:thin:@localhost:1521:XE";
    static String USUARIO = "HOTELES";
    static String CLAVE = "HOTELES";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URLDB, USUARIO, CLAVE);
    }
}