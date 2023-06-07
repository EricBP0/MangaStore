package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Connection.ConnectionFactory;
import com.example.mangastoreservidor.Model.Integer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MangaDAO implements IMangaDAO{


    @Override
    public Integer create(Integer integer) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO manga (anime, edicao, titulo, preco) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(integer.getTitulo()+ "Create");
            statement.setString(1, integer.getAnime());
            statement.setString(2, integer.getEdicao());
            statement.setString(3, integer.getTitulo());
            statement.setDouble(4, integer.getPreco());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long idGerado = resultSet.getLong(1);
            integer.setId(Math.toIntExact(idGerado));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir manga no banco de dados", e);
        }
        return integer;
    }

    @Override
    public Integer update(Integer integer) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE manga SET anime = ?, edicao = ?, titulo = ?, preco = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, integer.getAnime());
            statement.setString(2, integer.getEdicao());
            statement.setString(3, integer.getTitulo());
            statement.setDouble(4, integer.getPreco());
            statement.setInt(5, integer.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar Manga: nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Manga no banco de dados", e);
        }
        return integer;
    }

    @Override
    public void delete(Integer login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM manga WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, login.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro a excluir o Manga", e);
        }
    }

    @Override
    public List<Integer> findAll() {
        List<Integer> listaInteger = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer integer = new Integer(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getDouble("preco"));
                listaInteger.add(integer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a mangas", e);
        }
        return listaInteger;
    }

    @Override
    public Optional<Integer> findById(int id) {
        Optional<Integer> listaManga = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer integer = new Integer(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getDouble("preco"));
                listaManga = Optional.of(integer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar manga no banco de dados", e);
        }

        return listaManga;
    }
}
