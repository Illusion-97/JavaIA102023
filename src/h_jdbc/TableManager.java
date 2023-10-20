package h_jdbc;

import h_jdbc.dao.Dao;
import h_jdbc.models.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class TableManager {

    private static final Map<String,String> SQL_TYPES = Map.of(
            "String","VARCHAR(255)",
            "int", "INT(10)",
            "double", "DECIMAL(10,2)",
            "long", "INT(10) NOT NULL AUTO_INCREMENT",
            "Role", "VARCHAR(15)"
    );
    public static void createTable(Class<? extends BaseEntity> clazz) {
        try (Statement statement = DbConnection.getInstance().createStatement()) {
            String SQL = String.format(
                    "CREATE TABLE IF NOT EXISTS %s\n(%s, PRIMARY KEY ( id ))",
                    clazz.getSimpleName(),
                    getTableReq(clazz)
            );
            statement.execute(SQL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
    public static String getTableReq(Class<?> clazz) {
        return getAllFields(clazz).stream().map(field ->
                field.getName() + " " +
                        Objects.toString(
                                SQL_TYPES.get(field.getType().getSimpleName()),
                                "LONG")
        ).collect(Collectors.joining(", "));
    }
    public static String getCreateReq(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        return String.format("INSERT INTO %s(%s) VALUES(%s)",
                clazz.getSimpleName(),
                fields.stream().map(Field::getName).collect(Collectors.joining(",")),
                fields.stream().map(field -> "?").collect(Collectors.joining(",")));
    }
    public static String getUpdateReq(Class<?> clazz, BaseEntity object) {
        List<Field> fields = getAllFields(clazz);
        return String.format("UPDATE %s SET %s WHERE id = %d",
                clazz.getSimpleName(),
                fields.stream().skip(1).map(field -> String.format("%s = ?",field.getName())).collect(Collectors.joining(", ")),
                object.getId());
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }
        fields.addAll(Arrays.stream(clazz.getDeclaredFields()).toList());
        return fields;
    }

    public static void main(String[] args) {
        try {
            createTable(Personne.class);
            createTable(Marchandise.class);
            createTable(Transaction.class);
            Dao<Personne> personDao = new Dao<>(Personne.class);
            Dao<Marchandise> merchDao = new Dao<>(Marchandise.class);
            Dao<Transaction> transacDao = new Dao<>(Transaction.class);
            Personne p = new Personne(0L,0,"ADEK", "Yanis", Role.ADMIN);
            personDao.create(p);
            p.setNom("ADEKALOM");
            personDao.update(p);
            Random r = new Random();
            Marchandise m = new Marchandise("pc", r.nextDouble(350,2375));
            System.out.println(merchDao.create(m));
            Transaction t = new Transaction(personDao.findById(p.getId()),m, r.nextInt(25));
            System.out.println(transacDao.create(t));
            transacDao.findAll().forEach(tr -> System.out.printf("Pers : %s , Merch : %s , Fact : %.2f €\n",
                    tr.getActeur().getNom(),
                    tr.getObjet().getDesignation(),
                    tr.getQuantite() * tr.getObjet().getCout()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
