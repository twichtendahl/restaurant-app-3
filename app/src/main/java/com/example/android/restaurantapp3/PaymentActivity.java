package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.restaurantapp3.model.Payment;

import java.util.Locale;
import java.util.UUID;

public class PaymentActivity extends AppCompatActivity {
    Payment payment;

    // Subtotal, tip, tax, and total widgets
    Button submitOrder;
    EditText tipEntry;
    TextView tax;
    TextView subTotal;
    TextView orderTotal;

    // Credit Card, CSC, and expiration widgets
    EditText creditCard;
    EditText csc;
    EditText expMonth;
    EditText expYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment = getIntent().getExtras().getParcelable(OrderDisplay.PAYMENT_KEY);

        // Initialize order subtotal and total
        subTotal = findViewById(R.id.subtotal);
        subTotal.setText(String.format(Locale.getDefault(), "$%4.2f", payment.getOrder().getSubtotal()));
        tax = findViewById(R.id.tax);
        tax.setText(payment.formattedTaxAmount());
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

        // Handle payment input
        creditCard = findViewById(R.id.creditCard);
        csc = findViewById(R.id.csc);
        expMonth = findViewById(R.id.month);
        expYear = findViewById(R.id.year);
        submitOrder = findViewById(R.id.confirmationButton);
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment.setCardNumber(creditCard.getText().toString());
                payment.setCsc(csc.getText().toString());
                payment.setExpMonth(expMonth.getText().toString());
                payment.setExpYear(expYear.getText().toString());
                payment.setConfirmation(UUID.randomUUID().toString());

                // Send payment data to separate app
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(OrderDisplay.PAYMENT_KEY, payment);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

    }
}
