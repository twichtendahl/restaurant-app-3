package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.RestaurantItem;

import java.util.Iterator;
import java.util.List;

public class OrderDisplay extends AppCompatActivity {

    Button submitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);

        Order order = getIntent().getExtras().getParcelable(MainActivity.ORDER_KEY);
        List<RestaurantItem> orderedItems = order.getItems();
        DisplayOrderAdapter adapter = new DisplayOrderAdapter(orderedItems, this);
        RecyclerView recyclerView = findViewById(R.id.rvOrderDisplay);
        recyclerView.setAdapter(adapter);

        submitOrder = findViewById(R.id.finalizeOrder);
//        submitOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
