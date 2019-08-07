package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android.restaurantapp3.model.Order;

public class OrderDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);

        Order order = getIntent().getExtras().getParcelable(MainActivity.ORDER_KEY);
        DisplayOrderAdapter adapter = new DisplayOrderAdapter(order, this);
        RecyclerView recyclerView = findViewById(R.id.rvOrderDisplay);
        recyclerView.setAdapter(adapter);
    }
}
