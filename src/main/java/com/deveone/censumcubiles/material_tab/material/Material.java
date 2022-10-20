package com.deveone.censumcubiles.material_tab.material;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Material {
    private MaterialCategory category;
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty oneCost = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty totalCost = new SimpleDoubleProperty(0);

    public Material() {
        this.category = MaterialCategory.NO_CATEGORY;
    }

    public Material(String name, MaterialCategory category, double amount, double oneCost) {
        this.category = category;
        this.name.set(name);
        this.amount.set(amount);
        this.oneCost.set(oneCost);
        this.totalCost.set((int) (amount * oneCost));
    }

    private void recalcTotalCost() {
        totalCost.set(amount.get() * oneCost.get());
    }

    private void recalcOneCost() {
        oneCost.set(totalCost.get() / amount.get());
    }

    public void mergeMaterial(Material matToMerge) {
        addAmount(matToMerge.getAmount());
        setOneCost((totalCost.get() + matToMerge.totalCost.get()) / amount.get());
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

//    public String getDecodedName() {
//        return new String(name.get().getBytes(StandardCharsets.UTF_8));
//    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getAmount() {
        return amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
        if (oneCost.get() > 0)
            recalcTotalCost();
        else if (totalCost.get() > 0)
            recalcOneCost();
    }

    public void addAmount(double valueToAdd) {
        amount.set(amount.get() + valueToAdd);
    }

    public double getOneCost() {
        return oneCost.get();
    }

    public void setOneCost(double oneCost) {
        this.oneCost.set(oneCost);
        if (amount.get() > 0)
            recalcTotalCost();
    }


    public double getTotalCost() {
        return totalCost.get();
    }

    public void setTotalCost(double totalCost) {
        this.totalCost.set(totalCost);
        if (amount.get() > 0)
            recalcOneCost();
    }
}
