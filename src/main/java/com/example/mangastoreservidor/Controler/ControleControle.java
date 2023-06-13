package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

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
    private Button button_gerarRelatorioVendas;

    @FXML
    private Button button_gerarRelatorio;

    @FXML
    private Label label_Vendas;

    JasperControler controler;
    @FXML
    void gerar_relatorio(ActionEvent event) throws JRException {

        controler = new JasperControler();
        controler.gerarRelatorioManga();
    }

    @FXML
    void entrar_TelaMangas(ActionEvent ae) {
        button_Mangas.setOnMouseClicked((event ->{
            System.out.println("Entrar Tela Mangas");
            CentralFX centralFX = new CentralFX();
            ControleFX.getStage().close();
            try{
                centralFX.start(new Stage());
            } catch (Exception e){
                Logger.getLogger(ControleCadastro.class.getName())
                        .log(Level.SEVERE,null, e);
            }
        }));
    }

    @FXML
    void entrar_TelaVendas(ActionEvent ae) {
        button_Vendas.setOnMouseClicked((event ->{
            System.out.println("Entrar Tela Vendas");
            VendasFX vendasFX = new VendasFX();
            ControleFX.getStage().close();
            try{
                vendasFX.start(new Stage());
            } catch (Exception e){
                Logger.getLogger(ControleCadastro.class.getName())
                        .log(Level.SEVERE,null, e);
            }
        }));
    }
    @FXML
    void gerarRelatorioVendas(ActionEvent event) throws JRException {
        controler = new JasperControler();
        controler.gerarRelatorioVenda();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
