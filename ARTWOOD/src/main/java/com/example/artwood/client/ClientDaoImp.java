package com.example.artwood.client;

import com.example.artwood.shared.ConnectionDB;
import com.example.artwood.shared.Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

public class ClientDaoImp implements ClientDao {
    private Connection connection;

    public ClientDaoImp() {
        connection = ConnectionDB.getInstance();
    }

    @Override
    public boolean insertClient(Client client) {
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
                Utils.printInfoMessage("Client successfully Created ");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Utils.printWarningMessage("Client Not Createded in DataBase ");
        return false;
    }

    @Override
    public boolean deleteClient(String uuid) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM client WHERE client_uuid = ?"
                )) {
            preparedStatement.setString(1, uuid);
            int result = preparedStatement.executeUpdate();
            if ( result ==1 ){
                Utils.printInfoMessage("Client Deleted");
                return true;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        Utils.printWarningMessage("Client Not Deleted");
        return false;
    }

    @Override
    public boolean updateClient(String uuid, Client client) {
        try {

            String query = "update client set name = ?, email = ?, phone = ?, adress = ? where client_uuid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setString(4, client.getAdress());
            preparedStatement.setString(5, client.getUuid());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {

                preparedStatement.close();
                Utils.printInfoMessage("Client successfully updated");
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Utils.printWarningMessage("Client not updated");
        return false;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clientList = new ArrayList<>();

        try {
            String getAllQuery = "select client_uuid,name,email,adress,phone from client ;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {

                Client client = generateClientFromResultSet(resultSet);
                clientList.add(client);
            }
            Utils.printInfoMessage("Les clients ont été récupérés avec succès depuis la base de données.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;

    }

    @Override
    public Client getClientByUuid(String uuid) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from  client where  client_uuid = ?;"
                )) {
            preparedStatement.setString(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Utils.printInfoMessage("client récupéré avec succès depuis la base de données.");
                    return generateClientFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.printWarningMessage("Ni client trouver");
        return null;
    }
    private Client generateClientFromResultSet(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString("client_uuid");
        String name = resultSet.getString("name");
        String  phone= resultSet.getString("phone");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        Client client = new Client(name,email,phone,address);
        client.setUuid(uuid);

        return client;
    }

}
