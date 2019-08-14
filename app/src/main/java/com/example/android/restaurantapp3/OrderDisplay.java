package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.OrderItem;
import com.example.android.restaurantapp3.model.Payment;

import java.util.List;

public class OrderDisplay extends AppCompatActivity {

    Order order;
    Payment payment;

    RadioGroup method;
    RadioButton radioButton;
    RadioButton dineIn;
    RadioButton takeOut;
    RadioButton delivery;
    Button next;

    // Key for PaymentActivity object parcel
    public static final String PAYMENT_KEY = "payment_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);

        // Create RecyclerView for items on order
        order = getIntent().getExtras().getParcelable(MainActivity.ORDER_KEY);
        List<OrderItem> orderedItems = order.getItems();
        DisplayOrderAdapter adapter = new DisplayOrderAdapter(orderedItems, this);
        RecyclerView recyclerView = findViewById(R.id.rvOrderDisplay);
        recyclerView.setAdapter(adapter);

        // Create PaymentActivity based on Order
        payment = new Payment(order);

        // Assemble fulfillment method selection views
        method = findViewById(R.id.fulfillmentMethod);
        dineIn = findViewById(R.id.dineIn);
        takeOut = findViewById(R.id.takeOut);
        delivery = findViewById(R.id.delivery);
        next = findViewById(R.id.finalizeOrder);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Discover user's choice of fulfillment method
                int radioId = method.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String methodName = radioButton.getText().toString();
                if (methodName.equals("Dine In")) {
                    payment.setMethod(Payment.DINE_IN);
                } else if (methodName.equals("Take-out")) {
                    payment.setMethod(Payment.TAKE_OUT);
                } else {
                    payment.setMethod(Payment.DELIVERY);
                }

                Intent intent = new Intent(OrderDisplay.this, PaymentActivity.class);
                intent.putExtra(PAYMENT_KEY, payment);
                startActivity(intent);

            }
        });
    }
}
