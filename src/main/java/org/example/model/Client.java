package org.example.model;

public class Client extends Person {
    private String email;
    private String password;
    private int clientId;
    public Client(String name,int age,String email,String password){
        super(name,age);
        this.email = email;
        this.password = password;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
        return "Client{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
