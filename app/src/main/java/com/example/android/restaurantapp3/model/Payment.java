package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Payment implements Parcelable {
    private int cardNumber;
    private int csc;
    private int expMonth;
    private int expYear;
    private String confirmationCode;

    public Payment(int cardNumber, int csc, int expMonth, int expYear) {
        this.cardNumber = cardNumber;
        this.csc = csc;
        this.expMonth = expMonth;

        if(expYear > 99) {
            this.expYear = expYear;
        } else {
            this.expYear = 2000 + expYear;
        }

        confirmationCode = UUID.randomUUID().toString();
    }

    public Payment() {
        this(0,0,0,0);
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cardNumber);
        dest.writeInt(this.csc);
        dest.writeInt(this.expMonth);
        dest.writeInt(this.expYear);
        dest.writeString(this.confirmationCode);
    }

    protected Payment(Parcel in) {
        this.cardNumber = in.readInt();
        this.csc = in.readInt();
        this.expMonth = in.readInt();
        this.expYear = in.readInt();
        this.confirmationCode = in.readString();
    }

    public static final Parcelable.Creator<Payment> CREATOR = new Parcelable.Creator<Payment>() {
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
