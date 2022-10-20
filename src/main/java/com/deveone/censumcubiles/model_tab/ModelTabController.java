package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.model_tab.model_elements.ModelElement;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class ModelTabController {
    public TreeTableColumn<ModelElement, String> nameRow;
    public TreeTableColumn<ModelElement, Double> amountRow;
    public TreeTableColumn<ModelElement, Double> priceRow;
    public TreeTableColumn<ModelElement, Button> plusButtonRow;
    public TreeTableView<ModelElement> modelsTable;

    public void initialize() {
        modelsTable.setEditable(true);


        modelsTable.setRoot(new TreeItem<>());
        modelsTable.getRoot().getChildren().add(new TreeItem<>(new ModelElement("Ева")));
    }
}
