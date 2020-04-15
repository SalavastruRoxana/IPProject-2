package bdConnection;

import dbObjects.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    private List<Book> requestedObjects = new ArrayList<>();
    public List<Book> getRequestedObjects() {
        return requestedObjects;
    }

    public void setRequestedObjects(List<Book> requestedObjects) {
        this.requestedObjects = requestedObjects;
    }

    public void connectToDb(String url, String user, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from carte");
            getObject(resultSet);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getObject(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                requestedObjects.add(new Book(resultSet.getDouble(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getDouble(7), resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}