package com.example.artwood.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientService {
    private ClientDao clientDao;
    private static final Logger logger = LogManager.getLogger();

    public ClientService() {
        this.clientDao = new ClientDaoImp();
    }

    public boolean addClient(Client client) {
        logger.info("Adding new client");
        return clientDao.insertClient(client);
    }

    public boolean updateClient(String uuid,Client client) {
        logger.info("Updating client");
        return clientDao.updateClient(uuid,client);
    }

    public boolean deleteClient(String uuid) {
        logger.info("Deleting client");
        return clientDao.deleteClient(uuid);
    }

    public Client getClient(String uuid) {
        logger.info("Fetching client");
        return clientDao.getClientByUuid(uuid);
    }

    public List<Client> getAllClients() {
        logger.info("Fetching all clients");
        return clientDao.getAllClients();
    }
}
