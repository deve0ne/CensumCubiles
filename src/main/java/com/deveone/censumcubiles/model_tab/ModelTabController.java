package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.model_tab.model_elements.ModelElement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;

public class ModelTabController {
    public TreeTableColumn<ModelElement, String> nameRow;
    public TreeTableColumn<ModelElement, Double> amountRow;
    public TreeTableColumn<ModelElement, Double> priceRow;
    public TreeTableColumn<ModelElement, Button> plusButtonRow;
    public TreeTableView<ModelElement> modelsTable;

    public void initialize() {
        modelsTable.setEditable(true);

        modelsTable.setRoot(new TreeItem<>(new ModelElement("Ева")));
        modelsTable.getRoot().getChildren().add(new TreeItem<>(new ModelElement("Каркас")));

        initTableColumns();
    }

    private void initTableColumns() {
        nameRow.setCellValueFactory(param -> {
            if (param.getValue().getValue() == null)
                return null;

            return new SimpleStringProperty(param.getValue().getValue().getName());
        });

        nameRow.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());


        plusButtonRow.setCellValueFactory(o -> {
            Button addChildren = new Button("+");

            //Не уверен, что обращаться к o -- лучшее решение.
            addChildren.setOnMouseClicked(o1 ->
                    o.getValue().getChildren().add(new TreeItem<>(new ModelElement("Новый элемент"))));

            return new SimpleObjectProperty<>(addChildren);
        });

        modelsTable.setContextMenu(new modelsContextMenu(modelsTable));
    }

}
