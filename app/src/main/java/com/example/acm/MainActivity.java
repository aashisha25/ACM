package com.example.acm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupBottomNavMenu(navController);
        //setupToolbar(navController);
    }

    private void setupBottomNavMenu(NavController navController){
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    private void setupToolbar(NavController navController)
    {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.navigation_home,
                        R.id.navigation_event,
                        R.id.navigation_sponcors,
                        R.id.navigation_team,
                        R.id.navigation_contact_us).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        boolean navigated=NavigationUI.onNavDestinationSelected(item,navController);
        return NavigationUI.onNavDestinationSelected(item,navController)?navigated:
                super.onOptionsItemSelected(item);
    }*/
}