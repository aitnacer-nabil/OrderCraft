package com.example.artwood.dao.impl;

import com.example.artwood.model.Commande;
import com.example.artwood.dao.ICommandeDao;
import com.example.artwood.shared.ConnectionDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ICommandeDaoImp implements ICommandeDao {
    private static   final Logger logger = LogManager.getLogger();
    private Connection connection;

    public ICommandeDaoImp() {
        connection = ConnectionDB.getInstance();
        logger.info("CommandeDaoImp : Connection established");
    }

    @Override
    public List<Commande> getAllCommandes() {
        logger.info("Attempting to get all Commande");
        List<Commande> commandeList = new ArrayList<>();

        try {
            String getAllQuery = "SELECT\n" +
                    "    c.client_id,\n" +
                    "    c.client_uuid,\n" +
                    "    c.name AS client_name,\n" +
                    "    cmd.commande_id,\n" +
                    "    cmd.date_ajoute,\n" +
                    "    cmd.commande_uuid,\n" +
                    "    cmd.date_update,\n" +
                    "    cmd.commande_status,\n" +
                    "    p.produit_id,\n" +
                    "    p.produit_uuid,\n" +
                    "    p.name AS produit_name,\n" +
                    "    p.description AS produit_description,\n" +
                    "    p.prix\n" +
                    "\n" +
                    "FROM\n" +
                    "    commande cmd\n" +
                    "        JOIN\n" +
                    "    client c ON cmd.client_id = c.client_id\n" +
                    "        JOIN\n" +
                    "    commande_produit cp ON cmd.commande_id = cp.commande_id\n" +
                    "        JOIN\n" +
                    "    produit p ON cp.produit_id = p.produit_id\n";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {


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
