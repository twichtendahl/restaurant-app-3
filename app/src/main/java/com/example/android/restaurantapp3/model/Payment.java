package com.example.android.restaurantapp3.model;

import java.util.Locale;

public class Payment {
    private Order order;
    private double tax;
    private double tip;
    private int cardNumber;
    private int csc;
    private int expMonth;
    private int expYear;

    public static final double DEFAULT_TAX = 10.5;
    public static final double DEFAULT_TIP = 0.0;

    public Payment(Order order, double tax, double tip, int cardNumber, int csc, int expMonth, int expYear) {
        this.order = order;
        this.tax = tax;
        this.tip = tip;
        this.cardNumber = cardNumber;
        this.csc = csc;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public Payment(Order order, int cardNumber, int csc, int expMonth, int expYear) {
        this(order, DEFAULT_TAX, DEFAULT_TIP, cardNumber, csc, expMonth, expYear);
    }

    public Payment(Order order) {
        this(order, 0, 0, 0, 0);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCsc() {
        return csc;
    }

    public void setCsc(int csc) {
        this.csc = csc;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public double total() {
            return order.getSubtotal() * (1 + (tax/100)) + tip;
    }

    public String formattedTotal() {
        return String.format(Locale.getDefault(),"$%4.2f", total());
    }
}
