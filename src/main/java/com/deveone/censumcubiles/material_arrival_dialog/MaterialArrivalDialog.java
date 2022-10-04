package com.deveone.censumcubiles.material_arrival_dialog;

import com.deveone.censumcubiles.material.Material;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MaterialArrivalDialog extends Stage {
//    VBox topElementsVBox;
//    VBox bottomElementsVBox;
//
//    TableView<Material> arrivalTable;
//
//    HBox arrivalDateBox;
//    Button applyButton;
//
//    ButtonBar buttonBar;
//    Button addRowButton;
//    Button delRowButton;


    public MaterialArrivalDialog() throws IOException {
//        createTopElements();
//        createTableView();
//        createBottomElements();
//
//        VBox vBox = new VBox();
//        vBox.alignmentProperty().set(Pos.CENTER);
//        vBox.setPadding(new Insets(10));
//        vBox.getChildren().addAll(arrivalTable, bottomElementsVBox);
//
//        Scene myDialogScene = new Scene(vBox);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("arrival-dialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.setScene(scene);

        this.initModality(Modality.APPLICATION_MODAL);
//        System.err.println();
//        this.setMinWidth(scene.widthProperty().get());
//        this.setMinHeight(scene.getHeight());

        this.show();
    }

//    private void createTopElements() {
//        arrivalDateBox = new HBox();
//        Label dateLabel = new Label("Дата операции");
//        DatePicker datePicker = new DatePicker();
//        arrivalDateBox.getChildren().addAll(dateLabel, datePicker);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void createTableView() {
//        arrivalTable = new TableView<>();
//
////        TableColumn<Material, String> matCategory = new TableColumn<>("Категория");
//        TableColumn<Material, String> matName = new TableColumn<>("Название");
//        TableColumn<Material, Double> matAmount = new TableColumn<>("Количество");
//
//        TableColumn<Material, Double> matCost = new TableColumn<>("Цена");
//        TableColumn<Material, Double> matOneCost = new TableColumn<>("Цена за ед.");
//        TableColumn<Material, Double> matTotalCost = new TableColumn<>("Цена общая");
//
//        matCost.getColumns().addAll(matOneCost, matTotalCost);
//        arrivalTable.getColumns().addAll(matName, matAmount, matCost);
//    }
//
//    private void createBottomElements() {
//        addRowButton = new Button("+ Добавить строку");
////        addRowButton.
////        delRowButton = new Button("- Удалить строку");
//
//        buttonBar = new ButtonBar();
//        buttonBar.getButtons().addAll(addRowButton, delRowButton);
//
//        applyButton = new Button("Внести");
//        applyButton.setOnAction(arg0 -> this.close());
//
//        bottomElementsVBox = new VBox();
//        bottomElementsVBox.setAlignment(Pos.CENTER);
//        bottomElementsVBox.getChildren().addAll(buttonBar, applyButton);
//    }
}
