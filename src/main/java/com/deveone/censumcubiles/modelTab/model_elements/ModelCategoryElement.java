package com.deveone.censumcubiles.modelTab.model_elements;

import com.deveone.censumcubiles.materialTab.material.MaterialCategory;
import javafx.beans.property.SimpleDoubleProperty;

public class ModelCategoryElement extends ModelElement{
    protected MaterialCategory category;

    public ModelCategoryElement(MaterialCategory category) {
        super(category.getEncodedRuName());
        this.category = category;
    }
}
