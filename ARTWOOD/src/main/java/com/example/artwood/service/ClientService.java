package com.example.artwood.service;

import com.example.artwood.dao.IClientDao;
import com.example.artwood.dao.impl.ClientDaoImp;
import com.example.artwood.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientService {
    private IClientDao IClientDao;
    private static final Logger logger = LogManager.getLogger();

    public ClientService() {
        this.IClientDao = new ClientDaoImp();
    }

    public boolean addClient(Client client) {
        logger.info("Adding new client");
        return IClientDao.insertClient(client);
    }

    public boolean updateClient(String uuid,Client client) {
        logger.info("Updating client");
        return IClientDao.updateClient(uuid,client);
    }

    public boolean deleteClient(String uuid) {
        logger.info("Deleting client");
        return IClientDao.deleteClient(uuid);
    }

    public Client getClient(String uuid) {
        logger.info("Fetching client");
        return IClientDao.getClientByUuid(uuid);
    }

    public List<Client> getAllClients() {
        logger.info("Fetching all clients");
        return IClientDao.getAllClients();
    }
}
