package org.example.actualjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = FXMLLoader.load(getClass().getResource("scnMain.fxml"));

        Image icon = new Image("/car.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Mediocre Demo Title");
        primaryStage.setResizable(false);



        /* primaryStage.setX(50);
        primaryStage.setY(50);*/

        /*primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("YOU CAN'T ESCAPE unless you press q");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));*/


        primaryStage.show();

    }
}