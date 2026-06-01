package org.example.services;

import org.example.model.Client;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.config.DatabaseConfig.getConnection;
import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Test
    void registerClientTest() throws SQLException {
        ClientService clientService = new ClientService();
        List<Client> expectedCli = new ArrayList<>();

        Client client = new Client("rida", "rida@gmail.com", 20);
        boolean cli = clientService.registerClient(client);
        if (cli){
        expectedCli.add(client);
        assertEquals(expectedCli , "not success");
        }

    }
}