package com.deveone.censumcubiles.model_tab;

import com.deveone.censumcubiles.database.DBHelper;
import com.deveone.censumcubiles.database.MaterialDBHelper;
import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.model_tab.model_elements.ModelTTVElement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public class AutocompletionTextFieldTTV extends TreeTableCell<ModelTTVElement, String> {
    ArrayList<String> autocompletionList = new ArrayList<>();
    private TextField textField;
    private ObjectProperty<StringConverter<String>> converter =
            new SimpleObjectProperty<>(this, "converter");

    public AutocompletionTextFieldTTV() {
        converter.set(new DefaultStringConverter());

        textField = createTextField();

        List<String> materialList = MaterialDBHelper.getAllMaterials().stream().map(Material::getName).toList();

        System.err.println(materialList);

        autocompletionList.addAll(materialList);

        AutoCompletionBinding<String> autoCompletionBinding =
                TextFields.bindAutoCompletion(textField, autocompletionList);

        autoCompletionBinding.setDelay(10);


        //Баг с экспортом чего-то там. Без понятия, как пофиксить.
//        AutoCompletePopup<String> popup = autoCompletionBinding.getAutoCompletionPopup();

//        AutoCompletePopup<MaterialCategory> popup = autoCompletionBinding.getAutoCompletionPopup();
//        popup.setOnSuggestion(o->{});

        this.setGraphic(textField);
    }

    private TextField createTextField() {
        final TextField textField = new TextField(converter.get().toString(this.getItem()));

        // Use onAction here rather than onKeyReleased (with check for Enter),
        // as otherwise we encounter RT-34685
        textField.setOnAction(event -> {
            this.commitEdit(converter.get().fromString(textField.getText()));
            event.consume();
        });

        textField.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                this.cancelEdit();
                t.consume();
            }
        });
        return textField;
    }

    public final ObjectProperty<StringConverter<String>> converterProperty() {
        return converter;
    }

    @Override
    public void startEdit() {
        if (!isEditable()
                || !getTreeTableView().isEditable()
                || !getTableColumn().isEditable()) {
            return;
        }
        super.startEdit();

        if (isEditing()) {
            if (textField == null) {
                textField = createTextField();
            }

            textField.setText(converter.get().fromString(getItem()));
            setText(null);

            setGraphic(textField);

            textField.selectAll();

            // requesting focus so that key input can immediately go into the
            // TextField (see RT-28132)
            textField.requestFocus();
        }
    }


    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (this.isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(converter.get().toString(item));
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(converter.get().toString(item));
                setGraphic(null);
            }
        }
    }
}
