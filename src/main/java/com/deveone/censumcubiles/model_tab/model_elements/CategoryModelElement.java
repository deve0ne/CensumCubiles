package com.deveone.censumcubiles.model_tab.model_elements;

import com.deveone.censumcubiles.material.MaterialCategory;

/**
 * @see com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement
 */

public class CategoryModelElement implements ModelTTVElement {
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

    // TODO: 02.12.2022
    @Override
    public void setName(String newName) {}
}
