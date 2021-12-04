package Controllers;

import Model.Constants;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class settingController {

    @FXML
    private JFXButton back;

    Parent root;
    Stage stage;
    Scene scene;


    @FXML
    void handleBack(ActionEvent event) throws IOException {
        if(event.getSource().equals(back)){

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.HOME));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }

    }

}
