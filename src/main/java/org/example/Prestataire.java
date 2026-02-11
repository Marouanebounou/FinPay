package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Prestataire {
    private String name;
    private String type;
    private int id;
    private String email;
    private static int counter = 1;
    private String password;
    public Prestataire(String name , String type ,String email,String password){
        this.id = counter++;
        this.name = name;
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Prestataire{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    Scanner sc = new Scanner(System.in);

    public Prestataire searchPrestataire(List<Prestataire> prestatairesList){
        System.out.println("entrer l'id de prestatire");
        int num = sc.nextInt();
        sc.nextLine();

        for(Prestataire prest : prestatairesList){
            if (prest.getId() == num){
                return prest;
            }
        }
        System.out.println("la facture n'existe pas");
        return null;
    }

    public Client searchClient(List<Client> clientsList){
        System.out.println("entrer l'id de prestatire");
        int num = sc.nextInt();
        sc.nextLine();

        for(Client cl : clientsList){
            if (cl.getClientId() == num){
                return cl;
            }
        }
        System.out.println("la facture n'existe pas");
        return null;
    }

    public Facture addInvoice(List<Prestataire> prestatairesList,List<Client> clientsList){
        System.out.println("entrer le montant de facture ");
        double amount = sc.nextDouble();
        sc.nextLine();

        LocalDateTime now = LocalDateTime.now();

        Prestataire foundPrestataire = searchPrestataire(prestatairesList);
        if (foundPrestataire == null){
            System.out.println("ce prestataire n'existe pas");
            return null;
        }

        Client foundClient = searchClient(clientsList);
        if (foundClient == null){
            System.out.println("ce client n'existe pas");
            return null;
        }

        return new Facture(foundPrestataire,amount,now,"unpayed",foundClient);
    }

    public Facture searchFacture(List<Facture> factureList){
        System.out.println("entrer l'id de facture");
        int num = sc.nextInt();
        sc.nextLine();

        for(Facture fact : factureList){
            if (fact.getFactureId() == num){
                return fact;
            }
        }
        System.out.println("la facture n'existe pas");
        return null;
    }

    public void changeFacture(List<Facture> factureList){
        if (factureList.isEmpty()){
            System.out.println("il y'a aucune facture");
            return;
        }
        Facture foundFacture = searchFacture(factureList);

        if(foundFacture == null ){
            System.out.println("cette facture n'existe pas");
            return;
        }

        if(foundFacture.getStatus().equals("unpayed")){
            System.out.println("entrer le nouveau montant");
            double balance = sc.nextDouble();
            sc.nextLine();
            foundFacture.setBalence(balance);
        }else {
            System.out.println("tu ne peux pas changer une facture deja paye");
        }
    }
    public void removeFacture(List<Facture> factureList){

    }
}
