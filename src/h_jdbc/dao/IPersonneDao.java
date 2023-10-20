package h_jdbc.dao;

import h_jdbc.models.Personne;

import java.sql.ResultSet;
import java.util.List;

public interface IPersonneDao {
    boolean create(Personne object);

    Personne findById(Long id);

    List<Personne> findAll();

    boolean update(Personne object);

    boolean delete(Long id);

    Personne mapResultToPersonne(ResultSet rs);
}
