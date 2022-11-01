package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material.MaterialCategory;

public class CategoryModelElement implements TTVElement {
    protected MaterialCategory category;

    public CategoryModelElement() {
        category = MaterialCategory.NO_CATEGORY;
    }

    public CategoryModelElement(MaterialCategory category) {
        this.category = category;
    }

    @Override
    public String getName() {
        return category.getEncodedRuName();
    }
}
