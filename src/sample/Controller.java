package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

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
    void handleClose(ActionEvent event) {
        if(event.getSource().equals(close)){
            System.exit(0);

        }
    }

    @FXML
    void handleRecieve(ActionEvent event) {
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
