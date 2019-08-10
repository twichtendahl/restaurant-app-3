package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.RestaurantItem;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import android.widget.EditText;

public class OrderDisplay extends AppCompatActivity {

    Double tip_value = 0.00;

    Button submitOrder;
    TextView subtotal_view;
    TextView tax_view;
    EditText tip_view;
    TextView total_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);

        final Order order = (Order) getIntent().getExtras().getParcelable(MainActivity.ORDER_KEY);
        List<RestaurantItem> orderedItems = order.getItems();
        DisplayOrderAdapter adapter = new DisplayOrderAdapter(orderedItems, this);
        RecyclerView recyclerView = findViewById(R.id.rvOrderDisplay);
        recyclerView.setAdapter(adapter);

        // add order calculations
        subtotal_view = findViewById(R.id.subtotal);
        subtotal_view.setText(String.format("%.2f", order.getTotal(order.getSubtotal())));

        tax_view = findViewById(R.id.tax);
        tax_view.setText(String.format("%.2f", order.getTotal(tip_value)));


        tip_view = (EditText) findViewById(R.id.tip);
        tip_value = Double.parseDouble(tip_view.getText().toString());

        total_view = (TextView) findViewById(R.id.total);
        total_view.setText(String.format("%.2f", order.getTotal(tip_value)));


        tip_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                total_view.setText(Double.toString(order.getTotal(tip_value)));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                total_view.setText(Double.toString(order.getTotal(tip_value)));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tip_view.getText().toString().equals(null)
                        || tip_view.getText().toString().equals(""))
                {
                    tip_value = 0.00;
                }
                else
                {
                    tip_value = Double.parseDouble(tip_view.getText().toString());
                }
 //               total_view.setText(Double.toString(order.getTotal(tip_value)));
                    total_view.setText(String.format("%.2f", order.getTotal(tip_value)));

            }
        });

        submitOrder = findViewById(R.id.finalizeOrder);
//        submitOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
