package Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Controller {


    @FXML
    private AnchorPane sendPane;


    @FXML
    private JFXButton receive;

    @FXML
    private AnchorPane receivePane;

    @FXML
    private JFXTextArea receiveTextArea;

    @FXML
    private JFXTextField idKey;

    @FXML
    private JFXTextField privKey;

    @FXML
    private JFXButton submitReceive;

    @FXML
    private JFXButton setting;

    @FXML
    private JFXButton send;

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
    void handleSubmitReceive(ActionEvent event) {

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
        if(event.getSource().equals(receive)){
            sendPane.toBack();
            receivePane.toFront();

        }

    }

    @FXML
    void handleSend(ActionEvent event) {
        if (event.getSource().equals(send)) {
            receivePane.toBack();

            sendPane.toFront();



        }

    }

    @FXML
    void handleSubmit(ActionEvent event) {

    }

}
