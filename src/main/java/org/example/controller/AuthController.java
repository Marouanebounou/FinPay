package org.example.controller;
import org.example.services.AuthService;
import org.example.sessions.Session;
import java.util.Scanner;

public class AuthController {
    private final AuthService authService = new AuthService();
    private final Scanner scanner = new Scanner(System.in);

    public void login() throws Exception{
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (authService.login(email , password)){
            System.out.println("Connexion réussie");
        }else {
            System.out.println("Email ou mot de passe incorrect!");
        }
    }

    public void logout(){
        Session.logout();
        System.out.println("Déconnexion réussie");
    }
}
