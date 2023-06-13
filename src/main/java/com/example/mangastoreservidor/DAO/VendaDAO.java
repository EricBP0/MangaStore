package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Connection.ConnectionFactory;
import com.example.mangastoreservidor.Model.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendaDAO implements IVendaDAO{
    @Override
    public Venda create(Venda venda) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO vendas (preco, itensID) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(venda.getNumero()+ "Create");
            statement.setDouble(1, venda.getPreco());
            statement.setInt(2, venda.getItens());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long idGerado = resultSet.getLong(1);
            venda.setNumero(Math.toIntExact(idGerado));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir venda no banco de dados", e);
        }
        return venda;
    }

    @Override
    public Venda update(Venda venda) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE venda SET itensID = ?, preco = ? WHERE numero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, venda.getItens());
            statement.setDouble(2, venda.getPreco());
            statement.setInt(3, venda.getNumero());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar venda : nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda no banco de dados", e);
        }
        return venda;
    }

    @Override
    public void delete(Venda venda) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM venda WHERE numero = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, venda.getNumero());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro a excluir venda", e);
        }
    }

    @Override
    public List<Venda> findAll() {
        List<Venda> venda = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM venda";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Venda vendaBuscada = new Venda(
                        resultSet.getInt("numero"),
                        resultSet.getInt("itens"),
                        resultSet.getDouble("preco"));
                venda.add(vendaBuscada);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a vendas", e);
        }
        return venda;
    }

    @Override
    public Optional<Venda> findById(int id) {
        Optional<Venda> listVenda = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM venda WHERE numero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Venda vendaBuscada = new Venda(
                        resultSet.getInt("numero"),
                        resultSet.getInt("itensID"),
                        resultSet.getDouble("preco"));
                listVenda = Optional.of(vendaBuscada);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar venda no banco de dados", e);
        }

        return listVenda;
    }
}
