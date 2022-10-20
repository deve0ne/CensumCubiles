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
    exports com.deveone.censumcubiles.material_tab.material;
    opens com.deveone.censumcubiles.material_tab.material to javafx.fxml;
    exports com.deveone.censumcubiles.material_tab.material_arrival_dialog;
    opens com.deveone.censumcubiles.material_tab.material_arrival_dialog to javafx.fxml;
    exports com.deveone.censumcubiles.model_tab.model_elements;
    opens com.deveone.censumcubiles.model_tab.model_elements to javafx.fxml;
    exports com.deveone.censumcubiles.model_tab;
    opens com.deveone.censumcubiles.model_tab to javafx.fxml;
    exports com.deveone.censumcubiles.material_tab;
    opens com.deveone.censumcubiles.material_tab to javafx.fxml;
}