package org.example;

public class Client extends Person{
    private String email;
    private int clientId;
    private static int counter = 1;
    private String password;
    public Client(String name,int age,int id,String email,String password){
        super(name,age);
        this.clientId = counter++;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
