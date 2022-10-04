package com.deveone.censumcubiles.material_arrival_dialog;

import com.deveone.censumcubiles.CensumCubilesApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MaterialArrivalDialog extends Stage {
    public MaterialArrivalDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CensumCubilesApp.class.getResource("arrival-dialog.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        setScene(scene);

        setIconified(false);
        setTitle("Приход");

        initModality(Modality.APPLICATION_MODAL);
        show();
    }
}
