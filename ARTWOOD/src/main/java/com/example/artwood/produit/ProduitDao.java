package com.example.artwood.produit;

import com.example.artwood.client.Client;

import java.util.List;

public interface ProduitDao {
    boolean insertProduit(Produit produit);
    boolean deleteProduit(String uuid);
    boolean updateProduit(String uuid , Produit produit);
    List<Produit> getAllProduits();
    Produit getProduitById(String uuid);

}
