package com.example.foodplannercalendar;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_list_items_table")
public class ShoppingListItem {

    @PrimaryKey(autoGenerate = true)
    public int ID;
    private String title;
    private int amount;
    private boolean isBought;

    public ShoppingListItem(String title, int amount, boolean isBought){
        this.title = title;
        this.amount = amount;
        this.isBought = isBought;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIsBought(){ return isBought; }

    public void setIsBought(ShoppingListItem item)
    {
        if(item.isBought == false) {
            item.isBought = true;
        }
        else if (item.isBought == true){
            item.isBought = false;
        }
    }
}
