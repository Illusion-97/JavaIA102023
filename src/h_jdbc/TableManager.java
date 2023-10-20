package h_jdbc;

import h_jdbc.dao.IPersonneDAO;
import h_jdbc.dao.PersonneDAOImpl;
import h_jdbc.models.Personne;
import h_jdbc.models.Role;

public class TableManager {
    public static void main(String[] args) {
        try {
            IPersonneDAO personneDAO = new PersonneDAOImpl();
            personneDAO.createTable();
            Personne yanis = new Personne(0L, 0, "ADEKALOM", "Yanis", Role.ADMIN);
            personneDAO.create(yanis);
            System.out.println(yanis);
            System.out.println("personneDAO.findById(4) = " + personneDAO.findById(4));
            System.out.println("personneDAO.findById(25) = " + personneDAO.findById(25));
            System.out.println("Find All : ");
            personneDAO.findAll().forEach(System.out::println);
            yanis.setRole(Role.CLIENT);
            personneDAO.update(yanis);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
