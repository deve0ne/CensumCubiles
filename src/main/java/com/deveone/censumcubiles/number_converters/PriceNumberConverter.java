package com.deveone.censumcubiles.number_converters;

public class PriceNumberConverter extends DecimalHideNumberConverter {
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
    public String toString(Number value) {
        return super.toString(value) + " \u20BD";
    }
}
