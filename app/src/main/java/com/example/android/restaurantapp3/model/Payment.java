package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Payment implements Parcelable {
    private Order order;
    private double tax;
    private double tip;
    private String cardNumber;
    private String csc;
    private String expMonth;
    private String expYear;
    private String confirmation;

    public static final double DEFAULT_TAX = 10.5;
    public static final double DEFAULT_TIP = 0.0;
    public static final String NOT_CONFIRMED = "Not Confirmed";
    public static final String NO_CARD = "No Credit Card";
    public static final String NO_CSC = "No CSC";
    public static final String NO_MONTH = "No Month";
    public static final String NO_YEAR = "No Year";

    public Payment(Order order, double tax, double tip, String cardNumber, String csc, String expMonth, String expYear) {
        this.order = order;
        this.tax = tax;
        this.tip = tip;
        this.cardNumber = cardNumber;
        this.csc = csc;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.confirmation = NOT_CONFIRMED;
    }

    public Payment(Order order, String cardNumber, String csc, String expMonth, String expYear) {
        this(order, DEFAULT_TAX, DEFAULT_TIP, cardNumber, csc, expMonth, expYear);
    }

    public Payment(Order order) {
        this(order, NO_CARD, NO_CSC, NO_MONTH, NO_YEAR);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCsc() {
        return csc;
    }

    public void setCsc(String csc) {
        this.csc = csc;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public double total() {
            return order.getSubtotal() * (1 + (tax/100)) + tip;
    }

    public String formattedTotal() {
        return String.format(Locale.getDefault(),"$%4.2f", total());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.order, flags);
        dest.writeDouble(this.tax);
        dest.writeDouble(this.tip);
        dest.writeString(this.cardNumber);
        dest.writeString(this.csc);
        dest.writeString(this.expMonth);
        dest.writeString(this.expYear);
        dest.writeString(this.confirmation);
    }

    protected Payment(Parcel in) {
        this.order = in.readParcelable(Order.class.getClassLoader());
        this.tax = in.readDouble();
        this.tip = in.readDouble();
        this.cardNumber = in.readString();
        this.csc = in.readString();
        this.expMonth = in.readString();
        this.expYear = in.readString();
        this.confirmation = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}
