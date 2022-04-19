package com.example.foodplannercalendar;

public class ShoppingListItem {

    private String title;
    private int amount;

    public ShoppingListItem(String title, int amount){
        this.title = title;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }
}
