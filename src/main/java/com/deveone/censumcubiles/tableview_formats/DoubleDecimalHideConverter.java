package com.deveone.censumcubiles.tableview_formats;

import javafx.util.converter.DoubleStringConverter;

import java.text.DecimalFormat;

public class DoubleDecimalHideConverter extends DoubleStringConverter {
    @Override
    public Double fromString(String value) {
        // If the specified value is null or zero-length, return null
        if (value == null)
            return null;

        value = value.trim();

        if (value.length() < 1)
            return null;

        value = value.replaceAll(",", ".");
        value = value.replaceAll("[^\\d\\s.]+|\\.(?!\\d)",""); //Убирает всё кроме цифр и точки

        return Double.valueOf(value);
    }

    @Override
    public String toString(Double value) {
        // If the specified value is null, return a zero-length String
        if (value == null)
            return "";

        DecimalFormat format = new DecimalFormat("0.##");

        return format.format(value);
    }
}
