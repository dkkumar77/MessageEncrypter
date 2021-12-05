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

import static java.lang.Thread.sleep;

public class bootController
    {

        protected String data[] = new String[6];    // For each entry into Postgre database

        @FXML
        private AnchorPane sendPane;        // Defaulted to front

        @FXML
        private AnchorPane receivePane;

        @FXML
        private JFXButton send;             // Button for sendPane

        @FXML
        private JFXButton receive;          // Button for receivePane

        @FXML
        private Label warning;          // Activated with empty input bodies

        @FXML
        private Label success;          // Activated when outgoing message is successfully processed

        @FXML
        private JFXButton close;

        @FXML
        private JFXButton clear;

        @FXML
        private JFXTextArea messageInput;

        @FXML
        private JFXButton settings;

        @FXML
        private JFXButton sendSubmit;

        @FXML
        private JFXPasswordField sendPrivateKey;


        @FXML
        private JFXTextArea receiveTextArea;

        @FXML
        private JFXTextField idKey;


        @FXML
        private JFXTextField privKey, sendMessageID;


        @FXML
        private JFXButton receiveSubmit;


        Parent root;
        Scene scene;
        Stage stage;


        @FXML
        void handleSend(ActionEvent event)      // Clicking send button pushes send pane
        {
            if (event.getSource().equals(send))
            {
                receivePane.toBack();
                sendPane.toFront();
            }

        }


        @FXML
        void handleReceive(ActionEvent event)   // Clicking receive button pushes receive pane
        {
            if (event.getSource().equals(receive))
            {
                sendPane.toBack();
                receivePane.toFront();
            }

        }

        @FXML
        void handleClose(ActionEvent event)     // Close button exits program from application
        {
            if (event.getSource().equals(close))
            {
                System.exit(0);
            }
        }

        @FXML
        void handleSettings(ActionEvent event) throws IOException   // Boots new settings window
        {

            if (event.getSource().equals(settings))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.SETTING));
                root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        }



        // SEND PANE


        @FXML
        void handleClear(ActionEvent event)
        {
            if (event.getSource().equals(clear))
            {
                messageInput.setText("");
            }
        }

        @FXML
        void handleSendSubmit(ActionEvent event) throws IOException
        {
            if (event.getSource().equals(sendSubmit))
            {
                warning.setText("");
                success.setText("");


                if (!sendPrivateKey.getText().equals("")
                        && !sendMessageID.getText().equals("")
                        && !messageInput.getText().equals(""))
                {
                    printData(getData());
                    success.setText("Message sent!");
                } else
                {
                    warning.setText("Fields can not be empty");
                }

                messageInput.setText("");
                sendMessageID.setText("");
                sendPrivateKey.setText("");
            }

        }


        // RECEIVE PANE


        @FXML
        void handleReceiveSubmit(ActionEvent event)
        {
            if(event.getSource().equals(receiveSubmit))
            {

            }

        }


        private String[] getData() throws IOException
        {
            data[0] = sendMessageID.getText();
            data[1] = messageInput.getText();
            data[2] = sendPrivateKey.getText();
            data[3] = java.util.Calendar.getInstance().getTime().toString();
            data[4] = InetAddress.getLocalHost().getHostAddress();
            data[5] = System.getProperty("os.name");
            return data;

        }


        @SuppressWarnings("unused")
        private void printData(String[] data)
        {
            for (int i = 0; i < data.length; i++)
            {
                System.out.println("\n" + data[i]);

            }
        }

    }
