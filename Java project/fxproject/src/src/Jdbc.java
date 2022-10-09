package fxproject.src.src;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {
    public Connection databaselink;
     public Connection getConnection() {
        String dbname="JAVA_PROJECT";
        String dbuser="root";
        String dbPass="1234";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_PROJECT", "root", "1234");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return databaselink;
     }
    
}
