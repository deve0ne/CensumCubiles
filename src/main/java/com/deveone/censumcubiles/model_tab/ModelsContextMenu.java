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

public class ModelsContextMenu extends ContextMenu {
    private final MenuItem addChildren = new MenuItem("Добавить потомка");
    private final MenuItem delSelf = new MenuItem("Удалить этот элемент");

    public ModelsContextMenu(TreeTableView<AbstractModelElement> ttv) {
        super();

        initButtonListeners(ttv);

        getItems().addAll(addChildren, delSelf);
    }

    private void initButtonListeners(TreeTableView<AbstractModelElement> ttv) {
        addChildren.setOnAction(o -> {
            TreeItem<AbstractModelElement> selected = ttv.getSelectionModel().getSelectedItem();

            if (selected == null)
                selected = ttv.getRoot();

            if (!selected.isLeaf())
                selected.getChildren().add(new TreeItem<>(new ModelElement("Новая модель")));
            else if (selected.getValue() instanceof ModelElement)
                selected.getChildren().add(new TreeItem<>(new ModelCategoryElement(MaterialCategory.NO_CATEGORY)));
            else if (selected.getValue() instanceof ModelCategoryElement)
                selected.getChildren().add(new TreeItem<>(new MaterialModelElement("Новый материал")));
        });

        delSelf.setOnAction(o -> {
            TreeItem<AbstractModelElement> selected = ttv.getSelectionModel().getSelectedItem();

            if (selected == null)
                return;

            selected.getParent().getChildren().remove(selected);

            //Чтобы мы случайно не удалили корневой элемент
            if (!selected.isLeaf())
                ttv.getSelectionModel().clearSelection();
        });
    }
}
