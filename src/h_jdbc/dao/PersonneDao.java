package h_jdbc.dao;

import h_jdbc.DbConnection;
import h_jdbc.models.Personne;
import h_jdbc.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDao implements IPersonneDao {
    private final Connection connection;

    public PersonneDao() throws SQLException, ClassNotFoundException {
        this.connection = DbConnection.getInstance();
    }


    @Override
    public boolean create(Personne object) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Personne(version,nom,prenom,role) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,object.getVersion());
            ps.setString(2, object.getNom());
            ps.setString(3, object.getPrenom());
            ps.setString(4, object.getRole().name());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                object.setId(rs.getLong(1));
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            return false;
        }
    }

    @Override
    public Personne findById(Long id) {
        Personne object = null;
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Personne WHERE id = ?"
        )) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            object = mapResultToPersonne(rs);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return object;
    }

    @Override
    public List<Personne> findAll() {
        List<Personne> all = new ArrayList<>();
        try (Statement s = connection.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT * FROM Personne");
            while(rs.next()) {
                all.add(mapResultToPersonne(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return all;
    }

    @Override
    public boolean update(Personne object) {
        int updated = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE Personne SET version = ?, nom = ?, prenom = ?, role = ? WHERE id = ?")) {
            ps.setInt(1,object.getVersion());
            ps.setString(2, object.getNom());
            ps.setString(3, object.getPrenom());
            ps.setString(4, object.getRole().name());
            ps.setLong(5, object.getId());
            updated = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return updated > 0;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM Personne WHERE id = ?"
        )) {
            ps.setLong(1, id);
            ps.executeQuery();
            connection.commit();
            return true;
        } catch (SQLException e) {
            try { // Extract to rollback
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            return false;
        }
    }

    @Override
    public Personne mapResultToPersonne(ResultSet rs) {
        try {
            return rs == null
                    ? null
                    : new Personne(
                            rs.getLong("id"),
                            rs.getInt("version"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            Role.valueOf(rs.getString("role"))
            );
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            PersonneDao personneDao = new PersonneDao();
            Personne toAdd = new Personne(0L,0,"ADEKALOM","Yanis",Role.ADMIN);
            personneDao.create(toAdd);
            System.out.println("After Add : " + toAdd);
            Personne toUpdate = new Personne(0L,0,"ADEKALOM","Yanis",Role.ADMIN);
            System.out.println("Before Update : " + toUpdate);
            personneDao.create(toUpdate);
            toUpdate.setPrenom("Jérémy");
            toUpdate.setRole(Role.CLIENT);
            personneDao.update(toUpdate);
            Personne findByToUpdateId = personneDao.findById(toUpdate.getId());
            System.out.println("After Update & FindById : " + findByToUpdateId);
            System.out.println("Before Delete : ");
            personneDao.findAll().forEach(System.out::println);
            personneDao.delete(toAdd.getId());
            System.out.println("After Delete : ");
            personneDao.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
