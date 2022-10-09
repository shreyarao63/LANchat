package fxproject.src.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class DBUtils {

 public static void changeScene(ActionEvent event,String fxmlFile , String title)
    {
        Parent root =null;
       
        try{
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));   
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Stage stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();

    }

 public static void Sign_up(ActionEvent event, String fname, String lname, int id_no, String ip_add)
 {
    Connection connection=null;
    PreparedStatement psInsert=null;
    
    try{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_PROJECT","root","1234");
        psInsert = connection.prepareStatement("insert into CONTACTS(FIRST_NAME,LAST_NAME,ID,IP_ADD)values (?, ?, ?,?)");
        psInsert.setString(1, fname);
        psInsert.setString(2, lname);
        psInsert.setInt(3, id_no);
        psInsert.setString(4, ip_add);
        psInsert.executeUpdate();

        changeScene(event, "connectto", "connect_to");
        } 
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    finally{
        if(psInsert!=null)
        {
            try{
                psInsert.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null)
        {
            try{
               connection.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
 }

 public static String conn_to(ActionEvent event, String Fname, int id)
 {
    Connection connection = null;
    PreparedStatement psSelect = null;
    PreparedStatement psCheckUserExists = null;
    ResultSet resultset= null;
    
  try
 {
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_PROJECT","root@localhost","1234");
    psSelect= connection.prepareStatement("SELECT IP_ADD  FROM contacts WHERE (FIRST_NAME=? , ID=?)");
    psSelect.setString(1,Fname);
    psSelect.setInt(2,id);
    resultset = psSelect.executeQuery();
    String retrieveIP= null;
  if(!resultset.isBeforeFirst())
  {
    //existence check
     System.out.println("User not found in your contacts\n");
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setContentText("put valid user");
     alert.show();
     return null;
     
}
  else{
    //obtaining ip address
    while(resultset.next())
    {
         retrieveIP = resultset.getString("IP_ADD"); 
    }
    return retrieveIP;
}
}

 catch (SQLException e)
{
    System.out.println("ERROR: failed to connect!");
    System.out.println("ERROR: " + e.getMessage());
    e.printStackTrace();
    return null;
}
finally{
 if(resultset!=null)
 {
     try{
         resultset.close();
     }
     catch(SQLException e){
         e.printStackTrace();
     }
 }
 if(psCheckUserExists!=null)
 {
     try{
         psCheckUserExists.close();
     }
     catch(SQLException e){
         e.printStackTrace();
     }
 }
 if(psSelect!=null)
 {
     try{
         psSelect.close();
     }
     catch(SQLException e){
         e.printStackTrace();
     }
 }
 if(connection!=null)
 {
     try{
        connection.close();
     }
     catch(SQLException e){
         e.printStackTrace();
     }
 }
}
}    
    }
    

