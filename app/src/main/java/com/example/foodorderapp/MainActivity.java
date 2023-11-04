package com.example.foodorderapp;

import static com.example.foodorderapp.R.id.orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel>list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger,"Burger","5","Chicken Burger with extra chese"));
        list.add(new MainModel(R.drawable.pizza,"Pizza","10","Chicken pizza with extra chese"));
        list.add(new MainModel(R.drawable.vada,"VadaPav","15","vadaPav with extra chese"));
        list.add(new MainModel(R.drawable.pavbhaji,"Pavbhaji","30","spicy pavbhaji with extra chese"));
        list.add(new MainModel(R.drawable.burger,"Burger","5","Chicken Burger with extra chese"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.orders) {
            startActivity(
                    new Intent(MainActivity.this, OrderActivity.class)
            );
        }
        return super.onOptionsItemSelected(item);
    }
}