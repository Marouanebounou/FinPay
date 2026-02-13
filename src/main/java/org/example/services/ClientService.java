package org.example.services;

import org.example.dao.ClientDao;
import org.example.model.Client;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientDao clientDao = new ClientDao();

    public void registerClient(Client client) throws SQLException {
        if (!client.getEmail().contains("@")) {
            throw new IllegalArgumentException("Format d'email invalide.");
        }
        if (client.getAge() < 18) {
            throw new IllegalArgumentException("Le client doit être majeur.");
        }
        clientDao.addClient(client);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientDao.displayClients();
    }

    public boolean updateClientInfo(Client client) throws SQLException {
        clientDao.updateClient(client);
        return true;
    }

    public boolean deleteClient(int id) throws SQLException {
        // Need to improve but not now
        return clientDao.removeClient(id);
    }
    public Client getClientById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID doit être supérieur à 0.");
        }

        Client client = clientDao.findClientById(id);

        if (client == null) {
            System.out.println("le client n'existe pas");
            return null;
        }

        return client;
    }
}