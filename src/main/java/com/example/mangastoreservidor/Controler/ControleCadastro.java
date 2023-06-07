package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.CadastroFX;
import com.example.mangastoreservidor.DAO.LoginDAO;
import com.example.mangastoreservidor.LoginFX;
import com.example.mangastoreservidor.Model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
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
private Button button_Voltar;

@FXML
    void cadastrarUsuario(ActionEvent event) {
        String nome = textFild_Loguin.getText();
        String senha = textFild_Senha.getText();

        LoginDAO loginDAO = new LoginDAO();

        if(nome.isEmpty() || senha.isEmpty()){
            JOptionPane.showMessageDialog(null,"Deve-se preencher todos os campos!");
            return;
        }
        Login login = new Login(nome, senha);

        try{
            loginDAO.create(login);
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_Voltar.setOnMouseClicked((event ->{
            System.out.println("Voltar");
            LoginFX loginFX = new LoginFX();
            CadastroFX.getStage().close();
            try {
                loginFX.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
