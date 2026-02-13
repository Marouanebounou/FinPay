package org.example.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Prestataire {
    private String name;
    private String type;
    private int id;
    private Date createdAt;
    public Prestataire(){
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "Prestataire : " +
                "id= " + id +
                ", name= '" + name + '\'' +
                ", type= '" + type + '\'' +
                ", createdAt= " + createdAt ;
    }

}
