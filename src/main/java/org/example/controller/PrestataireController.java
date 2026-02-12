package org.example.controller;

import org.example.model.Prestataire;
import org.example.services.PrestataireService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PrestataireController {
    private final PrestataireService prestataireService = new PrestataireService();
    private final Scanner scanner = new Scanner(System.in);

    public void createPrestataire() throws Exception{
        Prestataire prestataire = new Prestataire();
        System.out.print("Nom : ");
        prestataire.setName(scanner.nextLine());
        System.out.print("Type : ");
        prestataire.setType(scanner.nextLine());
        prestataire.setCreatedAt(new Date());
        System.out.println("Prestataire créé avec success");
        prestataireService.createPrestataire(prestataire);
    }

    public void updatePrestataire()throws Exception{
        System.out.println("Entrer prestatiare id : ");
        int id = Integer.parseInt(scanner.nextLine());
        Prestataire prestataire = prestataireService.findPrestatairById(id);
        if (prestataire != null){
            System.out.println("Entrer nom de prestataire : ");
            String nom = scanner.nextLine();
            System.out.println("Entrer type de prestataire : ");
            String type = scanner.nextLine();
            if (!nom.isEmpty() && !type.isEmpty()){
                prestataire.setType(type);
                prestataire.setName(nom);
                prestataireService.updatePrestataire(prestataire);
                System.out.println("Prestataire a ete modifier");
            }else if(!nom.isEmpty()){
                prestataire.setName(nom);
                prestataireService.updatePrestataire(prestataire);
                System.out.println("Prestataire a ete modifier");
            }else if(!type.isEmpty()){
                prestataire.setType(type);
                prestataireService.updatePrestataire(prestataire);
                System.out.println("Prestataire a ete modifier");
            }
        }else {
            System.out.println("ce prestataire n'existe pas");
        }
    }

    public void getPrestataireById() throws Exception{
        System.out.print("Entrer id de prestataire : ");
        int id = Integer.parseInt(scanner.nextLine());
        Prestataire prestataire = prestataireService.findPrestatairById(id);
        System.out.println(prestataire.toString());
    }

    public void getAll() throws Exception {
        List<Prestataire> prestataires = prestataireService.findAll();
        prestataires.stream().forEach(Prestataire::toString);
    }

    public void deletePrestataire()throws Exception {
        System.out.print("Entrer id de prestatair: ");
        int id = Integer.parseInt(scanner.nextLine());
        prestataireService.deleteById(id);
        System.out.println("Prestataire a ete supprimé");
    }

}

