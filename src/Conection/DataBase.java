
package Conection;

import Processing.DuchaInfo;
import java.sql.*;

public class DataBase {
    
    private static final String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10341885";
    private static final String USER = "sql10341885";
    private static final String PASS = "6RucvaXJCI";
    
    static Connection conect = null;
    public static Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return conect; 
    }
    
    public static boolean Exist(String fecha){
        try {
            PreparedStatement statement = conect.prepareStatement("SELECT * FROM Datos_ducha WHERE Fecha='"+fecha+"'");
            ResultSet res = statement.executeQuery();
            return !res.next();
        } catch (SQLException ex) {
            System.out.println("Error, no se pudo verificar toda la informacion");
        }
        return true;
    }
    
    public static void Insert(DuchaInfo ducha){
        try {
            PreparedStatement statement = conect.prepareStatement(
            "INSERT INTO Datos_ducha(Fecha, Gasto, Tiempo, Costo) VALUES ('"+ducha.getFecha()+
                    "',"+ducha.getGasto()+","+ducha.getTiempo()+","+ducha.getCosto()+")");
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error en insert"+e);
        }
    }
}
