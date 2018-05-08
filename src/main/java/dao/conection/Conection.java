package dao.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    private Connection conn = null;
    private static Conection conection;

    private Conection() throws SQLException, ClassNotFoundException{
        this.createConexion();
    }

    public static Conection getInstance() throws SQLException, ClassNotFoundException{
        if(conection ==null){
            conection = new Conection();
        }
        return conection;
    }

    private void createConexion()throws SQLException, ClassNotFoundException{
        String urlDatabase =  "jdbc:postgresql://localhost:5432/examenER";
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(urlDatabase,  "postgres", "123456");
    }

    public Connection getConn() {
        return conn;
    }
}
