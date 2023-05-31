package com.example.mangastoreservidor.DAO;

import com.example.mangastoreservidor.Connection.ConnectionFactory;
import com.example.mangastoreservidor.Model.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.mangastoreservidor.Model.Login;
import org.jasypt.util.text.BasicTextEncryptor;

public class LoginDAO implements ILoginDAO{
    public boolean checkLogin(String nome, String senha) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("senha");

        String querry = "SELECT senha FROM login WHERE nome = '" + nome + "'" ;
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();
            String senhaDesencrypt = rs.getString("senha");
            senhaDesencrypt = textEncryptor.decrypt(senhaDesencrypt);
            if(senhaDesencrypt.equals(senha)) {
                return true;
            }else{
                return false;
            }
        }
        catch (SQLException e){
            System.out.println(e + "+++" +e.getMessage());
            return false;
        }
    }
    @Override
    public Login create(Login login){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("senha");
        String senhaEncrypt = textEncryptor.encrypt(login.getSenha());

        String query = "INSERT INTO login(nome, senha) VALUES(?,?)";
        try {
            Connection connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, login.getNome());
                statement.setString(2, senhaEncrypt);

                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
            } catch (Throwable var6) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }
                throw var6;
            }
            connection.close();

            return login;
        }
        catch (SQLException var7){
            throw new RuntimeException(var7);
        }
    }

    @Override
    public Login update(Login login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE login SET nome = ?, senha = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login.getNome());
            statement.setString(2, login.getSenha());
            statement.setInt(3, login.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro a alterar cadastro");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a atualizar cadastro no banco", e);
        }
        return login;
    }

    @Override
    public void delete(Login login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM login WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, login.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro a excluir o cadastro", e);
        }
    }

    @Override
    public List<Login> findAll() {
        List<Login> login = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM pratos";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Login cadastro = new Login(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("senha"));
                login.add(cadastro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a cadastros", e);
        }
        return login;
    }

    @Override
    public Optional<Login> findById(int id) {
        Optional<Login> listLogin = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM login WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Login login = new Login(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("preco"));
                listLogin = Optional.of(login);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prato no banco de dados", e);
        }

        return listLogin;
    }
}
