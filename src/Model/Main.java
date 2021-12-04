package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("bootPane.fxml"));
        primaryStage.setTitle("Message Encrypter V1");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(500);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);

    }


    public static void main(String[] args) {
        launch(args);
    }


}
