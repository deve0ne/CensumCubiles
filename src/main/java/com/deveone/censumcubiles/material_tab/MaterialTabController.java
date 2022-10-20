package com.deveone.censumcubiles.material_tab;

import com.deveone.censumcubiles.database.DBHelper;
import com.deveone.censumcubiles.material_tab.material.Material;
import com.deveone.censumcubiles.material_tab.material.MaterialCategory;
import com.deveone.censumcubiles.material_tab.material_arrival_dialog.MaterialArrivalDialog;
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

        initColumns();

        loadMatsToTable();

        initSearch();
    }

    private void initColumns() {
        materialIndex.setEditable(false);
        materialIndex.setCellValueFactory(new PropertyValueFactory<>("id"));
        materialIndex.setVisible(false);

        materialCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        materialCategory.setCellFactory(ComboBoxTableCell.forTableColumn(MaterialCategory.values()));
        materialCategory.setOnEditCommit(o -> {
            Material newMat = o.getRowValue();
            newMat.setCategory(o.getNewValue());
            DBHelper.changeMaterial(newMat.getName(), newMat); //Т.к. имя не меняется, можем передавать одно и то же
            materialsTable.sort();
            materialsTable.scrollTo(newMat);
        });
        materialCategory.setComparator(MaterialCategory.categoryComparator);
//        materialCategory.setSortType(TableColumn.SortType.DESCENDING);
//        materialCategory.sortNodeProperty().unbind();

        materialName.setCellValueFactory(new PropertyValueFactory<>("name"));
        materialName.setCellFactory(TextFieldTableCell.forTableColumn());
        materialName.setOnEditCommit(o -> {
            Material newMat = o.getRowValue();
            String oldName = newMat.getName();

            newMat.setName(o.getNewValue());
            materialsTable.refresh(); //Вероятно не требуется

            DBHelper.changeMaterial(oldName, o.getRowValue());
            materialsTable.sort();
        });

        materialAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        materialAmount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleDecimalHideConverter()));
        materialAmount.setOnEditCommit(o -> {
            Material newMat = o.getRowValue();

            newMat.setAmount(o.getNewValue());
            materialsTable.refresh(); //Вероятно не требуется

            DBHelper.changeMaterial(newMat.getName(), newMat);
        });

        materialOneCost.setCellValueFactory(new PropertyValueFactory<>("oneCost"));
        materialOneCost.setCellFactory(TextFieldTableCell.forTableColumn(new DoublePriceConverter()));
        materialOneCost.setOnEditCommit(o -> {
            Material newMat = o.getRowValue();

            newMat.setOneCost(o.getNewValue());
            materialsTable.refresh(); //Вероятно не требуется

            DBHelper.changeMaterial(newMat.getName(), newMat);

        });

        materialTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        materialTotalCost.setEditable(false);
        materialTotalCost.setCellFactory(TextFieldTableCell.forTableColumn(new DoublePriceConverter()));
//        materialTotalCost.setOnEditCommit(o -> o.getRowValue().setTotalCost(o.getNewValue()));
    }

    private void initSearch() {
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
            System.err.println("Ошибка в создании диалога прихода: " + e);
        }
    }
}