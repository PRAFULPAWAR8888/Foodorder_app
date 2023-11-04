package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type",0)==1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");
            final String foodname = getIntent().getStringExtra("food");
            binding.detailImage.setImageResource(image);
            binding.priceDenoter.setText(String.format("%d", price));
            binding.nameBox.setText(name);
            binding.detailDescription.setText(description);
            binding.foodname.setText(foodname);



            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            foodname,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            });
        }else{
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor= helper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceDenoter.setText(String.format("%d", cursor.getInt(3)));
            binding.foodname.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.button.setText("Update Now");
            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   boolean isUpdated= helper.updateOrder(
                           binding.nameBox.getText().toString(),
                           binding.phoneBox.getText().toString(),
                           Integer.parseInt(binding.priceDenoter.getText().toString()),
                           image,
                           binding.detailDescription.getText().toString(),
                           binding.foodname.getText().toString(),
                           1,
                           id
                   ) ;
                   if(isUpdated)
                       Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(DetailActivity.this, "Failed To Update", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}