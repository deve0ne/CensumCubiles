package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.model_tab.model_elements.AbstractModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;

public class ModelTabController {
    public TreeTableColumn<AbstractModelElement, String> nameRow;
    public TreeTableColumn<AbstractModelElement, Double> amountRow;
    public TreeTableColumn<AbstractModelElement, Double> priceRow;
    public TreeTableColumn<AbstractModelElement, Button> plusButtonRow;
    public TreeTableView<AbstractModelElement> modelsTable;

    public void initialize() {
        modelsTable.setEditable(true);

        modelsTable.setRoot(new TreeItem<>());
        modelsTable.setShowRoot(false);

        plusButtonRow.setVisible(false);

        modelsTable.getRoot().getChildren().add(new TreeItem<>(new ModelElement("Ева")));

        initTableColumns();

       addSelectionListener();
    }

    private void initTableColumns() {
        nameRow.setCellValueFactory(param -> {
            if (param.getValue().getValue() == null)
                return null;

            return new SimpleStringProperty(param.getValue().getValue().getName());
        });

        nameRow.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());


//        plusButtonRow.setCellValueFactory(o -> {
//            Button addChildren = new Button("+");
//
//            //Не уверен, что обращаться к o -- лучшее решение.
//            addChildren.setOnMouseClicked(o1 ->
//                    o.getValue().getChildren().add(new TreeItem<>(new ModelElement("Новый элемент"))));
//
//            return new SimpleObjectProperty<>(addChildren);
//        });

        modelsTable.setContextMenu(new ModelContextMenu(modelsTable));
    }

    private void addSelectionListener() {
        modelsTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Node source = event.getPickResult().getIntersectedNode();


            //*Тут суть в чём: у пользователя всегда должна быть возможность сбросить фокус со строки таблицы.
            // Но таблица занимает ровно столько места, сколько ей нужно, и свободных пикселей для сброса фокуса не остаётся.
            // Я пытался добавлять пустое место в конец таблицы, но из-за особенностей javafx это не особо получилось.
            // Так что пока сделал костыль: TreeTableRow выбирается первым Source только тогда, когда ты тыкаешь на
            // несуществующий столбец. Если столбец будет существовать, то источником будет он, и выполнятся остальные
            // строки кода. FIXME в общем-то */
            if (source instanceof TreeTableRow<?>) {
                modelsTable.getSelectionModel().clearSelection();
                return;
            }


            //Поднимается выше пока не найдем строку
            while (source != null && !(source instanceof TreeTableRow<?>))
                source = source.getParent();

            //Если мы тыкнули на пустое место или вообще не на строку, то очищаем выделение
            if (source == null || ((TreeTableRow<?>) source).isEmpty())
                modelsTable.getSelectionModel().clearSelection();
        });
    }
}
