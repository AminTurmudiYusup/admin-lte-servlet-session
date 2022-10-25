package com.servlet.adminlte.repository;

import com.servlet.adminlte.connection.DbConnection;
import com.servlet.adminlte.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private Connection connection;

    public User findByUsername(String userName){
        connection= DbConnection.getConnection();
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select user_id, user_name, user_password from user where user_name= ?");
            statement.setString(1, userName);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=new User();
                user.setUserId(resultSet.getString(1));
                user.setUserName(resultSet.getString(2));
                user.setUserPassword(resultSet.getString(3));
            }
            connection.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return user;
    }
}
