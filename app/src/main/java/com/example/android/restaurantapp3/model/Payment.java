package com.example.android.restaurantapp3.model;

public class Payment {
    private int cardNumber;
    private int csc;
    private int expMonth;
    private int expYear;

    public Payment(int cardNumber, int csc, int expMonth, int expYear) {
        this.cardNumber = cardNumber;
        this.csc = csc;
        this.expMonth = expMonth;

        if(expYear > 99) {
            this.expYear = expYear;
        } else {
            this.expYear = 2000 + expYear;
        }
    }


}
