package fxproject.src.src;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Signup {

    @FXML
    private TextField firstname;

    @FXML
    private TextField idno;

    @FXML
    private TextField ipaddress;

    @FXML
    private TextField lastname;

    @FXML
    private Button next;

    @FXML
    private Button submit;

    


    @FXML
     void submit(ActionEvent event) throws IOException,ClassNotFoundException,SQLException {
        Jdbc db= new Jdbc();
        Connection connectDb= db.getConnection();
        String query1= "select * from details";

        try{

            String query="insert into CONTACTS(FIRST_NAME,LAST_NAME,ID,IP_ADD)values (?, ?, ?,?)";
            PreparedStatement ps = connectDb.prepareStatement(query);
            ps.setString(1, firstname.getText());
            ps.setString(2, lastname.getText());
            ps.setString(3, idno.getText());
            ps.setString(4, ipaddress.getText());
            ps.execute();
            System.out.println("data inserted successfully\n");

            java.sql.Statement st1=connectDb.createStatement();
            ResultSet out1=st1.executeQuery(query1);

            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
           
    }

    

        
         




       

    

}
