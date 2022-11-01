package com.deveone.censumcubiles.material;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;

public enum MaterialCategory {
    METALWARE("Метизы", 1),
    MECHANISM("Механизмы", 2),
    WOOD("Дерево", 3),
    POROLON("Поролон", 4),
    FABRIC("Ткань", 5),
    NO_CATEGORY("Без категории", 6);

    private final String ruName;
    private final int order;

    public static final Comparator<MaterialCategory> categoryComparator = Comparator.comparingInt(o -> o.order);

    MaterialCategory(String ruName, int order) {
        this.ruName = ruName;
        this.order = order;
    }

    //Абсолютно неясно поведение кодировок строк в связке с mySql
    public String getDecodedRuName() {
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

        for (MaterialCategory v : values())
            if (v.getDecodedRuName().equalsIgnoreCase(decodedValue)) return v;

        throw new IllegalArgumentException();
    }
}
