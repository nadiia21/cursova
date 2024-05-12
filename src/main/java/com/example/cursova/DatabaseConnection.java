package com.example.cursova;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseConnection extends Configs {
     Connection connection;
     public Connection getConnection() throws  ClassNotFoundException, SQLException {
         String ConnectionString = "jdbc:mysql://" + Host + ":" + Port + "/" + Name + "?serverTimezone=UTC";
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(ConnectionString, User, Password);
         return connection;
     }

 public  void  RegisterUser(User NewUser) throws SQLException, ClassNotFoundException {
         String insert = "INSERT INTO " + Constants.TABLE_USER + "(" + Constants.USERNAME_USER + "," +
                 Constants.FIRSTNAME_USER + "," + Constants.SURNAME_USER + "," + Constants.PASSWORD_USER
                 + ")" + "VALUES(?,?,?,?)";
    try {
        PreparedStatement statement = getConnection().prepareStatement(insert);
        statement.setString(1, NewUser.getUserName());
        statement.setString(2, NewUser.getFirstName());
        statement.setString(3, NewUser.getSurname());
        statement.setString(4, NewUser.getPassword());

        statement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
 }
 public ResultSet GetUser(User NewUser){
         ResultSet resultSet = null;
     String select = "SELECT * FROM " + Constants.TABLE_USER + " WHERE " +
             Constants.USERNAME_USER + "=? AND " + Constants.PASSWORD_USER + "=?";
     try {
         PreparedStatement statement = getConnection().prepareStatement(select);
         statement.setString(1, NewUser.getUserName());
         statement.setString(2, NewUser.getPassword());

         resultSet = statement.executeQuery();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     } catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
     }

     return resultSet;
 }
}
