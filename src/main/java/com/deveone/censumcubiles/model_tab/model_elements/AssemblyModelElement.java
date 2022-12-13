package com.deveone.censumcubiles.model_tab.model_elements;

/**
 * @see com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement
 */

public class AssemblyModelElement implements ModelTTVElement {
    private String assemblyName;

    public AssemblyModelElement() {
        this.assemblyName = "Новая модель";
    }

    public AssemblyModelElement(String assemblyName) {
        this.assemblyName = assemblyName;
    }

    @Override
    public String getName() {
        return assemblyName;
    }

    @Override
    public void setName(String newName) {
        this.assemblyName = newName;
    }
}