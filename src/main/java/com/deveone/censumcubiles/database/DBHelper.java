package com.deveone.censumcubiles.database;

import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;

import java.sql.*;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;


public class DBHelper {

    public static Material getMaterialByName(String materialName) {
        String sql = ("SELECT * FROM censumcubilesdb.materials WHERE MatName = ?");

        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, materialName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String category = resultSet.getString(2);
                String matName = resultSet.getString(3);
                double amount = resultSet.getDouble(4);
                double oneCost = resultSet.getDouble(5);
                return new Material(id, MaterialCategory.getEnum(category), matName, amount, oneCost);
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
                int id = resultSet.getInt(1);
                String category = resultSet.getString(2);
                String matName = resultSet.getString(3);
                double amount = resultSet.getDouble(4);
                double oneCost = resultSet.getDouble(5);
                Material mat = new Material(id, MaterialCategory.getEnum(category), matName, amount, oneCost);
                materials.add(mat);
            }
        } catch (Exception ex) {
            printError(ex);
        }

        return materials;
    }

    public static void addMaterial(Material material) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO censumcubilesdb.materials(Category, MatName, Amount, OneCost) Values (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, material.getCategory().getEncodedRuName());
                preparedStatement.setString(2, material.getName());
                preparedStatement.setDouble(3, material.getAmount());
                preparedStatement.setDouble(4, material.getOneCost());

                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next())
                    material.setId(Integer.parseInt(rs.getString(1))); //Для корректного отображения ID
            }
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public static void removeMaterial(Material material) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM censumcubilesdb.materials WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, material.getId());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public static void changeMaterial(Material newMaterial) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE censumcubilesdb.materials SET Category=?, MatName=?, Amount=?, OneCost=? WHERE Id=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, newMaterial.getCategory().getEncodedRuName());
                preparedStatement.setString(2, newMaterial.getName());
                preparedStatement.setDouble(3, newMaterial.getAmount());
                preparedStatement.setDouble(4, newMaterial.getOneCost());
                preparedStatement.setInt(5, newMaterial.getId());

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


