package com.deveone.censumcubiles.material_arrival_dialog;

import com.deveone.censumcubiles.database.DBHelper;
import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.tableview_formats.DoubleDecimalHideConverter;
import com.deveone.censumcubiles.tableview_formats.DoublePriceConverter;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MaterialArrivalController {
    public TableView<Material> table;

    public TableColumn<Material, String> categoryCol;
    public TableColumn<Material, String> nameCol;
    public TableColumn<Material, Double> amountCol;
    public TableColumn<Material, Double> oneCostCol;
    public TableColumn<Material, Double> totalCostCol;

    public Button addRowButton;
    public Button delRowButton;
    public Button applyButton;
    public Button cancelButton;

    public TextField sumField;

    private final ArrayList<Material> arrivedMats = new ArrayList<>();


    public void initialize() {
        initButtons();
        initTableCells();

        recalcSumCost();
    }

    private void initButtons() {
        addRowButton.setOnMousePressed(o -> {
            Material newMat = new Material();
            arrivedMats.add(newMat);
            table.getItems().add(newMat);
            table.getSelectionModel().select(newMat);
            table.scrollTo(newMat);
        });

        delRowButton.setOnMousePressed(o -> {
            Material selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) return;

            table.getItems().remove(selected);
            arrivedMats.remove(selected);
        });

        applyButton.setOnMouseClicked(o -> {
            for (Material arrivedMat : arrivedMats) {
                Material matInDB = DBHelper.getMaterialByName(arrivedMat.getName());

                if (matInDB != null) {
                    matInDB.mergeMaterial(arrivedMat);
                    DBHelper.changeMaterial(matInDB);
                } else
                    DBHelper.addMaterial(arrivedMat);
            }

            Button source = (Button) o.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });

        cancelButton.setOnMouseClicked(o -> {
            Button source = (Button) o.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }

    private void initTableCells() {
        table.setEditable(true);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(o -> o.getRowValue().setName(o.getNewValue()));

        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleDecimalHideConverter()));
        amountCol.setOnEditCommit(o -> {
            o.getRowValue().setAmount( o.getNewValue());
            recalcSumCost();
        });

        oneCostCol.setCellValueFactory(new PropertyValueFactory<>("oneCost"));
        oneCostCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoublePriceConverter()));
        oneCostCol.setOnEditCommit(o -> {
            o.getRowValue().setOneCost(o.getNewValue());
            recalcSumCost();
        });

        totalCostCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        totalCostCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoublePriceConverter()));
        totalCostCol.setOnEditCommit(o -> {
            o.getRowValue().setTotalCost(o.getNewValue());
            recalcSumCost();
        });
    }

    private void recalcSumCost() {
        double sum = 0;
        for (Material mat : arrivedMats)
            sum += mat.getTotalCost();

        sumField.textProperty().set(new DoublePriceConverter().toString(sum));
    }
}
