package com.example.mangastoreservidor.Controler;

import com.example.mangastoreservidor.DAO.MangaDAO;
import com.example.mangastoreservidor.DAO.VendaDAO;
import com.example.mangastoreservidor.Model.Manga;
import com.example.mangastoreservidor.Model.Venda;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.util.List;

public class JasperControler {
    public void gerarRelatorioManga() throws JRException{
        MangaDAO mangaDAO = new MangaDAO();
        List<Manga> mangaList = mangaDAO.findAll();

        JasperReport relatorioManga = JasperCompileManager.compileReport("src/main/resources/com/example/mangastoreservidor/RelatorioMangaStore.jrxml");

        JasperPrint relatorio = JasperFillManager.fillReport(relatorioManga, null, new JRBeanCollectionDataSource(mangaList));

        JDialog tela = new JDialog( new JDialog(), "Relatorio Manga", true);
        tela.setSize(800, 600);
        JRViewer painel = new JRViewer(relatorio);
        tela.getContentPane().add(painel);
        tela.setVisible(true);
    }
    public void gerarRelatorioVenda() throws JRException{
        VendaDAO vendaDAO = new VendaDAO();
        List<Venda> vendaList = vendaDAO.findAll();

        JasperReport relatorioManga = JasperCompileManager.compileReport("src/main/resources/com/example/mangastoreservidor/RelatorioVendas.jrxml");

        JasperPrint relatorio = JasperFillManager.fillReport(relatorioManga, null, new JRBeanCollectionDataSource(vendaList));

        JDialog tela = new JDialog( new JDialog(), "Relatorio Vendas", true);
        tela.setSize(800, 600);
        JRViewer painel = new JRViewer(relatorio);
        tela.getContentPane().add(painel);
        tela.setVisible(true);
    }
}
