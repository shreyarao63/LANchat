package fxproject.src.src;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Connect_controller implements Initializable
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btct;

    @FXML
    private TextField fnct;

    @FXML
    private TextField idnoct;

    public void initialize(URL location, ResourceBundle resources)
    {
        btct.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                DBUtils.conn_to(event, fnct.getText(),Integer.parseInt(idnoct.getText()));//figure outs
                
            }
        });
    }

    
}
