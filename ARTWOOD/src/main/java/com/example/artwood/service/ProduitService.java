package com.example.artwood.service;

import com.example.artwood.dao.IProduitDao;
import com.example.artwood.dao.impl.ProduitDaoImp;
import com.example.artwood.model.Produit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProduitService {
    private IProduitDao IProduitDao;
    private static final Logger logger = LogManager.getLogger(ProduitService.class);

    public ProduitService(

    ) {
        this.IProduitDao = new ProduitDaoImp();
    }

    public boolean addProduit(Produit produit) {
        logger.info("Adding new produit");
        return IProduitDao.insertProduit(produit);
    }

    public boolean updateProduit(String uuid,Produit produit) {
        logger.info("Updating Produit");
        return IProduitDao.updateProduit(uuid,produit);
    }

    public boolean deleteProduit(String uuid) {
        logger.info("Deleting Produit");
        return IProduitDao.deleteProduit(uuid);
    }

    public Produit getProduitById(String uuid) {
        logger.info("Fetching Produit");
        return IProduitDao.getProduitById(uuid);
    }

    public List<Produit> getAllProduits() {
        logger.info("Fetching all Produits");
        return IProduitDao.getAllProduits();
    }
    public void updateQtePRoduit(Produit produit){
        logger.info("Updating Qte for produits : "+ produit);
        IProduitDao.updateQteProduit(produit);
    }
    public List<Produit> getProduitsByCommande(String commandeUUID){
        return  IProduitDao.getProduitsByCommande(commandeUUID);
    }
}
