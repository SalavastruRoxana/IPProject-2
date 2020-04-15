/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Claudiu
 */

import dbObjects.Review;
import serverConnection.Database;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {
    private Connection connection;
    
    public ReviewDAO() {}
    
    public ReviewDAO(Database databaseInstance) {
        this.connection = databaseInstance.connection;

    }
    
    /*
     
     * void addReview();
     * 
    */
    
    public ArrayList<Review> getReviewsByBookID(int bookID) {
        String sql = "SELECT * FROM recenzii WHERE ID_CARTE = ?";
        ArrayList<Review> reviewsList = new ArrayList<Review>();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bookID);     
            ResultSet rs = stmt.executeQuery();
            
            while (true == rs.next()) {
                reviewsList.add(new Review(rs.getDouble(1), rs.getDouble(2), rs.getByte(3), rs.getString(4)));
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return reviewsList;
    }
    
    public Review getLatestReviewByBookID(int bookID) {
        String sql = "SELECT * FROM recenzii WHERE ID_CARTE = ? ORDER BY ID DESC";
        Review review = null;
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bookID);     
            ResultSet rs = stmt.executeQuery();
            
            if (true == rs.next()) {
                review = new Review(rs.getInt(1), rs.getInt(2), rs.getByte(3) , rs.getString(4));
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return review;
    }

    public void addReview(Review reviewToAdd) {
        String sqlFindMaxID = "SELECT max(id) FROM recenzii";
        int maxID = -1;
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlFindMaxID);

            if (true == rs.next()) {
                maxID = rs.getInt(1);
                System.out.println("ID MAXIM : " + maxID);
            }
            maxID ++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "INSERT INTO recenzii VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, maxID);
            prepStmt.setDouble(2, reviewToAdd.getId());
            prepStmt.setDouble(3, reviewToAdd.getBookId());
            prepStmt.setByte(4, reviewToAdd.getRating());
            prepStmt.setString(5, reviewToAdd.getReviewText());
            
            prepStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public byte getAverageRatingByBookId(int bookID) {
        String sql = "SELECT avg(rating) FROM recenzii where id_carte = ?";
        byte ratingAverage = 0;
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bookID);     
            ResultSet rs = stmt.executeQuery();
            
            if (true == rs.next()) {
                ratingAverage = rs.getByte(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ratingAverage;
    }
    
    public void deleteReviewByID(Review review) {
        String sql = "DELETE FROM recenzii WHERE id = ?";
        
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setDouble(1, review.getId());
            
            prepStmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
