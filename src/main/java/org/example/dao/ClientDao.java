package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Client;
import org.example.model.Prestataire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    public void addClient(Client client){

        String query = "INSERT INTO client(clientName,age,email,passwordClient) VALUES (?,?,?,?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, client.getName());
            ps.setInt(2,client.getAge());
            ps.setString(3,client.getEmail());
            ps.setString(4,client.getPassword());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeClient(int clientId) throws SQLException{

        String query = "DELETE FROM client WHERE clientId =?";

        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,clientId);
            return ps.executeUpdate() > 0;
        }

    }

    public Client updateClient(Client client) throws SQLException{
        String query = "UPDATE client SET clientName = ? , email = ? , passwordClient = ? , age = ? WHERE idClient = ?";

        try (Connection conn =  DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, client.getName());
            ps.setString(2,client.getEmail());
            ps.setString(3, client.getPassword());
            ps.setInt(4,client.getAge());
            ps.setInt(5,client.getClientId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Updating client failed, no rows affected.");
            }

            return client;
        }
    }

    public Client findClientById(int clientId)throws SQLException{

            String query = "SELECT * FROM client WHERE clientId = ?";

        try (Connection conn =  DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,clientId);

            ResultSet rs = ps.executeQuery();

            Client newClient = new Client(rs.getString("clientName"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("passwordClient"));
            return newClient;
        }
    }

    public List<Client> displayClients() throws SQLException{

        List<Client> clientList= new ArrayList<>();

        String query = "SELECT * FROM client";

        try (Connection conn =  DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client(
                        rs.getString("clientName"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("passwordClient")
                );

                client.setClientId(rs.getInt("idClient"));

                clientList.add(client);
            }
            return clientList;

        }
    }
}
