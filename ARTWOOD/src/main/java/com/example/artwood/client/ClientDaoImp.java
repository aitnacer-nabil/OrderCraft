package com.example.artwood.client;

import com.example.artwood.shared.ConnectionDB;
import com.example.artwood.shared.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

public class ClientDaoImp implements ClientDao {
    private static   final Logger logger = LogManager.getLogger();
    private Connection connection;

    public ClientDaoImp() {
        connection = ConnectionDB.getInstance();
        logger.info("Connection established");
    }

    @Override
    public boolean insertClient(Client client) {
        logger.info("Attempting to insert client");
        try {
            String query = "INSERT INTO  client (name, email, phone, adress, client_uuid) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,client.getName());
            statement.setString(2,client.getEmail());
            statement.setString(3,client.getPhone());
            statement.setString(4,client.getAdress());
            statement.setString(5,client.getUuid());
            int i = statement.executeUpdate();
            if(i == 1){
                logger.info("Client successfully Created ");
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error inserting client", e);
        }
        logger.warn("Client Not Created in DataBase ");
        return false;
    }

    @Override
    public boolean deleteClient(String uuid) {
        logger.info("Attempting to delete client");
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM client WHERE client_uuid = ?"
                )) {
            preparedStatement.setString(1, uuid);
            int result = preparedStatement.executeUpdate();
            if ( result ==1 ){
                logger.info("Client Deleted");
                return true;
            }
        } catch (SQLException e) {
            //TODO java.sql.SQLIntegrityConstraintViolationException
            logger.error("Error deleting client", e);
        }
       logger.warn("Client Not Deleted");
        return false;
    }

    @Override
    public boolean updateClient(String uuid, Client client) {
        logger.info("Attempting to update client");
        try {

            String query = "update client set name = ?, email = ?, phone = ?, adress = ? where client_uuid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setString(4, client.getAdress());
            preparedStatement.setString(5, uuid);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {

                preparedStatement.close();
                logger.info("Client successfully updated");
                return true;
            }


        } catch (SQLException e) {
            logger.error("Error updating client", e);
        }
        logger.warn("Client not updated in database");
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        logger.info("Attempting to get all client");
        List<Client> clientList = new ArrayList<>();

        try {
            String getAllQuery = "select client_uuid,name,email,adress,phone from client ;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {

                Client client = generateClientFromResultSet(resultSet);
                clientList.add(client);
            }
            logger.info(" successfully get all client");

        } catch (SQLException e) {
            logger.error("Error getting client", e);
        }

        return clientList;

    }

    @Override
    public Client getClientByUuid(String uuid) {
        logger.info("Attempting to get client");
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from  client where  client_uuid = ?;"
                )) {
            preparedStatement.setString(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("Client successfully retrieved");
                    return generateClientFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting client", e);
        }
        logger.warn("Client not found in database");
        return null;
    }
    private Client generateClientFromResultSet(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString("client_uuid");
        String name = resultSet.getString("name");
        String  phone= resultSet.getString("phone");
        String email = resultSet.getString("email");
        String address = resultSet.getString("adress");
        Client client = new Client(name,email,phone,address);
        client.setUuid(uuid);

        return client;
    }

}
