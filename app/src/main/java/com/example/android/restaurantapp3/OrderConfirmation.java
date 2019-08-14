package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android.restaurantapp3.model.Payment;

public class OrderConfirmation extends AppCompatActivity {
    TextView orderConfirmationCode = findViewById(R.id.confirmationCode);
    TextView creditCardConfirmation = findViewById(R.id.creditConfirm);
    TextView cscConfirmation = findViewById(R.id.cscConfirm);
    TextView expConfirmation = findViewById(R.id.expiryConfirm);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        final Payment payment = getIntent().getExtras().getParcelable(OrderDisplay.PAYMENT_KEY);
        final Payment order = getIntent().getExtras().getParcelable(OrderDisplay.ORDER_KEY);

        /*
        final Order order = getIntent().getExtras().getParcelable(MainActivity.ORDER_KEY);
        List<RestaurantItem> orderedItems = order.getItems();
        DisplayOrderAdapter adapter = new DisplayOrderAdapter(orderedItems, this);
        RecyclerView recyclerView = findViewById(R.id.rvOrderDisplay);
        recyclerView.setAdapter(adapter);
         */
    }
}
