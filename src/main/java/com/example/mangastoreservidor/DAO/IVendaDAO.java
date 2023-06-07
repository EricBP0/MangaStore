package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Model.Venda;

import java.util.List;
import java.util.Optional;

public interface IVendaDAO {
    Venda create(Venda venda);

    Venda update(Venda venda);

    void delete(Venda venda);

    List<Venda> findAll();

    Optional<Venda> findById(int id);
}
