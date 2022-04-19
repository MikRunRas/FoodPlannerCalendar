package com.example.foodplannercalendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ShoppinglistFragment extends Fragment {

    ArrayList<ShoppingListItem> items = new ArrayList<>();
    ArrayList<ShoppingListItem> boughtItems = new ArrayList<>();
    RecyclerView itemList;
    RecyclerView boughtItemsList;
    FloatingActionButton fab;
    EditText editText1;
    EditText editText2;
    ShoppingListAdapter shoppingListAdapter;
    InBasketListAdapter basketListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shoppinglist, container, false);
        fab = root.findViewById(R.id.FloatingActionButton);
        editText1 = root.findViewById(R.id.EditText);
        editText2 = root.findViewById(R.id.EditText2);

        fab.setOnClickListener(view -> {
            if(editText1.getText().toString().trim().length() <=0)
            {
                Toast.makeText(getContext(), "Please enter a title", Toast.LENGTH_LONG).show();
            }
            else if ((editText1.getText().toString().trim().length() != 0) && (editText2.getText().toString().trim().length() <= 0))
            {
                items.add(new ShoppingListItem(editText1.getText().toString(), 1));
                shoppingListAdapter.notifyDataSetChanged();
            }
            else
            {
                items.add(new ShoppingListItem(editText1.getText().toString(), Integer.parseInt(editText2.getText().toString())));
                shoppingListAdapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        itemList = getView().findViewById(R.id.recyclerview);
        boughtItemsList = getView().findViewById(R.id.recyclerview2);
        itemList.setLayoutManager(new LinearLayoutManager(getContext()));
        boughtItemsList.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList.hasFixedSize();
        boughtItemsList.hasFixedSize();

        items.add(new ShoppingListItem("Eggs", 4));
        items.add(new ShoppingListItem("Milk", 3));
        boughtItems.add(new ShoppingListItem("test", 3));

        shoppingListAdapter = new ShoppingListAdapter(items);
        basketListAdapter = new InBasketListAdapter(boughtItems);

        shoppingListAdapter.setOnClickListener(item -> {
            items.remove(item);
            boughtItems.add(item);
            shoppingListAdapter.notifyDataSetChanged();
            basketListAdapter.notifyDataSetChanged();
        });

        basketListAdapter.setOnClickListener(boughtItem -> {
            boughtItems.remove(boughtItem);
            items.add(boughtItem);
            basketListAdapter.notifyDataSetChanged();
            shoppingListAdapter.notifyDataSetChanged();
        });

        itemList.setAdapter(shoppingListAdapter);
        boughtItemsList.setAdapter(basketListAdapter);

    }
}