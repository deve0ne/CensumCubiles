package com.deveone.censumcubiles.number_converters;

/**
 * Конвертер для валютных полей. Так же как и DecimalHideConverter скрывает дробную часть, если она = 0.
 */

public class PriceConverter extends DecimalHideConverter {
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
