package h_jdbc.dao;

import h_jdbc.models.Personne;

import java.sql.ResultSet;
import java.util.List;

//DAO : Data Access Object
public interface IPersonneDAO { // (CRUD) minimum de fonctionnalités requises pour les opérations en BDD
    boolean create(Personne personne); // Sauvegarder une personne en BDD (C)
    Personne findById(long id); // (R)
    List<Personne> findAll(); // (R)

    boolean update(Personne personne); // (U)
    boolean delete(long id); // (D)

    Personne mapResultToPersonne(ResultSet resultSet); // Pour convertir le retour d'une requête en objet
    void createTable();
}
