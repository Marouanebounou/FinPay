package org.example.services;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.sessions.Session;

import java.util.Optional;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();
    public boolean login(String email , String password) throws Exception{
        Optional<User> user = userDAO.findByEmailAndPassword(email , password);
        if (user.isPresent()){
            Session.start(user.get());
            return true;
        }
        return false;
    }
}
