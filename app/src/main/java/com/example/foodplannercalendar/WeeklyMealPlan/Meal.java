package com.example.foodplannercalendar.WeeklyMealPlan;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "meals_table")
public class Meal {

    public static ArrayList<Meal> mealsForDate(String date, ArrayList<Meal> mealsList){
        ArrayList<Meal> meals = new ArrayList<>();

        for (Meal meal : mealsList){
            if(meal.getDate().equals(date)){
                meals.add(meal);
            }
        }
        return meals;
    }

    @PrimaryKey(autoGenerate = true)
    private final int idMeal;
    private final String date;
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

    public Meal(int idMeal, String date, String strMeal, String strCategory, String strInstructions, String strIngredient1, String strIngredient2, String strIngredient3,
                String strIngredient4, String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8,
                String strIngredient9, String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13,
                String strIngredient14, String strIngredient15, String strIngredient16, String strIngredient17, String strIngredient18,
                String strIngredient19, String strIngredient20, String strMeasure1, String strMeasure2, String strMeasure3, String strMeasure4,
                String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8, String strMeasure9, String strMeasure10, String strMeasure11,
                String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16, String strMeasure17, String strMeasure18,
                String strMeasure19, String strMeasure20, String strMealThumb){
        this.idMeal = idMeal;
        this.date = date;
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

    public String getDate(){ return date; }

    public String getStrMeal(){ return strMeal; }

    public String strCategory(){ return strCategory; }

    public String strInstructions(){ return strInstructions; }

    public String strMealThumb() { return strMealThumb; }

    public String getStrIngredient1() {return strIngredient1; }

    public String getStrIngredient2() {return strIngredient2; }

    public String getStrIngredient3() {return strIngredient3; }

    public String getStrIngredient4() {return strIngredient4; }

    public String getStrIngredient5() {return strIngredient5; }

    public String getStrIngredient6() {return strIngredient6; }

    public String getStrIngredient7() {return strIngredient7; }

    public String getStrIngredient8() {return strIngredient8; }

    public String getStrIngredient9() {return strIngredient9; }

    public String getStrIngredient10() {return strIngredient10; }

    public String getStrIngredient11() {return strIngredient11; }

    public String getStrIngredient12() {return strIngredient12; }

    public String getStrIngredient13() {return strIngredient13; }

    public String getStrIngredient14() {return strIngredient14; }

    public String getStrIngredient15() {return strIngredient15; }

    public String getStrIngredient16() {return strIngredient16; }

    public String getStrIngredient17() {return strIngredient17; }

    public String getStrIngredient18() {return strIngredient18; }

    public String getStrIngredient19() {return strIngredient19; }

    public String getStrIngredient20() {return strIngredient20; }

    public String getStrMeasure1() {return strMeasure1; }

    public String getStrMeasure2() {return strMeasure2; }

    public String getStrMeasure3() {return strMeasure3; }

    public String getStrMeasure4() {return strMeasure4; }

    public String getStrMeasure5() {return strMeasure5; }

    public String getStrMeasure6() {return strMeasure6; }

    public String getStrMeasure7() {return strMeasure7; }

    public String getStrMeasure8() {return strMeasure8; }

    public String getStrMeasure9() {return strMeasure9; }

    public String getStrMeasure10() {return strMeasure10; }

    public String getStrMeasure11() {return strMeasure11; }

    public String getStrMeasure12() {return strMeasure12; }

    public String getStrMeasure13() {return strMeasure13; }

    public String getStrMeasure14() {return strMeasure14; }

    public String getStrMeasure15() {return strMeasure15; }

    public String getStrMeasure16() {return strMeasure16; }

    public String getStrMeasure17() {return strMeasure17; }

    public String getStrMeasure18() {return strMeasure18; }

    public String getStrMeasure19() {return strMeasure19; }

    public String getStrMeasure20() {return strMeasure20; }

    public String getAllIngredients() {
        ArrayList<String> ingredients = new ArrayList<String>();

        ingredients.add(strIngredient1);
        ingredients.add(strMeasure1);
        ingredients.add(strIngredient2);
        ingredients.add(strMeasure2);
        ingredients.add(strIngredient3);
        ingredients.add(strMeasure3);
        ingredients.add(strIngredient4);
        ingredients.add(strMeasure4);
        ingredients.add(strIngredient5);
        ingredients.add(strMeasure5);
        ingredients.add(strIngredient6);
        ingredients.add(strMeasure6);
        ingredients.add(strIngredient7);
        ingredients.add(strMeasure7);
        ingredients.add(strIngredient8);
        ingredients.add(strMeasure8);
        ingredients.add(strIngredient9);
        ingredients.add(strMeasure9);
        ingredients.add(strIngredient10);
        ingredients.add(strMeasure10);
        ingredients.add(strIngredient11);
        ingredients.add(strMeasure11);
        ingredients.add(strIngredient12);
        ingredients.add(strMeasure12);
        ingredients.add(strIngredient13);
        ingredients.add(strMeasure13);
        ingredients.add(strIngredient14);
        ingredients.add(strMeasure14);
        ingredients.add(strIngredient15);
        ingredients.add(strMeasure15);
        ingredients.add(strIngredient16);
        ingredients.add(strMeasure16);
        ingredients.add(strIngredient17);
        ingredients.add(strMeasure17);
        ingredients.add(strIngredient18);
        ingredients.add(strMeasure18);
        ingredients.add(strIngredient19);
        ingredients.add(strMeasure19);
        ingredients.add(strIngredient20);
        ingredients.add(strMeasure20);

        String allIngredients = "";
        int counter = 0;
        for (String s : ingredients) {
            if (s.length() > 0) {
                if(counter % 2 == 0) {
                    allIngredients = allIngredients + s + " ";
                    counter++;
                }
                else{
                    if (counter % 2 == 1) {
                        allIngredients = allIngredients + s + "\n";
                        counter++;
                    }
                }
            }
        }
        return allIngredients;
    }
}
