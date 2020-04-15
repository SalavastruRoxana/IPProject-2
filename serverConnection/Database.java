package serverConnection;

import java.sql.*;

public class Database {
    private static Database databaseInstance = null;
    public static Connection connection;

    private Database(String url, String user, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static Database getDatabaseInstance(String url, String user, String password) {
        if (databaseInstance == null) {
            databaseInstance = new Database(url, user, password);
        }
        return databaseInstance;
    }

    public static void closeDatabase() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
