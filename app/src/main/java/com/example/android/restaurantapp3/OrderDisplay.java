package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.Payment;
import com.example.android.restaurantapp3.model.RestaurantItem;

import java.util.Iterator;
import java.util.List;

public class OrderDisplay extends AppCompatActivity {
    public static final String PAYMENT_KEY = "payment_key";
    public static final String ORDER_KEY = "order_key";


    Button submitOrder;
    EditText creditCard;
    EditText csc;
    EditText expMonth;
    EditText expYear;
    Payment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);

        final Order order = getIntent().getExtras().getParcelable(MainActivity.ORDER_KEY);
        List<RestaurantItem> orderedItems = order.getItems();
        DisplayOrderAdapter adapter = new DisplayOrderAdapter(orderedItems, this);
        RecyclerView recyclerView = findViewById(R.id.rvOrderDisplay);
        recyclerView.setAdapter(adapter);

        submitOrder = findViewById(R.id.finalizeOrder);
        creditCard = findViewById(R.id.creditCard);
        csc = findViewById(R.id.csc);
        expMonth = findViewById(R.id.month);
        expYear = findViewById(R.id.year);
        payment = new Payment();

        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(creditCard.getText() == null ||
                csc.getText() == null ||
                expMonth == null ||
                expYear == null) {
                    Toast.makeText(OrderDisplay.this, "Please input all required data.", Toast.LENGTH_LONG).show();
                } else {
                    payment.setCardNumber(Integer.parseInt(creditCard.getText().toString()));
                    payment.setCsc(Integer.parseInt(csc.getText().toString()));
                    payment.setExpMonth(Integer.parseInt(expMonth.getText().toString()));
                    payment.setExpYear(Integer.parseInt(expYear.getText().toString()));

                    Intent intent = new Intent(OrderDisplay.this, OrderConfirmation.class);
                    Bundle extras = new Bundle();
                    extras.putParcelable(PAYMENT_KEY, payment);
                    extras.putParcelable(ORDER_KEY, order);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }
}
