package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;

public class MaterialModelElement extends Material implements ModelTTVElement {
    public MaterialModelElement() {
    }

    public MaterialModelElement(String name, MaterialCategory category, double amount, double oneCost) {
        super(name, category, amount, oneCost);
    }

    public MaterialModelElement(Material material) {
        super(material.getName(), material.getCategory(), material.getAmount(), material.getOneCost());
    }
}
