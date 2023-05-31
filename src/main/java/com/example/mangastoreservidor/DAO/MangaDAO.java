package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Connection.ConnectionFactory;
import com.example.mangastoreservidor.Model.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MangaDAO implements IMangaDAO{


    @Override
    public Manga create(Manga manga) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO manga (anime, edicao, titulo, preco) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(manga.getTitulo()+ "Create");
            statement.setString(1, manga.getAnime());
            statement.setString(2, manga.getEdicao());
            statement.setString(3, manga.getTitulo());
            statement.setDouble(4, manga.getPreco());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long idGerado = resultSet.getLong(1);
            manga.setId(Math.toIntExact(idGerado));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir prato no banco de dados", e);
        }
        return manga;
    }

    @Override
    public Manga update(Manga manga) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE manga SET anime = ?, edicao = ?, titulo = ?, preco = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, manga.getAnime());
            statement.setString(2, manga.getEdicao());
            statement.setString(3, manga.getTitulo());
            statement.setDouble(4, manga.getPreco());
            statement.setInt(5, manga.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar prato: nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar prato no banco de dados", e);
        }
        return manga;
    }

    @Override
    public void delete(Manga login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM manga WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, login.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro a excluir o cadastro", e);
        }
    }

    @Override
    public List<Manga> findAll() {
        List<Manga> listaManga = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM pratos";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getDouble("preco"));
                listaManga.add(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a cadastros", e);
        }
        return listaManga;
    }

    @Override
    public Optional<Manga> findById(int id) {
        Optional<Manga> listaManga = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM login WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getDouble("preco"));
                listaManga = Optional.of(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prato no banco de dados", e);
        }

        return listaManga;
    }
}
