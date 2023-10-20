package h_jdbc;

import h_jdbc.dao.IPersonneDAO;
import h_jdbc.dao.PersonneDAOImpl;

public class TableManager {
    public static void main(String[] args) {
        try {
            IPersonneDAO personneDAO = new PersonneDAOImpl();
            personneDAO.createTable();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
