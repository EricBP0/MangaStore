package com.example.mangastoreservidor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleFX extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ControleFX.stage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ControleFX.class.getResource("Controle.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Controle");
        stage.setScene(scene);
        setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
