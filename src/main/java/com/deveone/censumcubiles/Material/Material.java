package com.deveone.censumcubiles.Material;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Material {
    private SimpleIntegerProperty id;
    private MaterialCategory category;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty oneCost;
    private SimpleIntegerProperty totalCost = new SimpleIntegerProperty(0);

    public Material() {
        this.id = new SimpleIntegerProperty(-1);
        this.category = MaterialCategory.NO_CATEGORY;
        this.name = new SimpleStringProperty("");
        this.oneCost = new SimpleIntegerProperty(0);
    }

    public Material(int id, MaterialCategory category, String name, int amount, int oneCost) {
        this.id = new SimpleIntegerProperty(id);
        this.category = category;
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.oneCost = new SimpleIntegerProperty(oneCost);
    }
    public Material(MaterialCategory category, String name, int amount, int oneCost) {
        this.id = new SimpleIntegerProperty(-1);
        this.category = category;
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.oneCost = new SimpleIntegerProperty(oneCost);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public MaterialCategory getCategory() {
        return category;
    }

    public void setCategory(MaterialCategory category) {
        this.category = category;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getOneCost() {
        return oneCost.get();
    }

    public SimpleIntegerProperty oneCostProperty() {
        return oneCost;
    }

    public void setOneCost(int oneCost) {
        this.oneCost.set(oneCost);
    }

    public int getTotalCost() {
        return totalCost.get();
    }

    public SimpleIntegerProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost.set(totalCost);
    }
}
