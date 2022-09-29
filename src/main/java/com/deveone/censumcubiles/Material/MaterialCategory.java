package com.deveone.censumcubiles.Material;

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

    public String getRuName() {
        return ruName;
    }

    @Override
    public String toString() {
        return this.ruName;
    }
}
