package com.deveone.censumcubiles.Material;

import java.nio.charset.StandardCharsets;

public enum MaterialCategory {
    NO_CATEGORY ("Без категории"),
    METALWARE ("Метизы"),
    MECHANISM ("Механизмы"),
    WOOD ("Дерево"),
    POROLON ("Поролон"),
    FABRIC ("Ткань");

    private final String ruName;

    MaterialCategory(String ruName) {
        this.ruName = ruName;
    }

    public String getDecodedRuName() { //Абсолютно неясно поведение кодировок строк в связке с mySql
        return new String(ruName.getBytes(StandardCharsets.UTF_8));
    }

    public String getEncodedRuName() {
        return ruName;
    }

    @Override
    public String toString() {
        return ruName;
    }

    public static MaterialCategory getEnum(String value) {
        String decodedValue = new String(value.getBytes(StandardCharsets.UTF_8));
        for(MaterialCategory v : values())
            if (v.getDecodedRuName().equalsIgnoreCase(decodedValue)) return v;
        throw new IllegalArgumentException();
    }
}
