package h_jdbc;
import java.sql.*;
public class DbConnection {

    private static Connection connection = null;
    private static final String bddUrl = "jdbc:mariadb://localhost:3307/javaia";
    private static final String dbUser = "root";
    private static final String dbPassword = "";

    private DbConnection() {

    }

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        if(connection == null) {
            // https://downloads.mariadb.org/connector-java/
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(bddUrl,dbUser,dbPassword);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
