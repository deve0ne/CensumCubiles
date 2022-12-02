package com.deveone.censumcubiles.model;

import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;
import com.deveone.censumcubiles.model_tab.model_elements.AssemblyModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.CategoryModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.MaterialModelElement;
import com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.Objects;

public class Model {
    private String modelName;
    private ArrayList<Material> materials;

    public Model(String modelName) {
        this.modelName = modelName;
        materials = new ArrayList<>();
    }

    public TreeItem<ModelTTVElement> getTTVRepresentation() {
        TreeItem<ModelTTVElement> rootItem = new TreeItem<>(new AssemblyModelElement(modelName));

        // TODO: 02.12.2022 Какой-то слишком нагруженный цикл, надо бы потом упростить.
        for (Material material:materials) {
            ObservableList<TreeItem<ModelTTVElement>> categories = rootItem.getChildren();
            String matCategory =  material.getCategory().getEncodedRuName();

            TreeItem<ModelTTVElement> categoryTreeItem = findCategoryTreeItem(categories, matCategory);

            if (categoryTreeItem == null) {
                System.out.println(matCategory);
                categoryTreeItem = new TreeItem<>(new CategoryModelElement(MaterialCategory.getEnum(matCategory)));
                categories.add(categoryTreeItem);
            }

            TreeItem<ModelTTVElement> matTreeItem = new TreeItem<>(new MaterialModelElement(material));

            categoryTreeItem.getChildren().add(matTreeItem);
        }

        return rootItem;
    }

    public TreeItem<ModelTTVElement> findCategoryTreeItem(ObservableList<TreeItem<ModelTTVElement>> listToSearch, String searchedCategory) {
        for (TreeItem<ModelTTVElement> categoryTreeItem:listToSearch) {
            if (categoryTreeItem.getValue().getName().equals(searchedCategory))
                return categoryTreeItem;
        }

        return null;
    }


    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public void addMaterial(Material newMat) {
        materials.add(newMat);
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelName='" + modelName + '\'' +
                ", materials=" + materials +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return modelName.equals(model.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName);
    }
}
