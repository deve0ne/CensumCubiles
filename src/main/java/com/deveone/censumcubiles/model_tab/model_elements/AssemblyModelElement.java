package com.deveone.censumcubiles.model_tab.model_elements;

public class AssemblyModelElement implements TTVElement {
    private final String assemblyName;

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
}