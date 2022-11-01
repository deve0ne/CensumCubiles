package com.deveone.censumcubiles.number_converters;

import javafx.util.converter.NumberStringConverter;

import java.text.DecimalFormat;

public class DecimalHideNumberConverter extends NumberStringConverter {
    @Override
    public Double fromString(String value) {
        // If the specified value is null or zero-length, return null
        if (value == null)
            return null;

        value = value.trim();

        if (value.length() < 1)
            return null;

        value = value.replaceAll(",", ".");
        value = value.replaceAll("[^\\d\\s.]+|\\.(?!\\d)", ""); //Убирает всё кроме цифр и точки

        return Double.valueOf(value);
    }

    @Override
    public String toString(Number value) {
        if (value == null)
            return "";

        DecimalFormat format = new DecimalFormat("0.##");

        return format.format(value);
    }
}
