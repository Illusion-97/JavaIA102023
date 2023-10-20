package h_jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton : Principe permettant de n'avoir qu'une seule instance de la classe à disposition pour tout le programme
public class DbConnection {
    private static Connection connection = null;
    private static final String URL = "jdbc:mariadb://localhost:3307/javaia";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private DbConnection() {
    }

    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            Class.forName("org.mariadb.jdbc.Driver"); // charge le driver dans le programme
            connection = DriverManager.getConnection(URL,USER,PASSWORD); // Utilise le driver pour obtenir une connexion à la BDD
        }
        return connection;
    }
}
