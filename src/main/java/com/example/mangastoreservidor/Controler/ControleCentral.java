package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.CentralFX;
import com.example.mangastoreservidor.ControleFX;
import com.example.mangastoreservidor.DAO.MangaDAO;
import com.example.mangastoreservidor.Model.Manga;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleCentral implements Initializable {

    @FXML
    private AnchorPane anchorpane_Central;

    @FXML
    private Button button_Adicionar;

    @FXML
    private Button button_Remover;

    @FXML
    private Button button_Voltar;

    @FXML
    private Button button_alterar;

    @FXML
    private Label label_Id;

    @FXML
    private Label label_Titulo;

    @FXML
    private Label label_anime;

    @FXML
    private ListView<String> listview_ListaMangas;

    @FXML
    private TextField textfild_anime;

    @FXML
    private TextField textfild_edicao;

    @FXML
    private TextField textfild_preco;

    @FXML
    private TextField textfild_quantidade;

    @FXML
    private TextField textfilld_titulo;
    @FXML
    private TextField textfild_Id;

    @FXML
    void adicionarManga(ActionEvent event) {
        System.out.println("enviando prato");
        Manga manga = new Manga();

        manga.setAnime(textfild_anime.getText());
        manga.setEdicao(textfild_edicao.getText());
        manga.setPreco(Double.parseDouble(textfild_preco.getText()));
        manga.setQuantidade(Integer.parseInt(textfild_quantidade.getText()));
        manga.setTitulo(textfilld_titulo.getText());

        if (textfild_edicao.getText() == null || textfild_anime.getText() == null || textfild_preco.getText() == null || textfild_quantidade.getText() == null || textfilld_titulo.getText() == null) {
            JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos!");
        }else{
            MangaDAO mangaDAO = new MangaDAO();
            mangaDAO.create(manga);
            JOptionPane.showMessageDialog(null, "Manga criado com sucesso!");
        }
    }

    @FXML
    void alterarManga(ActionEvent event) {
        Manga manga = new Manga();

        manga.setId(Integer.parseInt(textfild_Id.getText()));
        manga.setAnime(textfild_anime.getText());
        manga.setEdicao(textfild_edicao.getText());
        manga.setPreco(Double.parseDouble(textfild_preco.getText()));
        manga.setQuantidade(Integer.parseInt(textfild_quantidade.getText()));
        manga.setTitulo(textfilld_titulo.getText());
        if (textfild_edicao.getText() == null || textfild_anime.getText() == null || textfild_preco.getText() == null || textfild_quantidade.getText() == null || textfilld_titulo.getText() == null || textfild_Id.getText() == null) {
            JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos!");
        }else{
            MangaDAO mangaDAO = new MangaDAO();
            mangaDAO.update(manga);
            JOptionPane.showMessageDialog(null, "Manga criado com sucesso!");
        }
    }

    @FXML
    void removerManga(ActionEvent event) {
        Manga manga = new Manga();

        manga.setId(Integer.parseInt(textfild_Id.getText()));

        if (textfild_Id.getText() == null) {
            JOptionPane.showMessageDialog(null,"Todos os campos devem ser preenchidos!");
        }else{
            MangaDAO mangaDAO = new MangaDAO();
            mangaDAO.delete(manga);
            JOptionPane.showMessageDialog(null,"Manga removido com sucesso!");
        }
    }
    @FXML
    void voltarTelaCentral(ActionEvent event) {
        ControleFX controleFX = new ControleFX();
        CentralFX.getStage().close();
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
            listview_ListaMangas.getItems().clear();
            MangaDAO mangaDAO = new MangaDAO();
            List<Manga> listManga = mangaDAO.findAll();
            for (Manga manga : listManga) {
                System.out.println(manga.getId());
                System.out.println(manga.getAnime());
                System.out.println(manga.getEdicao());
                System.out.println(manga.getTitulo());
                System.out.println(manga.getQuantidade());
                System.out.println(manga.getPreco());
                StringBuilder sb = new StringBuilder();
                sb = sb.append("ID : ").append(manga.getId()).append(" - Anime: ").append(manga.getAnime()).append(" - Edição : ").append(manga.getEdicao()).append("- Titulo : ").append(manga.getTitulo()).append("- Quantidade : ").append(manga.getQuantidade()).append("- Preço : ").append(manga.getPreco());
                listview_ListaMangas.getItems().add(sb.toString());
            }
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
