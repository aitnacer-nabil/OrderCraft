package com.example.artwood.dao;

import com.example.artwood.model.Commande;

import java.util.List;

public interface ICommandeDao {
    List<Commande> getAllCommandes();
    List<Commande> getAllCommandesByClient();
    Commande getCommande(String uuid);
    boolean insertCommande(Commande commande);
    boolean updateCommande(Commande commande);
    boolean deleteCommande(String uuid);
    boolean insertInTableCommandeProduit(String commandeUUID, String produitUUID,int amount);

}

