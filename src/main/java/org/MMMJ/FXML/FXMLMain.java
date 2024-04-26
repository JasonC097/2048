package org.MMMJ.FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FXMLMain extends Application {

    public FXMLController controller;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public  void start(Stage primaryStage) throws IOException {
        //Load the FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/2048.fxml"));
        Parent root = loader.load();
        this.controller = loader.getController();
        //set up the stage and show it
        primaryStage.setTitle("2048 Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
