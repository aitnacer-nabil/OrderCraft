package com.example.artwood.dao;

import com.example.artwood.model.Produit;

import java.util.List;

public interface IProduitDao {
    boolean insertProduit(Produit produit);
    boolean deleteProduit(String uuid);
    boolean updateProduit(String uuid , Produit produit);
    List<Produit> getAllProduits();
    Produit getProduitById(String uuid);
    void   updateQteProduit(Produit produit);
    List<Produit> getProduitsByCommande(String commandeUUID);

}
