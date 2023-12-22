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

        logger.info("Attempting to insert Produit");
        try {
            String query = "INSERT INTO  `produit` (name, description, prix, produit_uuid,qte_stock) values (?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,produit.getName());
            statement.setString(2,produit.getDescription());
            statement.setFloat(3,produit.getPrix());
            statement.setString(4,produit.getUuid());
            statement.setInt(5,produit.getQte_stock());
            int i = statement.executeUpdate();
            if(i == 1){
                logger.info("Produit successfully Created ");
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error inserting Produit", e);
        }
        logger.warn("Produit Not Created in DataBase ");
        return false;
    }



    @Override
    public boolean deleteProduit(String uuid) {
        logger.info("Attempting to delete Produit");
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM produit WHERE produit_uuid = ?"
                )) {
            preparedStatement.setString(1, uuid);
            int result = preparedStatement.executeUpdate();
            if ( result ==1 ){
                logger.info("produit Deleted");
                return true;
            }
        } catch (SQLException e) {
            //TODO java.sql.SQLIntegrityConstraintViolationException
            logger.error("Error deleting produit", e);
        }
        logger.warn("produit Not Deleted");
        return false;
    }

    @Override
    public boolean updateProduit(String uuid, Produit produit) {
        logger.info("Attempting to update produit");
        try {

            String query = "update produit set name = ?, description = ?, prix = ?, qte_stock = ? where produit_uuid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, produit.getName());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setFloat(3, produit.getPrix());
            preparedStatement.setInt(4, produit.getQte_stock());
            preparedStatement.setString(5, uuid);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {

                preparedStatement.close();
                logger.info("Produit successfully updated");
                return true;
            }


        } catch (SQLException e) {
            logger.error("Error updating Produit", e);
        }
        logger.warn("Produit not updated in database");
        return false;
    }

    @Override
    public List<Produit> getAllProduits() {
        logger.info("Attempting to get all Produit");
        List<Produit> produits = new ArrayList<>();

        try {
            String getAllQuery = "SELECT * from produit;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {

                Produit produit = generateProduitFromResult(resultSet);
                produits.add(produit);
            }
            logger.info(" successfully get all produits");

        } catch (SQLException e) {
            logger.error("Error getting all produits", e);
        }
        return produits;
    }

    private Produit generateProduitFromResult(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString("produit_uuid");
        String name = resultSet.getString("name");
        String  description= resultSet.getString("description");
        float prix = resultSet.getFloat("prix");
        int qte_stock = resultSet.getInt("qte_stock");
        Produit produit = new Produit(name,description,prix,qte_stock);
        produit.setUuid(uuid);
        return produit;
    }

    @Override
    public Produit getProduitById(String uuid) {
        logger.info("Attempting to get produit by Id");
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from  produit where  produit_uuid = ?;"
                )) {
            preparedStatement.setString(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("produit successfully retrieved");
                    return generateProduitFromResult(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting produit", e);
        }
        logger.warn("produit not found in database");
        return null;
    }


}
