package org.example.actualjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main (String[] args){ launch(); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("scnMain.fxml"));
            Image icon = new Image("/car.png");
            primaryStage.getIcons().add(icon);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
