package Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {


    @FXML
    private JFXButton setting;

    @FXML
    private JFXButton send;

    @FXML
    private JFXButton recieve;

    @FXML
    private JFXTextArea message;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton clear;

    @FXML
    void handleClear(ActionEvent event)
    {
        if(event.getSource().equals(clear))
        {
           message.setText("");
        }
    }

    @FXML
    void handleSettings(ActionEvent event) {

    }


    @FXML
    void handleClose(ActionEvent event) {
        if(event.getSource().equals(close)){
            System.exit(0);

        }
    }

    @FXML
    void handleReceive(ActionEvent event) {
        if(event.getSource().equals(recieve)){

        }

    }

    @FXML
    void handleSend(ActionEvent event) {
        if (event.getSource().equals(send)) {
            String fullMessage = message.getText();

            message.setText("");


        }

    }

    @FXML
    void handleSubmit(ActionEvent event) {

    }

}
