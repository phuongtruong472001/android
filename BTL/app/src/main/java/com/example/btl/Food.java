package com.example.btl;

public class Food {
    private int image;
    String FoodName;
    String Material;
    String Recipes;
    String Nutrition;

    public Food(int image, String foodName, String material, String recipes, String nutrition) {
        this.image = image;
        FoodName = foodName;
        Material = material;
        Recipes = recipes;
        Nutrition = nutrition;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getRecipes() {
        return Recipes;
    }

    public void setRecipes(String recipes) {
        Recipes = recipes;
    }

    public String getNutrition() {
        return Nutrition;
    }

    public void setNutrition(String nutrition) {
        Nutrition = nutrition;
    }
}
