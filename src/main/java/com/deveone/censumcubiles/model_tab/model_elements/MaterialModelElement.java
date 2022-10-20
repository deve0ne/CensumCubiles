package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material_tab.material.Material;
import javafx.beans.property.SimpleDoubleProperty;

public class MaterialModelElement extends ModelCategoryElement {
    protected Material material;
    protected SimpleDoubleProperty amount = new SimpleDoubleProperty(0);

    public MaterialModelElement(Material material, double amount) {
        super(material.getCategory());
        name.set(material.getName());
        this.material = material;
        this.amount.set(amount);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public double getTotalCost() {
        return amount.get() * material.getOneCost();
    }
}
