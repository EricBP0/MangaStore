package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Model.Login;

import java.util.List;
import java.util.Optional;

public interface ILoginDAO {
    Login create(Login login);

    Login update(Login login);

    void delete(Login login);

    List<Login> findAll();

    Optional<Login> findById(int id);
}
