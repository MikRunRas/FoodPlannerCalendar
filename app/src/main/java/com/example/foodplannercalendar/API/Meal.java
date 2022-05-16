package com.example.foodplannercalendar.API;

public class Meal {

    private final int idMeal;
    private final String strMeal;
    private final String strCategory;
    private final String strInstructions;
    private final String strIngredient1;
    private final String strIngredient2;
    private final String strIngredient3;
    private final String strIngredient4;
    private final String strIngredient5;
    private final String strIngredient6;
    private final String strIngredient7;
    private final String strIngredient8;
    private final String strIngredient9;
    private final String strIngredient10;
    private final String strIngredient11;
    private final String strIngredient12;
    private final String strIngredient13;
    private final String strIngredient14;
    private final String strIngredient15;
    private final String strIngredient16;
    private final String strIngredient17;
    private final String strIngredient18;
    private final String strIngredient19;
    private final String strIngredient20;
    private final String strMeasure1;
    private final String strMeasure2;
    private final String strMeasure3;
    private final String strMeasure4;
    private final String strMeasure5;
    private final String strMeasure6;
    private final String strMeasure7;
    private final String strMeasure8;
    private final String strMeasure9;
    private final String strMeasure10;
    private final String strMeasure11;
    private final String strMeasure12;
    private final String strMeasure13;
    private final String strMeasure14;
    private final String strMeasure15;
    private final String strMeasure16;
    private final String strMeasure17;
    private final String strMeasure18;
    private final String strMeasure19;
    private final String strMeasure20;
    private final String strMealThumb;

    public Meal(int idMeal, String strMeal, String strCategory, String strInstructions, String strIngredient1, String strIngredient2, String strIngredient3,
                String strIngredient4, String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8,
                String strIngredient9, String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13,
                String strIngredient14, String strIngredient15, String strIngredient16, String strIngredient17, String strIngredient18,
                String strIngredient19, String strIngredient20, String strMeasure1, String strMeasure2, String strMeasure3, String strMeasure4,
                String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8, String strMeasure9, String strMeasure10, String strMeasure11,
                String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16, String strMeasure17, String strMeasure18,
                String strMeasure19, String strMeasure20, String strMealThumb){
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strInstructions = strInstructions;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
        this.strMealThumb = strMealThumb;
    }

    public int getIdMeal(){return idMeal; }

    public String getName(){ return strMeal; }

    public String getCategory(){ return strCategory; }

    public String getInstructions(){ return strInstructions; }

    public String getImageUrl() { return strMealThumb; }

    public String getAllIngredients(){ return strIngredient1 + " " + strMeasure1 + ", " + strIngredient2 + " " + strMeasure2 + ", " +
                                              strIngredient3 + " " + strMeasure3 + ", " + strIngredient4 + " " + strMeasure4 + ", " +
                                              strIngredient5 + " " + strMeasure5 + ", " + strIngredient6 + " " + strMeasure6 + ", " +
                                              strIngredient7 + " " + strMeasure7 + ", " + strIngredient8 + " " + strMeasure8 + ", " +
                                              strIngredient9 + " " + strMeasure9 + ", " + strIngredient10 + " " + strMeasure10 + ", " +
                                              strIngredient11 + " " + strMeasure11 + ", " + strIngredient12 + " " + strMeasure12 + ", " +
                                              strIngredient13 + " " + strMeasure13 + ", " + strIngredient14 + " " + strMeasure14 + ", " +
                                              strIngredient15 + " " + strMeasure15 + ", " + strIngredient16 + " " + strMeasure16 + ", " +
                                              strIngredient17 + " " + strMeasure17 + ", " + strIngredient18 + " " + strMeasure18 + ", " +
                                              strIngredient19 + " " + strMeasure19 + ", " + strIngredient20 + " " + strMeasure20;}

}
