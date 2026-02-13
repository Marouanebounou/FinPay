package org.example.sessions;

import org.example.model.Role;
import org.example.model.User;

public class Session {
    private static User currentUser;
    private Session(){};

    public static void start(User user){
        currentUser = user;
    }
    public static User getCurrentUser(){
        if (currentUser == null){
            throw new IllegalStateException("No active session");
        }
        return currentUser;
    }

    public static Role getRole(){
        return  currentUser.getRole();
    }

    public static boolean isAuth(){
        return currentUser != null;
    }

    public static void logout(){
        currentUser = null;
    }

}
