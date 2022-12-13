package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.model_tab.model_elements.CategoryModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement;
import javafx.scene.control.TreeItem;

/**
 * Вспомогательные утилиты, использующиеся (на данный момент) только в ModelTabController для подсчёта стоимости элементов.
 */

public class ModelUtils {
    public static double calcAssemblyCost(TreeItem<ModelTTVElement> assemblyItem) {
        double assemblyCost = 0;

        for (TreeItem<ModelTTVElement> categoryItem : assemblyItem.getChildren()) {
            if (!(categoryItem.getValue() instanceof CategoryModelElement))
                continue;

            assemblyCost += calcCategoryCost(categoryItem);
        }

        return assemblyCost;
    }

    public static double calcCategoryCost(TreeItem<ModelTTVElement> categoryItem) {
        double categoryCost = 0;

        for (TreeItem<ModelTTVElement> matItem : categoryItem.getChildren()) {
            if (!(matItem.getValue() instanceof MaterialModelElement))
                continue;

            MaterialModelElement mat = (MaterialModelElement) matItem.getValue();
            categoryCost += mat.getTotalCost();
        }

        return categoryCost;
    }
}
