package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Model.Integer;

import java.util.List;
import java.util.Optional;

public interface IMangaDAO {
    Integer create(Integer integer);

    Integer update(Integer integer);

    void delete(Integer login);

    List<Integer> findAll();

    Optional<Integer> findById(int id);
}
