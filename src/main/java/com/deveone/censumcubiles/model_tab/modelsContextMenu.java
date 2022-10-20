package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.model_tab.model_elements.ModelElement;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

public class modelsContextMenu extends ContextMenu {
    MenuItem addChildren = new MenuItem("Добавить потомка");
    MenuItem delSelf = new MenuItem("Удалить этот элемент");
    TreeTableView<ModelElement> treeTableView;

    public modelsContextMenu(TreeTableView<ModelElement> ttv) {
        super();

        treeTableView = ttv;

        addChildren.setOnAction(o -> {
            TreeItem<ModelElement> selected = ttv.getSelectionModel().getSelectedItem();
            selected.getChildren().add(new TreeItem<>(new ModelElement("Новый элемент")));


        });

        delSelf.setOnAction(o -> {
            TreeItem<ModelElement> selected = ttv.getSelectionModel().getSelectedItem();
//            if (selected.isLeaf())
//                selected.getParent().getChildren().remove(selected);

            System.out.println(selected.isLeaf());
        });

        getItems().addAll(addChildren, delSelf);
    }


}
