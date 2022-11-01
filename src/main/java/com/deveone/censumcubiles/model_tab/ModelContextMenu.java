package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.material.MaterialCategory;
import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.CategoryModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.AssemblyModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.TTVElement;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

public class ModelContextMenu extends ContextMenu {
    private final MenuItem addChildren = new MenuItem("Добавить потомка");
    private final MenuItem delSelf = new MenuItem("Удалить этот элемент");
    private final TreeTableView<TTVElement> ttv;

    public ModelContextMenu(TreeTableView<TTVElement> ttv) {
        super();

        this.ttv = ttv;

        initButtonListeners();
        initMenuListeners();

        getItems().addAll(addChildren, delSelf);
    }

    private void initMenuListeners() {
        this.setOnShowing(o -> {
            TreeItem<TTVElement> selected = getSelectedItem();

            addChildren.setVisible(!(selected.getValue() instanceof MaterialModelElement));
        });
    }

    private void initButtonListeners() {
        addChildren.setOnAction(o -> {
            TreeItem<TTVElement> selected = getSelectedItem();

            // TODO: 28.10.2022 Не самый лучший код, надо покумекать над улучшением
            if (selected.getValue() == null) {
                selected.getChildren().add(new TreeItem<>(new AssemblyModelElement()));

            } else if (selected.getValue() instanceof AssemblyModelElement)
                selected.getChildren().add(new TreeItem<>(new CategoryModelElement()));

            else if (selected.getValue() instanceof CategoryModelElement)
                selected.getChildren().add(new TreeItem<>(new MaterialModelElement()));
        });

        delSelf.setOnAction(o -> {
            TreeItem<TTVElement> selected = getSelectedItem();

            if (selected.equals(ttv.getRoot()))
                return;

            selected.getParent().getChildren().remove(selected);
        });
    }

    private TreeItem<TTVElement> getSelectedItem() {
        TreeItem<TTVElement> selected = ttv.getSelectionModel().getSelectedItem();

        if (selected == null)
            selected = ttv.getRoot();

        return selected;
    }
}
