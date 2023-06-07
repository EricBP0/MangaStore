package com.example.mangastoreservidor.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleCadastro implements Initializable {

@FXML
private Button button_Cadastrar;

@FXML
private ImageView image_Cadastro;

@FXML
private Label label_Loguin;

@FXML
private Label label_Senha;

@FXML
private TextField textFild_Loguin;

@FXML
private TextField textFild_Senha;

@FXML
private TextField textFild_Profissao;


@FXML
private Label label_Profissao;

@FXML
    void cadastrarUsuario(ActionEvent event) {

            }

@FXML
    void voltarTelaLoguin(ActionEvent event) {

            }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
