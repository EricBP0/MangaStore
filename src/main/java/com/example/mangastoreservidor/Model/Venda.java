package com.example.mangastoreservidor.Model;

import java.util.List;

public class Venda {
    private int numero;

    private int itens;

    private String endereco;

    private double preco;

    public Venda() {
    }

    public Venda(int numero, int itens, String endereco, double preco) {
        this.numero = numero;
        this.itens = itens;
        this.endereco = endereco;
        this.preco = preco;
    }

    public Venda(int itens, String endereco, double preco) {
        this.itens = itens;
        this.endereco = endereco;
        this.preco = preco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getItens() {
        return itens;
    }

    public void setItens(int itens) {
        this.itens = itens;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
