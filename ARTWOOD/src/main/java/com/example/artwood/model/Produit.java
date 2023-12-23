package com.example.artwood.model;

import com.example.artwood.shared.Utils;

public class Produit {
    private String uuid ="";
    private String name ="";
    private String description ="";
    private float prix =0;
    private int qte_stock = 0;
    private int qte_order = 0;
    private int amount =0;

    public Produit(String name, String description, float prix, int qte_stock) {
        this.uuid = Utils.GenerateId();
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.qte_stock = qte_stock;

    }

    public Produit() {
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

    public int getQte_order() {
        return qte_order;
    }

    public void setQte_order(int qte_order) {
        this.qte_order = qte_order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", qte_stock=" + qte_stock +
                ", qte_order=" + qte_order +
                ", amount=" + amount +
                '}';
    }
}
