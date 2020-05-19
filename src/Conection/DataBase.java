
package Conection;

import java.sql.*;

public class DataBase {
    
    private static final String URL = "jdbc:mysql://dsql10.freemysqlhosting.net:3306/sql10341885";
    private static final String USER = "sql10341885";
    private static final String PASS = "6RucvaXJCI";
    
    static Connection conect = null;
    public static Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect =(Connection) DriverManager.getConnection(URL, USER, PASS);
            System.out.println("SI");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conect; 
    }
}
