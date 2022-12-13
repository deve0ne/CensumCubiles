package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;

/**
 * @see com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement
 */

public class MaterialModelElement extends Material implements ModelTTVElement {
//    private double amountInModel = 0;

    public MaterialModelElement() {
    }

    public MaterialModelElement(String name, MaterialCategory category, double amount, double oneCost) {
        super(name, category, amount, oneCost);
    }

    public MaterialModelElement(Material material) {
        super(material.getName(), material.getCategory(), material.getAmount(), material.getOneCost());
    }
    //    public double getAmountInModel() {
//        return amountInModel;
//    }
//
//    public void setAmountInModel(double amountInModel) {
//        this.amountInModel = amountInModel;
//    }

//    @Override
//    public double getTotalCost() {
//        return getOneCost() * getAmountInModel();
//    }
}
