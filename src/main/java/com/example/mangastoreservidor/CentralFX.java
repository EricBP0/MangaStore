package com.example.mangastoreservidor;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class CentralFX extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CentralFX.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Central.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Central");
        setStage(stage);
        stage.show();
    }
}
