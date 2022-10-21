package com.deveone.censumcubiles.model_tab.model_elements;

import javafx.beans.property.SimpleStringProperty;

public abstract class AbstractModelElement {
    protected final SimpleStringProperty name = new SimpleStringProperty();

    public AbstractModelElement(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getTotalCost() {
        return 0;
    }
}
