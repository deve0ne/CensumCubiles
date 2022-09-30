package com.deveone.censumcubiles;

import com.deveone.censumcubiles.Database.DBHelper;
import com.deveone.censumcubiles.Material.Material;
import com.deveone.censumcubiles.Material.MaterialCategory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.stream.Stream;

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
    @FXML
    private TextField materialSearch;

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
        materialsTable.sort();

        materialSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("") || oldValue.length() > newValue.length())
                materialsTable.getItems().setAll(DBHelper.getAllMaterials());

            Stream<Material> filteredMats = materialsTable.getItems().stream().filter(material ->
                    material.getName().toLowerCase().contains(newValue.toLowerCase()));

            materialsTable.getItems().setAll(filteredMats.toList());

            materialsTable.sort();
        });


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
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);

        Button okButton = new Button("CLOSE");
        okButton.setOnAction(arg0 -> myDialog.close());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(new Text("Hello! it's My Dialog."), okButton);
        vBox.alignmentProperty().set(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        Scene myDialogScene = new Scene(vBox);

        myDialog.setScene(myDialogScene);
        myDialog.show();
    }
}