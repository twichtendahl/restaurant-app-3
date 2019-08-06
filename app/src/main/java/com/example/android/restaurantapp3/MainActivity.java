package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.RestaurantItem;
import com.example.android.restaurantapp3.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<RestaurantItem> restaurantItemList = SampleDataProvider.restaurantItemList;
    Order order = new Order();

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
    }
}
