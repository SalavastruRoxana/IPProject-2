/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import serverConnection.Database;
import dbObjects.User;
import java.sql.*;

public class UserDAO {
    private Connection connection;
    
    public UserDAO() {}
    
    public UserDAO(Database databaseInstance) {
        this.connection = databaseInstance.connection;
    }
    
    public User getUserByName(String nickname) throws SQLException{
        String sql = "SELECT * FROM utilizator WHERE NUME = ?";
        User user = null;
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nickname);     
            ResultSet rs = stmt.executeQuery();
            
            if (true == rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return user;
    }
    
    public void addUser(User userToAdd) {
        String sqlFindMaxID = "SELECT max(id) FROM utilizator";
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
        
        String sql = "INSERT INTO utilizator VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, maxID);
            prepStmt.setString(2, userToAdd.getNume());
            prepStmt.setString(3, userToAdd.getPrenume());
            prepStmt.setString(4, userToAdd.getNickname());
            prepStmt.setString(5, userToAdd.getPassword());
            prepStmt.setString(6, userToAdd.getEmail());
            
            prepStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserNickname(User User) {
    /*
        To do..
    */
    }
    
    public void updateUserPassword(User user, String newPassword) {
        String sql = "UPDATE utilizator SET parola = ? WHERE nickname = ?";
        
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, newPassword);
            prepStmt.setString(2, user.getNickname());
            
            prepStmt.executeQuery();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserEmail(User user) {
    /*
        To do..
    */
    }

    public void deleteUserByFirstName(User user) {
        String sql = "DELETE FROM utilizator WHERE nume = ?";
        
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, user.getNume());
            
            prepStmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteUserByLastName(User user) {
        String sql = "DELETE FROM utilizator WHERE prenume = ?";
        
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, user.getPrenume());
            
            prepStmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteUserByNickname(User user) {
        String sql = "DELETE FROM utilizator WHERE nickname = ?";
        
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, user.getNickname());
            
            prepStmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteUserByEmail(User user) {
        String sql = "DELETE FROM utilizator WHERE email = ?";
        
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, user.getEmail());
            
            prepStmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
