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

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.security.*;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

public class bootController
    {

        private static final int MAX_VALUE = 0x7FFFFFFF;
        protected String[] outboundData = new String[6];    // For each entry into Postgre database
        protected String[] inboundData = new String[2];

        @FXML
        private AnchorPane sendPane;        // Defaulted to front

        @FXML
        private AnchorPane receivePane;

        @FXML
        private JFXButton send;             // Button for sendPane

        @FXML
        private JFXButton receive, download;          // Button for receivePane

        @FXML
        private Label sendWarning;          // Activated with empty input bodies

        @FXML
        private Label success;          // Activated when outgoing message is successfully processed

        @FXML
        private JFXButton close;

        @FXML
        private JFXButton clear;

        @FXML
        private JFXTextArea outboundMessage;

        @FXML
        private JFXButton settings;

        @FXML
        private JFXButton sendSubmit;

        @FXML
        private JFXTextField sendMessageID;

        @FXML
        private JFXTextField sendPrivateKey;


        @FXML
        private JFXTextArea inboundMessage;

        @FXML
        private JFXTextField receiveMessageID;


        @FXML
        private JFXTextField receivePrivateKey;


        @FXML
        private JFXButton receiveSubmit;

        @FXML
        private Label receiveWarning;


        /**
         * GLOBALS
         */
        Parent root;
        Scene scene;
        Stage stage;
        Database e;
        String messageID;
        String privateKey;



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
        void handleReceive(ActionEvent event) throws FileNotFoundException, IOException   // Clicking receive button pushes receive pane
        {
            if (event.getSource().equals(receive))
            {
                this.e = new Database();


                sendPane.toBack();
                receivePane.toFront();

            }

        }

        @FXML
        void handleDownload(ActionEvent event) throws IOException {

            if (event.getSource().equals(download)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(messageID + ".txt", true));
                writer.write("Message ID: " + messageID + "\n");
                writer.write("Private Key " + privateKey);


                writer.close();


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
                outboundMessage.setText("");
                sendMessageID.setText("");
                sendPrivateKey.setText("");
            }
        }

        @FXML
        void handleSendSubmit(ActionEvent event) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InterruptedException {
            if (event.getSource().equals(sendSubmit))
            {
                sendWarning.setText("");
                success.setText("");


                if (!outboundMessage.getText().equals(""))
                {
                    //printData(getOutboundData());
                    Database e = new Database();

                    e.add(getOutboundData());
                    success.setText("Message sent!");
                } else if (!sendMessageID.getText().equals("")
                        && (!sendPrivateKey.getText().equals("")))
                {
                    sendWarning.setText("Message cannot be empty");
                } else
                {
                    sendWarning.setText("Fill all fields");
                }

                sendMessageID.setText(messageID);
                sendPrivateKey.setText(privateKey);

                outboundMessage.setText("");



            }



        }

        private String[] getOutboundData() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {


            messageID = String.valueOf(new Random().nextInt(MAX_VALUE));
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            PrivateKey priv = keyGen.generateKeyPair().getPrivate();
            this.privateKey = priv.getEncoded().toString();

            outboundData[0] = messageID;
            outboundData[1] = privateKey;
            outboundData[2] = outboundMessage.getText();
            outboundData[3] = java.util.Calendar.getInstance().getTime().toString();
            outboundData[4] = InetAddress.getLocalHost().getHostAddress();
            outboundData[5] = System.getProperty("os.name");
            return outboundData;

        }






        // RECEIVE PANE


        @FXML
        void handleReceiveSubmit(ActionEvent event) throws InterruptedException {
            if(event.getSource().equals(receiveSubmit))
            {
                receiveWarning.setText("Verifying....");

                if(!receiveMessageID.getText().equals("")
                        && !receivePrivateKey.getText().equals(""))
                {
                    Object obj = new Object();

                    //printData(getInboundData());
                    //inboundMessage.setText("Message retrieval simulated. ");

                    int statusCode = 0;
                    inboundMessage.setText("Verifying..");


                    /*
                    synchronized(obj) {
                        do {
                            obj.wait(1000, 0);
                            statusCode = 1;
                            obj.notify();
                        }
                        while (statusCode == 0);

                    }

                     */

                    if(e.verifyCredentials(receiveMessageID.getText(), receivePrivateKey.getText())){
                        inboundMessage.setText(e.findString(receiveMessageID.getText()));

                    }
                    /**
                     * Message ID -> Private Key that the user enters matches the private key for the message id
                     * -> print String
                     */
                }
                else
                {
                    receiveWarning.setText("Fields cannot be empty. ");
                }

                receiveMessageID.setText("");
                receivePrivateKey.setText("");

            }

        }

        private String[] getInboundData()
        {
            inboundData[0] = receiveMessageID.getText();
            inboundData[1] = receivePrivateKey.getText();
            return inboundData;
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
