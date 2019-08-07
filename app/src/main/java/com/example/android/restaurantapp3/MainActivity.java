package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.RestaurantItem;
import com.example.android.restaurantapp3.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String ORDER_KEY = "order_key";
    List<RestaurantItem> restaurantItemList = SampleDataProvider.restaurantItemList;
    Order order = new Order();
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.sort(restaurantItemList, new Comparator<RestaurantItem>() {
            @Override
            public int compare(RestaurantItem o1, RestaurantItem o2) {
                return o1.getItemName().compareTo(o2.getItemName());
            }
        });

        RestaurantItemAdapter adapter = new RestaurantItemAdapter(restaurantItemList, this);
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order.getItems().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select at least one item.", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, OrderDisplay.class);
                    intent.putExtra(ORDER_KEY, order);
                    startActivity(intent);

                }
            }
        });
    }
}
