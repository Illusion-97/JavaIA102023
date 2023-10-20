package h_jdbc.dao;

import h_jdbc.DbConnection;
import h_jdbc.models.Personne;
import h_jdbc.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAOImpl implements IPersonneDAO {

    private final Connection connection;

    public PersonneDAOImpl(/*Connection connection*/) throws SQLException, ClassNotFoundException {
        this.connection = DbConnection.getInstance();
    }

    @Override
    public boolean create(Personne personne) {
        // PreparedStatement prévoit une syntaxe permettant de prévenir l'injection SQL
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Personne(version,nom,prenom,role) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS // demande à ma requete de me retourner les id générés
        )) {
            // A l'aide de différentes fonctions pour chaque type souhaité, preparedStatement vérifie l'intégrité de la donnée avant de la passer à la requête
            preparedStatement.setInt(1, personne.getVersion());
            preparedStatement.setString(2, personne.getNom());
            preparedStatement.setString(3, personne.getPrenom());
            preparedStatement.setString(4, personne.getRole().name());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys(); // Un ResultSet représente le retour d'une requête SQL (Tableau avec des colonnes et des données sur chaque ligne)
            if(resultSet.next()) { // si j'ai bien une donnée à la ligne suivante
                personne.setId(resultSet.getLong(1)); //
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public Personne findById(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Personne WHERE id = ?"
        )) {
            preparedStatement.setLong(1,id); // Toujours passer par une vérification quel que soit le type de variable
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapResultToPersonne(resultSet);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<Personne> findAll() {
        List<Personne> personnes = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Personne");
            while (resultSet.next()) personnes.add(mapResultToPersonne(resultSet));
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return personnes;
    }

    @Override
    public boolean update(Personne personne) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Personne SET version = ?, nom = ?, prenom = ?, role = ? WHERE id = ? AND version = ?"
        )) {
            preparedStatement.setInt(1, personne.getVersion() + 1);
            preparedStatement.setString(2, personne.getNom());
            preparedStatement.setString(3, personne.getPrenom());
            preparedStatement.setString(4, personne.getRole().name());
            preparedStatement.setLong(5,personne.getId());
            preparedStatement.setInt(6, personne.getVersion());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Personne WHERE id = ?"
        )) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Personne mapResultToPersonne(ResultSet resultSet) {
        try {
            return new Personne(
                    resultSet.getLong("id"),
                    resultSet.getInt("version"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    Role.valueOf(resultSet.getString("role"))
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void createTable() {
        try (Statement statement = connection.createStatement()) { // Statement représente une "exécution" de requête
            String SQL = """
                    CREATE TABLE IF NOT EXISTS Personne
                    (id INT(10) NOT NULL AUTO_INCREMENT, version INT(10), nom VARCHAR(255), prenom VARCHAR(255), role VARCHAR(255), PRIMARY KEY ( id ))
                    """;

            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
