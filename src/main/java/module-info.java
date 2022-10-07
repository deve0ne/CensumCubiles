module com.deveone.censumcubiles {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.deveone.censumcubiles to javafx.fxml;
    exports com.deveone.censumcubiles;
    exports com.deveone.censumcubiles.materialTab.material;
    opens com.deveone.censumcubiles.materialTab.material to javafx.fxml;
    exports com.deveone.censumcubiles.materialTab.material_arrival_dialog;
    opens com.deveone.censumcubiles.materialTab.material_arrival_dialog to javafx.fxml;
    exports com.deveone.censumcubiles.modelTab.model_elements;
    opens com.deveone.censumcubiles.modelTab.model_elements to javafx.fxml;
    exports com.deveone.censumcubiles.modelTab;
    opens com.deveone.censumcubiles.modelTab to javafx.fxml;
    exports com.deveone.censumcubiles.materialTab;
    opens com.deveone.censumcubiles.materialTab to javafx.fxml;
}