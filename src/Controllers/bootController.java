package Controllers;

import Model.Constants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class bootController {


    String messageID;
    String privateKey;
    String messageBody;
    String date;
    String IP;
    String OS;

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

    Parent root;
    Scene scene;
    Stage stage;


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
    void handleSettings(ActionEvent event) throws IOException {

        if(event.getSource().equals(setting)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.SETTING));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

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
    void handleSubmit(ActionEvent event) throws UnknownHostException
    {


      //   privateKey = privKey.getText();
      //  messageID = idKey.getText();
        messageBody = message.getText();
        date = java.util.Calendar.getInstance().getTime().toString();
        IP = InetAddress.getLocalHost().getHostName();
        OS = System.getProperty("os.name");



    }


}
