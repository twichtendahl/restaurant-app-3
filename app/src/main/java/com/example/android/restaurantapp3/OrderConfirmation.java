package com.example.android.restaurantapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android.restaurantapp3.model.Payment;

import java.util.Locale;

public class OrderConfirmation extends AppCompatActivity {
    
    Payment payment;
    TextView confirmationCode;
    TextView creditCardConfirm;
    TextView cscConfirm;
    TextView expiryConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        
        // Get payment from OrderDisplay activity
        payment = getIntent().getExtras().getParcelable(OrderDisplay.PAYMENT_KEY);
        
        // Access views
        confirmationCode = findViewById(R.id.confirmationCode);
        creditCardConfirm = findViewById(R.id.creditConfirm);
        cscConfirm = findViewById(R.id.cscConfirm);
        expiryConfirm = findViewById(R.id.expiryConfirm);

        // Populate with data from Payment
        confirmationCode.setText(payment.getConfirmation());
        creditCardConfirm.setText(payment.getCardNumber());
        cscConfirm.setText(payment.getCsc());
        expiryConfirm.setText(String.format("%s/%s", payment.getExpMonth(), payment.getExpYear()));


    }
}
