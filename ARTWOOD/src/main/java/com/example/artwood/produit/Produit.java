package com.example.artwood.produit;

import com.example.artwood.shared.Utils;

public class Produit {
    private String uuid;
    private String name;
    private String description;
    private float prix;
    private int qte_stock;

    @Override
    public String toString() {
        return "Produit{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", qte_stock=" + qte_stock +
                '}';
    }

    public Produit(String name, String description, float prix, int qte_stock) {
        this.uuid = Utils.GenerateId();
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.qte_stock = qte_stock;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }
}
