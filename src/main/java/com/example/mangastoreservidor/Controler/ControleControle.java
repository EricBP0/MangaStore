package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.CadastroFX;
import com.example.mangastoreservidor.CentralFX;
import com.example.mangastoreservidor.LoginFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleControle implements Initializable {

    @FXML
    private Button button_Mangas;

    @FXML
    private Button button_Vendas;

    @FXML
    private Label label_Vendas;

    @FXML
    void entrar_TelaMangas(ActionEvent ae) {
        button_Mangas.setOnMouseClicked((event ->{
            System.out.println("Entrar Tela Mangas");
            CentralFX centralFX = new CentralFX();
            LoginFX.getStage().close();
            try{
                centralFX.start(new Stage());
            } catch (Exception e){
                Logger.getLogger(ControleCadastro.class.getName())
                        .log(Level.SEVERE,null, e);
            }
        }));
    }

    @FXML
    void entrar_TelaVendas(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
