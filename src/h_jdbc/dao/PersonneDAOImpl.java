package h_jdbc.dao;

import h_jdbc.DbConnection;
import h_jdbc.models.Personne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PersonneDAOImpl implements IPersonneDAO {

    private final Connection connection;

    public PersonneDAOImpl(/*Connection connection*/) throws SQLException, ClassNotFoundException {
        this.connection = DbConnection.getInstance();
    }

    @Override
    public boolean create(Personne personne) {
        return false;
    }

    @Override
    public Personne findById(long id) {
        return null;
    }

    @Override
    public List<Personne> findAll() {
        return null;
    }

    @Override
    public boolean update(Personne personne) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Personne mapResultToPersonne(ResultSet resultSet) {
        return null;
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
