package com.example.artwood.client;

import java.util.List;

public interface ClientDao {
     boolean insertClient(Client client);
     boolean deleteClient(String uuid);
     boolean updateClient(String uuid , Client client);
     List<Client> getAllClient();
     Client getClientByUuid(String uuid);

}
