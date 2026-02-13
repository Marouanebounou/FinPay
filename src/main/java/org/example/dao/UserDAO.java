package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Role;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {
    public Optional<User> findByEmailAndPassword(String email , String password) throws SQLException{
        String SQL = "SELECT * FROM utilisateur where email = ? AND password = ? ";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL);
        ){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return Optional.of(user);
            }
            return Optional.empty();
        }
    }
}
