package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.DAO.VendaDAO;
import com.example.mangastoreservidor.Model.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControleVendas implements Initializable {

    @FXML
    private Button button_Alterar;

    @FXML
    private Button button_Remover;

    @FXML
    private TextField textFild_Endereco;

    @FXML
    private TextField textFild_Numero;

    @FXML
    private TextField textFild_Preco;
    @FXML
    private Button button_Voltar;

    @FXML
    private ListView<String> listview_Vendas;

    @FXML
    void alterarVenda(ActionEvent event) {

    }
    @FXML
    void removerVenda(ActionEvent event) {

    }

    @FXML
    void voltarTela(ActionEvent event) {

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
