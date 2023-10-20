package h_jdbc.dao;

import h_jdbc.models.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDao<ID,T extends BaseEntity> {
    boolean create(T object) throws SQLException;

    T findById(ID id);

    List<T> findAll();

    boolean update(T object);

    boolean delete(ID id);

    T mapToObj(ResultSet rs) throws SQLException, IllegalAccessException, ClassNotFoundException;
}
