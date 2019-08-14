package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.OrderItem;
import com.example.android.restaurantapp3.model.Payment;
import com.example.android.restaurantapp3.model.RestaurantItem;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class OrderDisplay extends AppCompatActivity {

    Order order;
    Payment payment;

    // Subtotal, tip, tax, and total widgets
    Button submitOrder;
    EditText tipEntry;
    TextView subTotal;
    TextView orderTotal;

    // Credit Card, CSC, and expiration widgets
    EditText creditCard;
    EditText csc;
    EditText expMonth;
    EditText expYear;

    // Key for Payment object parcel
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

        // Create Payment based on Order
        payment = new Payment(order);

        // Initialize order subtotal and total
        subTotal = findViewById(R.id.subtotal);
        subTotal.setText(String.format(Locale.getDefault(), "$%4.2f", order.getSubtotal()));
        orderTotal = findViewById(R.id.total);
        orderTotal.setText(payment.formattedTotal());

        // Create onChanged method for tip entry
        tipEntry = findViewById(R.id.tip);
        tipEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                payment.setTip(Double.parseDouble(s.toString()));
            }
        });

        // Initialize payment method widgets
        // Create on-click for submitting order
        creditCard = findViewById(R.id.creditCard);
        csc = findViewById(R.id.csc);
        expMonth = findViewById(R.id.month);
        expYear = findViewById(R.id.year);
        submitOrder = findViewById(R.id.finalizeOrder);
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment.setCardNumber(creditCard.getText().toString());
                payment.setCsc(csc.getText().toString());
                payment.setExpMonth(expMonth.getText().toString());
                payment.setExpYear(expYear.getText().toString());
                payment.setConfirmation(UUID.randomUUID().toString());

                Intent intent = new Intent(OrderDisplay.this, OrderConfirmation.class);
                intent.putExtra(PAYMENT_KEY, payment);
                startActivity(intent);
            }
        });
    }
}
