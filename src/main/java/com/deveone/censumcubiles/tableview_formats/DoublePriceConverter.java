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
        // If the specified value is null, return a zero-length String
        if (value == null)
            return "";

        DecimalFormat format = new DecimalFormat("0.##");

        return format.format(value) + " \u20BD";
    }
}
