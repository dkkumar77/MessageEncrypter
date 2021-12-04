package Controllers;

import Model.Constants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Date;

public class bootController {

    protected String data[] = new String[6];

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
    private Label warning;

    @FXML
    private JFXTextField privKey,sendMessageID;

    @FXML
    private JFXPasswordField sendPrivateKey;


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
    void handleSubmit(ActionEvent event) throws UnknownHostException, IOException
    {
        warning.setText("");

        if(!sendPrivateKey.getText().equals("") && !sendMessageID.getText().equals("") && !message.getText().equals("")){

            printData(getData());



        message.setText("Message has successfully sent ");

        }
        else{
            warning.setText("Fields can not be empty");
    }


    }



    private String [] getData() throws IOException {
        data[0] = sendPrivateKey.getText();
        data[1] = sendMessageID.getText();
        data[2] =  message.getText();
        data[3] = java.util.Calendar.getInstance().getTime().toString();
        data[4] = InetAddress.getLocalHost().getHostAddress();
        data[5] = System.getProperty("os.name");
        return data;

    }


    @SuppressWarnings("unused")
    private void printData(String[] data) {
        for(int i = 0; i < data.length; i++){
            System.out.println("\n" + data[i]);

        }

    }

}
