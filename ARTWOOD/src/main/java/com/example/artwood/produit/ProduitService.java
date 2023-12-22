package com.example.artwood.produit;

import com.example.artwood.client.Client;
import com.example.artwood.client.ClientDao;
import com.example.artwood.client.ClientDaoImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProduitService {
    private ProduitDao produitDao;
    private static final Logger logger = LogManager.getLogger(ProduitService.class);

    public ProduitService(

    ) {
        this.produitDao = new ProduitDaoImp();
    }

    public boolean addProduit(Produit produit) {
        logger.info("Adding new produit");
        return produitDao.insertProduit(produit);
    }

    public boolean updateProduit(String uuid,Produit produit) {
        logger.info("Updating Produit");
        return produitDao.updateProduit(uuid,produit);
    }

    public boolean deleteProduit(String uuid) {
        logger.info("Deleting Produit");
        return produitDao.deleteProduit(uuid);
    }

    public Produit getProduitById(String uuid) {
        logger.info("Fetching Produit");
        return produitDao.getProduitById(uuid);
    }

    public List<Produit> getAllProduits() {
        logger.info("Fetching all Produits");
        return produitDao.getAllProduits();
    }
}
