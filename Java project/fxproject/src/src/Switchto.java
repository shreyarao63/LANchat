package fxproject.src.src;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Switchto{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSignup(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToConnectto(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("connectto.fxml"));
        stage=(Stage)(((Node) event.getSource()).getScene().getWindow());
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
