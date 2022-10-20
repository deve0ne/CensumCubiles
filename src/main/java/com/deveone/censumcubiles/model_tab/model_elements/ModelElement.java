package com.deveone.censumcubiles.model_tab.model_elements;

import javafx.beans.property.SimpleStringProperty;

public class ModelElement {
    protected final SimpleStringProperty name = new SimpleStringProperty();

    public ModelElement(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getTotalCost() {
        return 0;
    }
}
