package org.MMMJ.FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.MMMJ.Board;
import org.MMMJ.GameManager;
import org.MMMJ.Movement;
import org.MMMJ.Tile;

import java.io.IOException;
import java.security.Key;
import java.util.Scanner;

public class FXMLMain extends Application {
    /** An instance of the {@link FXMLController} class**/
    public FXMLController controller;

    public GameManager manager;

//    public void init() throws Exception {
//        super.init();
//        manager = new GameManager(FXMLController.getTheBoard(), FXMLController.getMovement(), 16);
//         manager.getBoard().addTile(1,2, new Tile(16));
//        while(manager.didPlayerWin()){
//            System.out.println("Player Won or Lost");
//        }
//
//
//
//    }





    /**
     * Initialized the user interface for the game
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Load the FXML file
//        this.controller = loader.getController();
        //set up the stage and show it
//        primaryStage.setTitle("2048 Game");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/2048.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
