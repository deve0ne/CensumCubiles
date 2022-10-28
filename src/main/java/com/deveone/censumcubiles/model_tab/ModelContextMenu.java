package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.material_tab.material.MaterialCategory;
import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelCategoryElement;
import com.deveone.censumcubiles.model_tab.model_elements.AbstractModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelElement;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

public class ModelContextMenu extends ContextMenu {
    private final MenuItem addChildren = new MenuItem("Добавить потомка");
    private final MenuItem delSelf = new MenuItem("Удалить этот элемент");
    private final TreeTableView<AbstractModelElement> ttv;

    public ModelContextMenu(TreeTableView<AbstractModelElement> ttv) {
        super();

        this.ttv = ttv;

        initButtonListeners();
        initMenuListeners();

        getItems().addAll(addChildren, delSelf);
    }

    private void initMenuListeners() {
        this.setOnShowing(o -> {
            TreeItem<AbstractModelElement> selected = getSelectedItem();

            addChildren.setVisible(!(selected.getValue() instanceof MaterialModelElement));
        });
    }

    private void initButtonListeners() {
        addChildren.setOnAction(o -> {
            TreeItem<AbstractModelElement> selected = getSelectedItem();

            // TODO: 28.10.2022 Не самый лучший код, надо покумекать над улучшением
            if (selected.getValue() == null) {
                selected.getChildren().add(new TreeItem<>(new ModelElement("Новая модель")));

            } else if (selected.getValue() instanceof ModelElement)
                selected.getChildren().add(new TreeItem<>(new ModelCategoryElement(MaterialCategory.NO_CATEGORY)));

            else if (selected.getValue() instanceof ModelCategoryElement)
                selected.getChildren().add(new TreeItem<>(new MaterialModelElement("Новый материал")));
        });

        delSelf.setOnAction(o -> {
            TreeItem<AbstractModelElement> selected = getSelectedItem();

            if (selected.equals(ttv.getRoot()))
                return;

            selected.getParent().getChildren().remove(selected);
        });
    }

    private TreeItem<AbstractModelElement> getSelectedItem() {
        TreeItem<AbstractModelElement> selected = ttv.getSelectionModel().getSelectedItem();

        if (selected == null)
            selected = ttv.getRoot();

        return selected;
    }
}
