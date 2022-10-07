package com.deveone.censumcubiles.database;

import com.deveone.censumcubiles.materialTab.material.Material;
import com.deveone.censumcubiles.materialTab.material.MaterialCategory;

import java.sql.*;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;


public class DBHelper {
    public static Material getMaterialByName(String materialName) {
        String sql = ("SELECT * FROM censumcubilesdb.materials WHERE mat_name = ?");

        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, materialName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String matName = resultSet.getString(1);
                String category = resultSet.getString(2);
                double amount = resultSet.getDouble(3);
                double oneCost = resultSet.getDouble(4);
                return new Material(matName, MaterialCategory.getEnum(category), amount, oneCost);
            }
        } catch (Exception ex) {
            printError(ex);
        }

        return null;
    }

    public static ArrayList<Material> getAllMaterials() {
        ArrayList<Material> materials = new ArrayList<>();

        try (Connection conn = getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM censumcubilesdb.materials");

            while (resultSet.next()) {
                String matName = resultSet.getString(1);
                String category = resultSet.getString(2);
                double amount = resultSet.getDouble(3);
                double oneCost = resultSet.getDouble(4);
                Material mat = new Material(matName, MaterialCategory.getEnum(category), amount, oneCost);
                materials.add(mat);
            }
        } catch (Exception ex) {
            printError(ex);
        }

        return materials;
    }

    public static void addMaterial(Material material) {
        // TODO: 07.10.2022 добавить проверку на существование материала
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO censumcubilesdb.materials(mat_name, category, amount, cost) Values (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql /*, Statement.RETURN_GENERATED_KEYS*/)) {
                preparedStatement.setString(1, material.getName());
                preparedStatement.setString(2, material.getCategory().getEncodedRuName());
                preparedStatement.setDouble(3, material.getAmount());
                preparedStatement.setDouble(4, material.getOneCost());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public static void removeMaterial(Material material) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM censumcubilesdb.materials WHERE mat_name = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, material.getName());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public static void changeMaterial(String oldName, Material newMaterial) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE censumcubilesdb.materials SET mat_name=?, category=?, amount=?, cost=? WHERE mat_name=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, newMaterial.getName());
                preparedStatement.setString(2, newMaterial.getCategory().getEncodedRuName());
                preparedStatement.setDouble(3, newMaterial.getAmount());
                preparedStatement.setDouble(4, newMaterial.getOneCost());
                preparedStatement.setString(5, oldName);

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            printError(ex);
        }
    }

    private static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException e) {
            System.err.println("database.properties not found :O");
        }

        String url = props.getProperty("url");
        return DriverManager.getConnection(url, props);
    }

    private static void printError(Exception ex) {
        System.err.println("Connection to DB failed :(\n" + ex);
    }
}


