package com.example.artwood.produit;

import com.example.artwood.client.Client;
import com.example.artwood.shared.ConnectionDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImp implements ProduitDao {
    private static   final Logger logger = LogManager.getLogger();
    private Connection connection;

    public ProduitDaoImp() {
        connection = ConnectionDB.getInstance();
        logger.info("Produit Connection established");

    }

    @Override
    public boolean insertProduit(Produit produit) {
        logger.info("Attempting to insert client");
        return excuteProduitAction("INSERT" , produit);
    }



    @Override
    public boolean deleteProduit(String uuid) {
        return false;
    }

    @Override
    public boolean updateProduit(String uuid, Produit produit) {
        logger.info("Attempting to Update client");
        return excuteProduitAction("UPDATE" , produit);
    }

    @Override
    public List<Produit> getAllProduits() {
//        logger.info("Attempting to get all Produit");
//        List<Produit> produits = new ArrayList<>();
//
//        try {
//            String getAllQuery = "SELECT  (name, description, prix, produit_uuid) from produit;";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(getAllQuery);
//            while (resultSet.next()) {
//
//                Produit produit = generateProduitFromResult(resultSet);
//                clientList.add(client);
//            }
//            logger.info(" successfully get all client");
//
//        } catch (SQLException e) {
//            logger.error("Error getting client", e);
//        }
//        logger.warn("no client in database");
        return new ArrayList<>();
    }

    private Produit generateProduitFromResult(ResultSet resultSet) {
//        String uuid = resultSet.getString("");
//        String name = resultSet.getString("name");
//        String  phone= resultSet.getString("phone");
//        String email = resultSet.getString("email");
//        String address = resultSet.getString("address");
//        Client client = new Client(name,email,phone,address);
//        client.setUuid(uuid);
        return null;
    }

    @Override
    public Produit getProduitById(String uuid) {
        return null;
    }

    private boolean excuteProduitAction(String action,Produit produit) {
        try {
            String query =  action +"INSERT INTO  `produit` (name, description, prix, produit_uuid,qte_stock) values (?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,produit.getName());
            statement.setString(2,produit.getDescription());
            statement.setFloat(3,produit.getPrix());
            statement.setString(4,produit.getUuid());
            statement.setInt(5,produit.getQte_stock());
            int i = statement.executeUpdate();
            if(i == 1){
                logger.info("Produit successfully " + action);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error "+action.toUpperCase()+" produit", e);
        }
        logger.warn("Produit  Not"+action.toUpperCase()+"  in DataBase ");
        return false;
    }
}
