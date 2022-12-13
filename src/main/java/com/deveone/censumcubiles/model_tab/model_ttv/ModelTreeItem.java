package com.deveone.censumcubiles.model_tab.model_ttv;

import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import org.controlsfx.property.BeanPropertyUtils;

import java.util.Properties;

/**
 * Я не уверен, точно ли оно надо. И что оно вообще делает.
 * TODO 13.12.22 Разобраться
 * UPD: Я уверен, что оно на данный момент не надо. Осталось лишь переписать места, где оно используется.
 */

public class ModelTreeItem extends TreeItem<ModelTTVElement> {
    public ModelTreeItem() {
    }

    public ModelTreeItem(ModelTTVElement value) {
        super(value);
    }

    public ModelTreeItem(ModelTTVElement value, Node graphic) {
        super(value, graphic);
    }

    public double getCost() {
        double cost = 0;

        if (getValue() instanceof MaterialModelElement) {
            System.out.println(getValue());
            return ((MaterialModelElement) getValue()).getTotalCost();
        }

        for (TreeItem<ModelTTVElement> item : getChildren()) {
            if (item instanceof ModelTreeItem)
                cost += ((ModelTreeItem) item).getCost();
        }

        return cost;
    }

//    private costUpdated
}
