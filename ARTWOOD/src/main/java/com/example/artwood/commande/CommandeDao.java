package com.example.artwood.commande;

import java.util.List;

public interface CommandeDao {
    List<Commande> getAllCommandes();
    List<Commande> getAllCommandesByClient();
    Commande getCommande(String uuid);
    boolean insertCommande(Commande commande);
    boolean updateCommande(Commande commande);
    boolean deleteCommande(String uuid);

}
