package com.example.foodplannercalendar;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ShoppinglistFragment extends Fragment {

    private ArrayList<ShoppingListItem> items = new ArrayList<>();
    private ArrayList<ShoppingListItem> boughtItems = new ArrayList<>();
    private RecyclerView itemList;
    private RecyclerView boughtItemsList;
    private FloatingActionButton fab;
    private FloatingActionButton fabd;
    private EditText editText1;
    private EditText editText2;
    private ShoppingListAdapter shoppingListAdapter;
    private InBasketListAdapter basketListAdapter;
    private ShoppinglistFragmentViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ShoppinglistFragmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shoppinglist, container, false);

        fab = root.findViewById(R.id.FloatingActionButton);
        fabd = root.findViewById(R.id.FloatingActionButtond);
        editText1 = root.findViewById(R.id.EditText);
        editText2 = root.findViewById(R.id.EditText2);

        viewModel.getAllShoppingListItems().observe(this, tempItems-> {
            if(!tempItems.isEmpty()){
                for (ShoppingListItem i : tempItems) {
                    if (i.getIsBought() == false) {
                        items.add(new ShoppingListItem(i.getTitle(), i.getAmount(), i.getIsBought()));
                    } else if (i.getIsBought() == true) {
                        boughtItems.add(new ShoppingListItem(i.getTitle(), i.getAmount(), i.getIsBought()));
                    }
                }
            } else {

            }
            shoppingListAdapter.notifyDataSetChanged();
        });

        fab.setOnClickListener(view -> {
            if(editText1.getText().toString().trim().length() <=0)
            {
                Toast.makeText(getContext(), "Please enter a title", Toast.LENGTH_LONG).show();
            }
            else if ((editText1.getText().toString().trim().length() != 0) && (editText2.getText().toString().trim().length() <= 0))
            {
                viewModel.insert(new ShoppingListItem(editText1.getText().toString(), 1, false));
                editText1.setText("");
                editText2.setText("");
            }
            else
            {
                viewModel.insert(new ShoppingListItem(editText1.getText().toString(), Integer.parseInt(editText2.getText().toString()), false));
                editText1.setText("");
                editText2.setText("");
            }
        });

        fabd.setOnClickListener(view ->{
            viewModel.deleteAllShoppingListItems();
            shoppingListAdapter.notifyDataSetChanged();
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

        shoppingListAdapter = new ShoppingListAdapter(items);
        basketListAdapter = new InBasketListAdapter(boughtItems);


        shoppingListAdapter.setOnClickListener(item -> {
            items.remove(item);
            item.setIsBought(item);
            boughtItems.add(item);
            viewModel.update(item.ID, item.getIsBought());
            Log.d("ISBOUGHT", "IsBought value: " + item.getIsBought());
            shoppingListAdapter.notifyDataSetChanged();
            basketListAdapter.notifyDataSetChanged();
        });

        basketListAdapter.setOnClickListener(boughtItem -> {
            boughtItems.remove(boughtItem);
            boughtItem.setIsBought(boughtItem);
            items.add(boughtItem);
            viewModel.update(boughtItem.ID, boughtItem.getIsBought());
            Log.d("ISNULL", "IsBought value: " + boughtItem.getIsBought());
            viewModel.update(boughtItem.ID, boughtItem.getIsBought());
            basketListAdapter.notifyDataSetChanged();
            shoppingListAdapter.notifyDataSetChanged();
        });
        itemList.setAdapter(shoppingListAdapter);
        boughtItemsList.setAdapter(basketListAdapter);
    }

    public void clearLists(){
        items.clear();
        boughtItems.clear();
        basketListAdapter.notifyDataSetChanged();
        shoppingListAdapter.notifyDataSetChanged();
    }
}