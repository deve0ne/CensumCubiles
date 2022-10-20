package com.deveone.censumcubiles.tableview_formats;

import javafx.util.converter.DoubleStringConverter;

import java.text.DecimalFormat;

public class DoublePriceConverter extends DoubleDecimalHideConverter {
    @Override
    public Double fromString(String value) {
        if (value == null)
            return null;

        value = value.trim();

        if (value.length() < 1)
            return null;

        value = value.replaceAll("\u20BD", "");

        return super.fromString(value);
    }

    @Override
    public String toString(Double value) {
        return super.toString(value) + " \u20BD";
    }
}
