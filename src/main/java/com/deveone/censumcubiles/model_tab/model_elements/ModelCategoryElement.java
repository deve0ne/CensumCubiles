package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material_tab.material.MaterialCategory;

public class ModelCategoryElement extends AbstractModelElement {
    protected MaterialCategory category;

    public ModelCategoryElement(MaterialCategory category) {
        super(category.getEncodedRuName());
        this.category = category;
    }
}
