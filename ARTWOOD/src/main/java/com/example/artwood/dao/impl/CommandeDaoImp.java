package com.example.artwood.dao.impl;

import com.example.artwood.dao.IClientDao;
import com.example.artwood.dao.IProduitDao;
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
import java.util.Queue;

public class CommandeDaoImp implements ICommandeDao {
    private static   final Logger logger = LogManager.getLogger();
    private Connection connection;


    public CommandeDaoImp() {
        connection = ConnectionDB.getInstance();

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


                commandeList.add(generateCommandeFromResult(resultSet));



            }
            logger.info(" successfully get all all Commande " + commandeList);

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
        Commande commande = null;
        logger.info("Attempting to get  Commande By id "+ uuid
        );


        try {
            String query = "select * from commande where commande_uuid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                 commande = generateCommandeFromResult(resultSet);
                logger.info("Getting Commande By id "+commande);



            }

        } catch (SQLException e) {
            logger.error("Error getting Commande ", e);
        }

        return commande;
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

    @Override
    public boolean changeStatutCommande(String commandeUUID,CommandeStatus commandeStatus) {
        String query = "update commande set commande_status = ? , date_update = CURRENT_TIME where commande_uuid = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,commandeStatus.name());
            preparedStatement.setString(2,commandeUUID);
            int i = preparedStatement.executeUpdate();
            if(i ==1){
                logger.info("successfully update statut on commande id " + commandeUUID);
                return  true;
            }
        } catch (SQLException e) {

            logger.error("Error ",e);
        }
        logger.info("Not update statut on commande id " + commandeUUID);
        return false;
    }

    private Commande generateCommandeFromResult(ResultSet resultSet) throws SQLException {
        Commande commande = new Commande(
                resultSet.getTimestamp("date_ajoute"),
                resultSet.getTimestamp("date_update"),
                resultSet.getString("client_uuid"),
                CommandeStatus.valueOf(resultSet.getString("commande_status")),
                resultSet.getFloat("commande_total")

        );
        commande.setUuid(resultSet.getString("commande_uuid"));
        return commande;
    }


}
