package h_jdbc.dao;

import h_jdbc.DbConnection;
import h_jdbc.TableManager;
import h_jdbc.models.BaseEntity;
import h_jdbc.models.Role;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao<T extends BaseEntity> implements IDao<Long, T> {
    private final Connection connection;
    private final Class<T> clazz;

    public Dao(Class<T> clazz) throws SQLException, ClassNotFoundException {
        this.clazz = clazz;
        this.connection = DbConnection.getInstance();
    }

    @Override
    public boolean create(T object) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(TableManager.getCreateReq(clazz), Statement.RETURN_GENERATED_KEYS)) {
           setValues(ps,object,true);
           ps.execute();
           ResultSet rs = ps.getGeneratedKeys();
           if (rs.next()) {
               object.setId(rs.getLong(1));
           }
           connection.commit();
           return true;
        } catch (IllegalAccessException e) {
            rollback(e);
            return false;
        }
    }

    private void rollback(Exception e) {
        try {
            connection.rollback();
        } catch (SQLException r) {
            r.printStackTrace();
        }
        e.printStackTrace();
    }

    @Override
    public T findById(Long aLong) {
        T object = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + clazz.getSimpleName() + " WHERE id = ?")) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            object = mapToObj(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public List<T> findAll() {
        List<T> all = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM " + clazz.getSimpleName());
            while (rs.next()){
                all.add(mapToObj(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public boolean update(T object) {
        int updated = 0;
        try (PreparedStatement ps = connection.prepareStatement(TableManager.getUpdateReq(clazz,object))) {
            setValues(ps,object,false);
            updated = ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            rollback(e);
        }
        return updated > 0;
    }

    @Override
    public boolean delete(Long aLong) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                String.format("DELETE FROM %s WHERE id = %d",clazz.getSimpleName(),aLong))) {
            preparedStatement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback(e);
            return false;
        }
    }

    @Override
    public T mapToObj(ResultSet rs) throws SQLException, IllegalAccessException, ClassNotFoundException {
        if(rs == null) return null;
        T object;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
        List<Field> fields = TableManager.getAllFields(clazz);
        for(Field field : fields) {
            boolean accessible = field.canAccess(object);
            if(!accessible) field.setAccessible(true);
            switch (field.getType().getSimpleName()) {
                case "Role" -> field.set(object, Role.valueOf(rs.getString(field.getName())));
                case "String" -> field.set(object, rs.getString(field.getName()));
                case "int" -> field.set(object, rs.getInt(field.getName()));
                case "double" -> field.set(object, rs.getDouble(field.getName()));
                case "long" -> field.set(object, rs.getLong(field.getName()));
                default -> field.set(object, new Dao<>((Class<? extends BaseEntity>) field.getType()).findById(rs.getLong(field.getName())));
            }
            field.setAccessible(accessible);
        }
        return object;
    }

    private void setValues(PreparedStatement ps, T object, boolean setId) throws SQLException, IllegalAccessException {
        List<Field> fields = TableManager.getAllFields(clazz);
        if(!setId) fields = fields.subList(1,fields.size());
        for(Field field : fields) {
            boolean accessible = field.canAccess(object);
            if(!accessible) field.setAccessible(true);
            switch (field.getType().getSimpleName()) {
                case "String", "Role" -> ps.setString(fields.indexOf(field) + 1, field.get(object).toString());
                case "int" -> ps.setInt(fields.indexOf(field) + 1, (int)field.get(object));
                case "double" -> ps.setDouble(fields.indexOf(field) + 1, (double)field.get(object));
                case "long" -> ps.setLong(fields.indexOf(field) + 1, object.getId());
                default -> ps.setLong(fields.indexOf(field) + 1, ((BaseEntity) field.get(object)).getId());
            }
            field.setAccessible(accessible);
        }
    }
}
