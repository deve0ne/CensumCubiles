package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;

public class MaterialModelElement extends Material implements TTVElement {
    public MaterialModelElement() {
    }

    public MaterialModelElement(String name, MaterialCategory category, double amount, double oneCost) {
        super(name, category, amount, oneCost);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
