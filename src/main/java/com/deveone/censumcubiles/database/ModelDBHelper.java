package com.deveone.censumcubiles.database;

import com.deveone.censumcubiles.material.Material;
import com.deveone.censumcubiles.material.MaterialCategory;
import com.deveone.censumcubiles.model.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ModelDBHelper extends DBHelper {
    public static ArrayList<Model> getAllModels() {
        ArrayList<Model> models = new ArrayList<>();

        try (Connection conn = getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM censumcubilesdb.model_materials" +
                    " INNER JOIN materials ON model_materials.mat_name = materials.mat_name;");

            while (resultSet.next()) {
                String modelName = resultSet.getString(1);
                String matName = resultSet.getString(2);
                double amount = resultSet.getDouble(3);
                String category = resultSet.getString(5);
                double oneCost = resultSet.getDouble(7);

                Model model = new Model(modelName);
                if (models.contains(model))
                        model = models.get(models.indexOf(model));

                Material newMat = new Material(matName, MaterialCategory.getEnum(category), amount, oneCost);

                model.addMaterial(newMat);

                //Я не уверен, нужно ли это вообще, но пока не до этого
                models.remove(model);
                models.add(model);
            }
        } catch (Exception ex) {
            printError(ex);
        }

        return models;
    }
}
