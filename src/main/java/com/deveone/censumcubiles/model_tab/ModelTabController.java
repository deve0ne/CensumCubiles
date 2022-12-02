package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.database.DBHelper;
import com.deveone.censumcubiles.database.MaterialDBHelper;
import com.deveone.censumcubiles.database.ModelDBHelper;
import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.model.Model;
import com.deveone.censumcubiles.model_tab.model_elements.CategoryModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement;
import com.deveone.censumcubiles.model_tab.model_ttv.ModelTreeItem;
import com.deveone.censumcubiles.number_converters.DecimalHideNumberConverter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ModelTabController {
    public TreeTableColumn<ModelTTVElement, String> nameRow;
    public TreeTableColumn<ModelTTVElement, Number> amountRow;
    public TreeTableColumn<ModelTTVElement, Number> costRow;
    public TreeTableColumn<ModelTTVElement, Button> plusButtonRow;
    public TreeTableView<ModelTTVElement> modelsTTV;

    public void initialize() {
        modelsTTV.setEditable(true);

        modelsTTV.setRoot(new TreeItem<>());
        modelsTTV.setShowRoot(false);

        plusButtonRow.setVisible(false);

        initTableColumns();

        addSelectionListener();
        modelsTTV.setContextMenu(new ModelContextMenu(modelsTTV));

//        TextFields.bindAutoCompletion()
//        TextFieldTreeTableCell

        loadModelsToTable();
    }

//        amountRow.setCellValueFactory(param -> {
//            return new SimpleDoubleProperty();
//        });


//        amountRow.setCellValueFactory(param -> {
//            AbstractModelElement modelElement = param.getValue().getValue();
//
//            if (modelElement instanceof MaterialModelElement) {
//                double amount = ((MaterialModelElement) modelElement).getAmount();
//                return
//            } else
//                return null;
//        });


//        plusButtonRow.setCellValueFactory(o -> {
//            Button addChildren = new Button("+");
//
//            //Не уверен, что обращаться к o -- лучшее решение.
//            addChildren.setOnMouseClicked(o1 ->
//                    o.getValue().getChildren().add(new TreeItem<>(new ModelElement("Новый элемент"))));
//
//            return new SimpleObjectProperty<>(addChildren);
//        });


    private void initTableColumns() {
        nameRow.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//        nameRow.setCellFactory(ttColumn -> new AutocompletionTextFieldTTV());

        nameRow.setCellValueFactory(param -> {
            ModelTTVElement modelElement = param.getValue().getValue();

            if (modelElement == null)
                return null;

            return new SimpleStringProperty(modelElement.getName());
        });

//        nameRow.setOnEditCommit(event -> {
//            ModelTTVElement modelElement = event.getRowValue().getValue();
//
//            if (modelElement == null)
//                return null;
//
//            if (modelElement instanceof MaterialModelElement) {
//
//            } else
//                return null;
//        });

//        nameRow.


        amountRow.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new DecimalHideNumberConverter()));

        amountRow.setCellValueFactory(param -> {
            TreeItem<ModelTTVElement> treeItem = param.getValue();
            ModelTTVElement modelElement = treeItem.getValue();

            if (modelElement == null)
                return null;

            if (modelElement instanceof MaterialModelElement) {
                double amount = ((MaterialModelElement) modelElement).getAmount();
                return new SimpleDoubleProperty(amount);
            } else
                return null;
        });

        amountRow.setOnEditCommit(event -> {
            if (!(event.getRowValue().getValue() instanceof MaterialModelElement modelElement))
                return;

            modelElement.setAmount(event.getNewValue().doubleValue());
            modelsTTV.refresh();
        });


        costRow.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new DecimalHideNumberConverter()));

        costRow.setCellValueFactory(param -> {
            //Очень забавная конструкция
            if (!(param.getValue() instanceof ModelTreeItem treeItem))
                return null;

            ModelTTVElement modelElement = treeItem.getValue();

            if (modelElement == null)
                return null;

            return new SimpleDoubleProperty(treeItem.getCost());
        });

        costRow.setEditable(false);
//        costRow.setOnEditCommit(event -> {
//            ModelTTVElement modelElement = event.getRowValue().getValue();
//
//            if (modelElement instanceof MaterialModelElement)
//                ((MaterialModelElement) modelElement).setTotalCost(event.getNewValue().doubleValue());
//
//            modelsTTV.refresh();
//        });
    }

    private boolean isModelTreeItem(TreeItem<ModelTTVElement> item) {
        return item instanceof ModelTreeItem;
    }

    private void addSelectionListener() {
        modelsTTV.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Node source = event.getPickResult().getIntersectedNode();


            //*Тут суть в чём: у пользователя всегда должна быть возможность сбросить фокус со строки таблицы.
            // Но таблица занимает ровно столько места, сколько ей нужно, и свободных пикселей для сброса фокуса не остаётся.
            // Я пытался добавлять пустое место в конец таблицы, но из-за особенностей javafx это не особо получилось.
            // Так что пока сделал костыль: TreeTableRow выбирается первым Source только тогда, когда ты тыкаешь на
            // несуществующий столбец. Если столбец будет существовать, то источником будет он, и выполнятся остальные
            // строки кода. FIXME в общем-то */
            if (source instanceof TreeTableRow<?>) {
                modelsTTV.getSelectionModel().clearSelection();
                return;
            }


            //Поднимается выше пока не найдем строку
            while (source != null && !(source instanceof TreeTableRow<?>))
                source = source.getParent();

            //Если мы тыкнули на пустое место или вообще не на строку, то очищаем выделение
            if (source == null || ((TreeTableRow<?>) source).isEmpty())
                modelsTTV.getSelectionModel().clearSelection();
        });
    }

    private void loadModelsToTable() {
        List<Model> list = ModelDBHelper.getAllModels();
        list.forEach(o -> {
            modelsTTV.getRoot().getChildren().add(o.getTTVRepresentation());
        });
//        modelsTTV.getRoot().getChildren().setAll(ModelDBHelper.getAllModels());
//        getTreeTableViewRepresentation(ModelDBHelper.getAllModels());
    }


}
