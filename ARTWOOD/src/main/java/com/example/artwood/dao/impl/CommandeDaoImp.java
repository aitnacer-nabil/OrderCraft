package com.example.artwood.dao.impl;

import com.example.artwood.dao.IClientDao;
import com.example.artwood.model.Client;
import com.example.artwood.model.Commande;
import com.example.artwood.dao.ICommandeDao;
import com.example.artwood.model.CommandeStatus;
import com.example.artwood.shared.ConnectionDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeDaoImp implements ICommandeDao {
    private static   final Logger logger = LogManager.getLogger();
    private Connection connection;
    private IClientDao clientDao;

    public CommandeDaoImp() {
        connection = ConnectionDB.getInstance();
        clientDao = new ClientDaoImp();
        logger.info("CommandeDaoImp : Connection established");
    }

    @Override
    public List<Commande> getAllCommandes() {
        logger.info("Attempting to get all Commande");
        List<Commande> commandeList = new ArrayList<>();

        try {
            String query = "select * from commande;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Commande commande = new Commande(
                        resultSet.getTimestamp("date_ajoute"),
                        resultSet.getTimestamp("date_update"),
                        clientDao.getClientByUuid(resultSet.getString("client_uuid")),
                        CommandeStatus.valueOf(resultSet.getString("commande_status")),

                )

            }
            logger.info(" successfully get all all Commande");

        } catch (SQLException e) {
            logger.error("Error getting Commande ", e);
        }
        logger.warn("no client in database");
        return commandeList;
    }

    @Override
    public List<Commande> getAllCommandesByClient() {
        return null;
    }

    @Override
    public Commande getCommande(String uuid) {
        return null;
    }

    @Override
    public boolean insertCommande(Commande commande) {
        String query = "insert into commande ( client_uuid, commande_status, commande_uuid, commande_total) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, commande.getClient().getUuid());
            preparedStatement.setString(2, commande.getCommandeStatus().name());
            preparedStatement.setString(3, commande.getUuid());
            preparedStatement.setFloat(4, commande.getTotal_amount());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                logger.info("insert commande in commande table "+ commande);
                return true;
            }
        } catch (SQLException e) {
           logger.error("Error in inserting commande");
           logger.error(e.getMessage());
        }
        logger.error("Error in inserting commande"+ commande);
        return false;
    }

    @Override
    public boolean updateCommande(Commande commande) {
        return false;
    }

    @Override
    public boolean deleteCommande(String uuid) {
        return false;
    }

    @Override
    public boolean insertInTableCommandeProduit(String commandeUUID, String produitUUID,int amount) {
            String query = "insert into commande_produit (commande_uuid, produit_uuid,amount) values (?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,commandeUUID);
                preparedStatement.setString(2,produitUUID);
                preparedStatement.setInt(3,amount);
                int i = preparedStatement.executeUpdate();
                if(i == 1){
                    logger.info("insert into table commande_produit "+ commandeUUID + " , "+ produitUUID);
                    return true;
                } else {

                }logger.info("not insert into table commande_produit "+ commandeUUID + " , "+ produitUUID);

            } catch (SQLException e) {
                logger.info("not insert into table commande_produit "+ commandeUUID + " , "+ produitUUID);
                logger.error("Error insert into table commande_produit  ", e);

            }


        return false;
    }

    private Commande generateCommandeFromResultSet(ResultSet resultSet) throws SQLException {
//        int i= 0;
//        do {
//            String client_uuid = resultSet.getString("client_uuid");
//            String client_name = resultSet.getString("client_name");
//            String client_phone = resultSet.getString("client_phone");
//            String client_adress= resultSet.getString("client_adress");
//            String client_email= resultSet.getString("client_email");
//        }while (i != 1)
//
//        String  commande_uuid= resultSet.getString("commande_uuid");
//        String total_amount = resultSet.getString("total_amount");
//        Timestamp date_ajoute = resultSet.getTimestamp("date_ajoute");
//        Timestamp date_update = resultSet.getTimestamp("date_update");
//        CommandeStatus commandeStatus = CommandeStatus.valueOf(resultSet.getString("commande_status"));
//        String produit_uuid = resultSet.getString("produit_uuid");
//        String produit_name = resultSet.getString("produit_name");
//        String produit_description = resultSet.getString("produit_description");
//        float prix  = resultSet.getFloat("prix");
//        int produit_qte_order = resultSet.getInt("qte_order");
//        int produit_qte_stock= resultSet.getInt("qte_stock");
//        Client client = new Client(client_name,client_email,client_phone,client_adress);
//        client.setUuid(client_uuid);
//        Produit produit = new Produit(produit_name,produit_description,prix,produit_qte_stock,produit_qte_order);
//        produit.setUuid(produit_uuid);




        return null;
    }


}
