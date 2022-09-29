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

public class HelloController {
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

        materialName.setCellValueFactory(new PropertyValueFactory<>("name"));
        materialName.setCellFactory(TextFieldTableCell.forTableColumn());

        materialCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        materialCategory.setCellFactory(ComboBoxTableCell.forTableColumn(MaterialCategory.values()));

        materialAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        materialAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        materialOneCost.setCellValueFactory(new PropertyValueFactory<>("oneCost"));
        materialOneCost.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        materialTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        materialTotalCost.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        DBHelper.init();

        for (int i = 0; i < 100; i++) {
            Material test = new Material(getLastMatId(), MaterialCategory.WOOD, "Фанера", 100);
            materialsTable.getItems().add(test);
        }
    }

    public void onAddRowPressed() {
        Material newMat = new Material(getLastMatId());
        materialsTable.getItems().add(newMat);
        materialsTable.getSelectionModel().select(newMat);
        materialsTable.scrollTo(getLastMatId());
    }

    public void onDelRowPressed() {
        Material selected = materialsTable.getSelectionModel().getSelectedItem();
        materialsTable.getItems().remove(selected);
    }

    private int getLastMatId() {
        return materialsTable.getItems().size();
    }
}