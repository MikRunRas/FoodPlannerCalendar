package com.example.foodplannercalendar.shoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingListItemDao {

    @Insert
    void insert(ShoppingListItem shoppingListItem);

    @Query("UPDATE shopping_list_items_table SET isBought = :isBought WHERE ID = :ID")
    void update(int ID, boolean isBought);

    @Delete
    void delete(ShoppingListItem shoppingListItem);

    @Query("DELETE FROM shopping_list_items_table")
    void deleteAllItems();

    @Query("SELECT * FROM shopping_list_items_table")
    LiveData<List<ShoppingListItem>> getAllShoppingListItems();
}
