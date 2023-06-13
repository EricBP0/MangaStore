package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.CentralFX;
import com.example.mangastoreservidor.ControleFX;
import com.example.mangastoreservidor.DAO.MangaDAO;
import com.example.mangastoreservidor.DAO.VendaDAO;
import com.example.mangastoreservidor.Model.Venda;
import com.example.mangastoreservidor.VendasFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleVendas implements Initializable {

    @FXML
    private Button button_Remover;

    @FXML
    private TextField textFild_Numero;

    @FXML
    private Button button_Voltar;

    @FXML
    private ListView<String> listview_Vendas;

    @FXML
    void removerVenda(ActionEvent event) {
        Venda venda = new Venda();

        venda.setNumero(Integer.parseInt(textFild_Numero.getText()));

        if (textFild_Numero.getText() == null) {
            JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos!");
        }else{
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.delete(venda);
            JOptionPane.showMessageDialog(null,"Venda removida com sucesso!");
        }

    }

    @FXML
    void voltarTela(ActionEvent event) {
        ControleFX controleFX = new ControleFX();
        VendasFX.getStage().close();
        try {
            controleFX.start(new Stage());
        } catch (Exception e) {
            Logger.getLogger(ControleCadastro.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listview_Vendas.getItems().clear();
            VendaDAO vendaDAO = new VendaDAO();
            List<Venda> listVendas = vendaDAO.findAll();
            for (Venda venda : listVendas) {
                System.out.println(venda.getNumero());
                System.out.println(venda.getPreco());
                System.out.println(venda.getItens());
                StringBuilder sb = new StringBuilder();
                sb = sb.append("ID - ").append(venda.getNumero()).append(" - R$ ").append(venda.getPreco()).append(" - Itens : ").append(venda.getItens());
                listview_Vendas.getItems().add(sb.toString());
            }
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
