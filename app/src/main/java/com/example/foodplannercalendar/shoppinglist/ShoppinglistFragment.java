package com.example.foodplannercalendar.shoppinglist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foodplannercalendar.R;
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
    public ShoppingListAdapter shoppingListAdapter;
    public InBasketListAdapter basketListAdapter;
    private ShoppinglistFragmentViewModel viewModel;
    private LinearLayout shoppingListLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ShoppinglistFragmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shoppinglist, container, false);
        findViews(root);

        viewModel.getAllShoppingListItems().observe(getViewLifecycleOwner(), tempItems-> {
            if(!tempItems.isEmpty()) {
                items.clear();
                boughtItems.clear();
                    for (ShoppingListItem i : tempItems) {
                        if(i.getIsBought() == false) {
                            items.add(i);
                        }
                        else if(i.getIsBought() == true){
                            boughtItems.add(i);
                        }
                    }
                    shoppingListAdapter.notifyDataSetChanged();
                    basketListAdapter.notifyDataSetChanged();
                }
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
        return root;
    }

    private void findViews(View root) {
        shoppingListLayout = root.findViewById(R.id.shoppingListLayout);
        fab = root.findViewById(R.id.FloatingActionButton);
        editText1 = root.findViewById(R.id.EditText);
        editText2 = root.findViewById(R.id.EditText2);
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
            viewModel.delete(item);
            viewModel.insert(item);
            shoppingListAdapter.notifyDataSetChanged();
            basketListAdapter.notifyDataSetChanged();
        });

        basketListAdapter.setOnClickListener(boughtItem -> {
            boughtItems.remove(boughtItem);
            boughtItem.setIsBought(boughtItem);
            items.add(boughtItem);
            viewModel.delete(boughtItem);
            viewModel.insert(boughtItem);
            basketListAdapter.notifyDataSetChanged();
            shoppingListAdapter.notifyDataSetChanged();
        });
        itemList.setAdapter(shoppingListAdapter);
        boughtItemsList.setAdapter(basketListAdapter);
    }

    public void clearLists(){
        ShoppinglistFragment shoppinglistFragment = new ShoppinglistFragment();
        shoppinglistFragment.items.clear();
        boughtItems.clear();
    }
}