package com.example.foodplannercalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.example.foodplannercalendar.WeeklyMealPlan.WeeklyMealPlanFragment;
import com.example.foodplannercalendar.shoppinglist.InBasketListAdapter;
import com.example.foodplannercalendar.shoppinglist.ShoppingListAdapter;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItem;
import com.example.foodplannercalendar.shoppinglist.ShoppinglistFragment;
import com.example.foodplannercalendar.shoppinglist.ShoppinglistFragmentViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ShoppingListItem> items = new ArrayList<>();
    private ArrayList<ShoppingListItem> boughtItems = new ArrayList<>();
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private ShoppinglistFragmentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupNavigation();
    }

    private void findViews()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        toolbar = findViewById(R.id.toolbar);
        mAuth = FirebaseAuth.getInstance();
        viewModel = new ViewModelProvider(this).get(ShoppinglistFragmentViewModel.class);
    }

    private void setupNavigation()
    {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_calendar,
                R.id.nav_shoppinglist)
                .build();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        setBottomNavigationVisibility();
    }

    private void setBottomNavigationVisibility() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            final int id = destination.getId();
            if (id == R.id.nav_home || id == R.id.nav_calendar || id == R.id.nav_shoppinglist) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            final int id = destination.getId();
            if (id == R.id.nav_shoppinglist) {
                menu.findItem(R.id.nav_clear_list).setVisible(true);
            } else {
                menu.findItem(R.id.nav_clear_list).setVisible(false);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null)
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void logOut(MenuItem item) {
        mAuth.signOut();
        startActivity( new Intent(MainActivity.this, LoginActivity.class));
    }

    public void ClearList(MenuItem item)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirm delete");
        builder.setMessage("Do you really want to empty your shoppinglist?").
                setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.deleteAllShoppingListItems();
            }
        }).setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}