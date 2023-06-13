package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.CadastroFX;
import com.example.mangastoreservidor.CentralFX;
import com.example.mangastoreservidor.ControleFX;
import com.example.mangastoreservidor.DAO.LoginDAO;
import com.example.mangastoreservidor.LoginFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleLogin implements Initializable {

        @FXML
        private Button button_Cadastrar;

        @FXML
        private Button button_Entrar;

        @FXML
        private ImageView imagem_Login;

        @FXML
        private Label label_Senha;

        @FXML
        private Label label_login;

        @FXML
        private TextField textFild_Login;

        @FXML
        private TextField textFild_Senha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagemLogin;

        try {
            imagemLogin = new Image(new FileInputStream("src/main/java/com/example/mangastoreservidor/Drawble/mangaComics.jpg"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        imagem_Login.setImage(imagemLogin);
    }

    @FXML
    void entrarTelaCadastro(ActionEvent ae) {
            button_Cadastrar.setOnMouseClicked((event ->{
            System.out.println("Entrar Tela Cadastro");
            CadastroFX cadastroFX = new CadastroFX();
            LoginFX.getStage().close();
            try{
                cadastroFX.start(new Stage());
            } catch (Exception e){
                Logger.getLogger(ControleCadastro.class.getName())
                        .log(Level.SEVERE,null, e);
            }
        }));

    }

    @FXML
    void enviar_loguin(ActionEvent ae) {
        LoginDAO loginDAO = new LoginDAO();
        try {
            if(textFild_Login.getText().isEmpty() || textFild_Senha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuário e senha devem ser preenchidos!");
            }
                if (loginDAO.checkLogin(textFild_Login.getText(), textFild_Senha.getText())) {
                    button_Entrar.setOnMouseClicked((event -> {
                        System.out.println("Entrar Central");
                        ControleFX controleFX = new ControleFX();
                        LoginFX.getStage().close();
                        try {
                            controleFX.start(new Stage());
                        } catch (Exception e) {
                            Logger.getLogger(ControleCadastro.class.getName())
                                    .log(Level.SEVERE, null, e);
                        }
                    }));
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario ou Senha incorreto!");
                }
            }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
