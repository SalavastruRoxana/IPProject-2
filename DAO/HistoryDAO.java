package DAO;

import dbObjects.Book;
import dbObjects.History;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDAO {
    private Connection connection;

    public HistoryDAO(Connection connection) {
        this.connection = connection;
    }


    ArrayList<History> getHistoryByUserId(double userId) throws SQLException {
        ArrayList<History> list = new ArrayList<History>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from istoric where id_utilizator = " + userId);
        while (rs.next()) {
            int idCarte = rs.getInt(2);
            ResultSet rs2 = stmt.executeQuery("select * from carte where id = " + idCarte);
            Book newBook = new Book();
            while (rs2.next()) {
                newBook.setId(rs.getInt(1)); //id
                newBook.setTitle(rs.getString(2));//titlu
                newBook.setAuthor(rs.getString(3));//autor
                newBook.setGenre(rs.getString(4));//gen
                newBook.setPageNr(rs.getInt(5));//nr_pag
                newBook.setIsbn(rs.getString(6));//isbn
                newBook.setAvgRating(rs.getByte(7));//raiting
                newBook.setResume(rs.getString(8));//rezumat
            }
            list.add(new History(rs.getInt(1), newBook,rs.getString(3)));
        }
        return list;
    }

    public void deleteHistoryByUserId(double userId) throws SQLException { // toate inregistrarile care au acel user id
        Statement stmt = connection.createStatement();
        String sql1 = "delete from istoric where id_utilizator =" + userId;
        stmt.executeUpdate(sql1);
    }

    public void addHistory(History history) throws SQLException {
        String query = " insert into istoric (id_utilizator, id_carte, data_vizitare)"
                + " values (" + history.getId() + ", " + history.getVisitedBook().getId() + "," + history.getVisitationDate()  +")";
        Statement stmt = connection.createStatement();
        stmt.execute(query);
    }
}
