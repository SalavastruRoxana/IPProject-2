package DAO;

import dbObjects.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public Book getBookByTitle(String title) throws SQLException {
        Book newBook = new Book();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from carte where titlu = '" + title + "' ");
        while (rs.next()) {
            newBook.setId(rs.getInt(1)); //id
            newBook.setTitle(rs.getString(2));//titlu
            newBook.setAuthor(rs.getString(3));//autor
            newBook.setGenre(rs.getString(4));//gen
            newBook.setPageNr(rs.getInt(5));//nr_pag
            newBook.setIsbn(rs.getString(6));//isbn
            newBook.setAvgRating(rs.getByte(7));//raiting
            newBook.setResume(rs.getString(8));//rezumat
        }
        return newBook;
    }

    public Book getBookByISBN(String isbn) throws SQLException {
        Book newBook = new Book();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from carte where isbn = '" + isbn + "' ");
        while (rs.next()) {
            newBook.setId(rs.getDouble(1)); //id
            newBook.setTitle(rs.getString(2));//titlu
            newBook.setAuthor(rs.getString(3));//autor
            newBook.setGenre(rs.getString(4));//gen
            newBook.setPageNr(rs.getInt(5));//nr_pag
            newBook.setIsbn(rs.getString(6));//isbn
            newBook.setAvgRating(rs.getDouble(7));//raiting
            newBook.setResume(rs.getString(8));//rezumat
        }
        return newBook;
    }

    public ArrayList<Book> getBooksByAuthor(String author) throws SQLException {
        ArrayList<Book> list = new ArrayList<Book>();
        Book newBook = new Book();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from carte where autor = '" + author + "' ");
        while (rs.next()) {
            list.add(new Book(rs.getDouble(1),
                    rs.getString(3),
                    rs.getString(2),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getByte(7),
                    rs.getString(8)
            ));
        }
        return list;
    }

    public ArrayList<Book> getBooksByGenre(String genre) throws SQLException {
        ArrayList<Book> list = new ArrayList<Book>();
        Book newBook = new Book();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from carte where gen = '" + genre + "' ");
        while (rs.next()) {
            list.add(new Book(rs.getDouble(1),
                    rs.getString(3),
                    rs.getString(2),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getByte(7),
                    rs.getString(8)
            ));
        }
        return list;
    }

    public void deleteBookByISBN(String isbn) throws SQLException {
        Statement stmt = connection.createStatement();
        int id = -1;
        //sterg din istoric si recenzii mai intai
        ResultSet rs = stmt.executeQuery("select id from carte where isbn = '" + isbn + "' ");
        while (rs.next()) {
            id = rs.getInt(1); //id
        }

        String sql1 = "delete from istoric where id_carte =" + id;
        stmt.executeUpdate(sql1);

        String sql2 = "delete from recenzii where id_carte =" + id;
        stmt.executeUpdate(sql2);

        String sql = "delete from carte where isbn ='" + isbn + "' ";
        stmt.executeUpdate(sql);
    }

    public void addBook(double id, String title, String author, String genre, int pageNr, String isbn, double rating, String resume) throws SQLException {
        try {
            String query = " insert into carte (id, titlu, autor, gen, nr_pag, isbn, rating, rezumat)"
                    + " values (" + id + ", " + title + "," + author + ", " + genre + "," + pageNr + ", " + isbn + "," + rating + ", " + resume + ")";
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception e)
        {
            System.err.println("Exceptie aruncata in BookDAO addBook! ");
            System.err.println(e.getMessage());
        }
    }
}

