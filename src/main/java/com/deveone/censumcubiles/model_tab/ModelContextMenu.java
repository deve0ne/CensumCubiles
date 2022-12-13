package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.CategoryModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.AssemblyModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement;
import com.deveone.censumcubiles.model_tab.model_ttv.ModelTreeItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

/**
 * Контекстная менюшка, открывающаяся на различных элементах модели. Позволяет удалять и добавлять эти элементы.
 */

public class ModelContextMenu extends ContextMenu {
    private final MenuItem addChildren = new MenuItem("Добавить потомка");
    private final MenuItem delSelf = new MenuItem("Удалить этот элемент");
    private final TreeTableView<ModelTTVElement> ttv;

    public ModelContextMenu(TreeTableView<ModelTTVElement> ttv) {
        super();

        this.ttv = ttv;

        initButtonListeners();
        initMenuListeners();

        getItems().addAll(addChildren, delSelf);
    }

    private void initMenuListeners() {
        this.setOnShowing(o -> {
            TreeItem<ModelTTVElement> selected = getSelectedItem();

            addChildren.setVisible(!(selected.getValue() instanceof MaterialModelElement));
        });
    }

    private void initButtonListeners() {
        addChildren.setOnAction(o -> {
            TreeItem<ModelTTVElement> selected = getSelectedItem();

            // TODO: 28.10.2022 Не самый лучший код, надо покумекать над улучшением
            if (selected.getValue() == null) {
                selected.getChildren().add(new ModelTreeItem(new AssemblyModelElement()));

            } else if (selected.getValue() instanceof AssemblyModelElement)
                selected.getChildren().add(new ModelTreeItem(new CategoryModelElement()));

            else if (selected.getValue() instanceof CategoryModelElement)
                selected.getChildren().add(new ModelTreeItem(new MaterialModelElement()));
        });

        delSelf.setOnAction(o -> {
            TreeItem<ModelTTVElement> selected = getSelectedItem();

            if (selected.equals(ttv.getRoot()))
                return;

            selected.getParent().getChildren().remove(selected);
        });
    }

    private TreeItem<ModelTTVElement> getSelectedItem() {
        TreeItem<ModelTTVElement> selected = ttv.getSelectionModel().getSelectedItem();

        if (selected == null)
            selected = ttv.getRoot();

        return selected;
    }
}
