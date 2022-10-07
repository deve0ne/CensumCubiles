package com.deveone.censumcubiles.controllers;

import com.deveone.censumcubiles.database.DBHelper;
import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;
import com.deveone.censumcubiles.material_arrival_dialog.MaterialArrivalDialog;
import com.deveone.censumcubiles.tableview_formats.DoubleDecimalHideConverter;
import com.deveone.censumcubiles.tableview_formats.DoublePriceConverter;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.stream.Stream;

public class MaterialTabController {
    public TableView<Material> materialsTable;
    public TableColumn<Material, Integer> materialIndex;
    public TableColumn<Material, MaterialCategory> materialCategory;
    public TableColumn<Material, String> materialName;
    public TableColumn<Material, Double> materialAmount;
    public TableColumn<Material, Double> materialOneCost;
    public TableColumn<Material, Double> materialTotalCost;
    public TextField materialSearch;
    public Button delRowButton;
    public Button addRowButton;

    public void initialize() {
        materialsTable.setEditable(true);
        materialsTable.getSortOrder().add(materialCategory);
        materialsTable.getSortOrder().add(materialName);

        materialIndex.setEditable(false);
        materialIndex.setCellValueFactory(new PropertyValueFactory<>("id"));
        materialIndex.setVisible(false);

        materialCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        materialCategory.setCellFactory(ComboBoxTableCell.forTableColumn(MaterialCategory.values()));
        materialCategory.setOnEditCommit(o -> {
            Material mat = o.getRowValue();
            mat.setCategory(o.getNewValue());
            DBHelper.changeMaterial(mat);
            materialsTable.sort();
            materialsTable.scrollTo(mat);
        });
        materialCategory.setComparator(MaterialCategory.categoryComparator);
//        materialCategory.setSortType(TableColumn.SortType.DESCENDING);
//        materialCategory.sortNodeProperty().unbind();

        materialName.setCellValueFactory(new PropertyValueFactory<>("name"));
        materialName.setCellFactory(TextFieldTableCell.forTableColumn());
        materialName.setOnEditCommit(o -> {
            o.getRowValue().setName(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
            materialsTable.sort();
        });

        materialAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        materialAmount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleDecimalHideConverter()));
        materialAmount.setOnEditCommit(o -> {
            o.getRowValue().setAmount(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
        });

        materialOneCost.setCellValueFactory(new PropertyValueFactory<>("oneCost"));
        materialOneCost.setCellFactory(TextFieldTableCell.forTableColumn(new DoublePriceConverter()));
        materialOneCost.setOnEditCommit(o -> {
            o.getRowValue().setOneCost(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
        });

        materialTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        materialTotalCost.setEditable(false);
        materialTotalCost.setCellFactory(TextFieldTableCell.forTableColumn(new DoublePriceConverter()));
//        materialTotalCost.setOnEditCommit(o -> o.getRowValue().setTotalCost(o.getNewValue()));

        loadMatsToTable();

        materialSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("") || oldValue.length() > newValue.length())
                loadMatsToTable();

            Stream<Material> filteredMats = materialsTable.getItems().stream().filter(material ->
                    material.getName().toLowerCase().contains(newValue.toLowerCase()));

            materialsTable.getItems().setAll(filteredMats.toList());

            materialsTable.sort();
        });
    }

    private void loadMatsToTable() {
        materialsTable.getItems().setAll(DBHelper.getAllMaterials());
        materialsTable.sort();
    }

    public void onAddRowPressed() {
        Material newMat = new Material();
        DBHelper.addMaterial(newMat);
        materialsTable.getItems().add(newMat);
        materialsTable.sort();
        materialsTable.getSelectionModel().select(newMat);
        materialsTable.scrollTo(newMat);
    }

    public void onDelRowPressed() {
        Material selected = materialsTable.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        materialsTable.getItems().remove(selected);
        DBHelper.removeMaterial(selected);
    }

    public void onArrivalPressed() {
        try {
            new MaterialArrivalDialog().setOnHidden(o -> loadMatsToTable());
        } catch (IOException e) {
            System.err.println("Ошибка в создании диалога прихода");
        }
    }
}
