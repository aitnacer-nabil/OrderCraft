package com.example.artwood.dao;

import com.example.artwood.model.Client;

import java.util.List;

public interface IClientDao {
     boolean insertClient(Client client);
     boolean deleteClient(String uuid);
     boolean updateClient(String uuid , Client client);
     List<Client> getAllClients();
     Client getClientByUuid(String uuid);

}
