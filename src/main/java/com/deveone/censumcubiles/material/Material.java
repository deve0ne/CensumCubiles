package com.deveone.censumcubiles.material;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Material {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(-1);
    private MaterialCategory category;
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty oneCost = new SimpleDoubleProperty(0);
    private final SimpleIntegerProperty totalCost = new SimpleIntegerProperty(0);

    public Material() {
        this.category = MaterialCategory.NO_CATEGORY;
    }

    public Material(int id, MaterialCategory category, String name, double amount, double oneCost) {
        this.id = new SimpleIntegerProperty(id);
        this.category = category;
        this.name.set(name);
        this.amount.set(amount);
        this.oneCost.set(oneCost);
        this.totalCost.set((int) (amount * oneCost));
    }

    private void recalcTotalCost() {
        totalCost.set((int) (amount.get() * oneCost.get()));
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

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
        recalcTotalCost();
    }

    public double getOneCost() {
        return oneCost.get();
    }

    public void setOneCost(double oneCost) {
        this.oneCost.set(oneCost);
        recalcTotalCost();
    }

    public SimpleDoubleProperty oneCostProperty() {
        return oneCost;
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
