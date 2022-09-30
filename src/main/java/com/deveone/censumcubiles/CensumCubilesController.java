package com.deveone.censumcubiles;

import com.deveone.censumcubiles.Database.DBHelper;
import com.deveone.censumcubiles.Material.Material;
import com.deveone.censumcubiles.Material.MaterialCategory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class CensumCubilesController {
    @FXML
    private TableView<Material> materialsTable;
    @FXML
    private TableColumn<Material, Integer> materialIndex;
    @FXML
    private TableColumn<Material, MaterialCategory> materialCategory;
    @FXML
    private TableColumn<Material, String> materialName;
    @FXML
    private TableColumn<Material, Integer> materialAmount;
    @FXML
    private TableColumn<Material, Integer> materialOneCost;
    @FXML
    private TableColumn<Material, Integer> materialTotalCost;

    public void initialize() {
        materialsTable.setEditable(true);

        materialIndex.setEditable(false);
        materialIndex.setCellValueFactory(new PropertyValueFactory<>("id"));
        materialIndex.setVisible(false);

        materialName.setCellValueFactory(new PropertyValueFactory<>("name"));
        materialName.setCellFactory(TextFieldTableCell.forTableColumn());
        materialName.setOnEditCommit(o -> {
            o.getRowValue().setName(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
        });

        materialCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        materialCategory.setCellFactory(ComboBoxTableCell.forTableColumn(MaterialCategory.values()));
        materialCategory.setOnEditCommit(o -> {
            o.getRowValue().setCategory(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
        });

        materialAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        materialAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        materialAmount.setOnEditCommit(o -> {
            o.getRowValue().setAmount(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
        });

        materialOneCost.setCellValueFactory(new PropertyValueFactory<>("oneCost"));
        materialOneCost.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        materialOneCost.setOnEditCommit(o -> {
            o.getRowValue().setOneCost(o.getNewValue());
            DBHelper.changeMaterial(o.getRowValue());
        });

        materialTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        materialTotalCost.setEditable(false);
//        materialTotalCost.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        materialTotalCost.setOnEditCommit(o -> o.getRowValue().setTotalCost(o.getNewValue()));

        materialsTable.getItems().addAll(DBHelper.getAllMaterials());

    }

    public void onAddRowPressed() {
        Material newMat = new Material();
        DBHelper.addMaterial(newMat);
        materialsTable.getItems().add(newMat);
        materialsTable.getSelectionModel().select(newMat);
        materialsTable.scrollTo(getLastMatId());
    }

    public void onDelRowPressed() {
        Material selected = materialsTable.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        materialsTable.getItems().remove(selected);
        DBHelper.removeMaterial(selected);
    }

    private int getLastMatId() {
        return materialsTable.getItems().size();
    }
}