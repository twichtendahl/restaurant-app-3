package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {
    private Payment payment;
    private String fulfillmentMethod;

    public static final String DINE_IN = "Dine In";
    public static final String TAKE_OUT = "Take-out";
    public static final String DELIVERY = "Delivery";

    public Request(Payment payment, String fulfillmentMethod) {
        this.payment = payment;
        this.fulfillmentMethod = fulfillmentMethod;
    }

    public Request(Payment payment) {
        this.payment = payment;
        fulfillmentMethod = DINE_IN;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getFulfillmentMethod() {
        return fulfillmentMethod;
    }

    public void setFulfillmentMethod(String fulfillmentMethod) {
        this.fulfillmentMethod = fulfillmentMethod;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.payment, flags);
        dest.writeString(this.fulfillmentMethod);
    }

    protected Request(Parcel in) {
        this.payment = in.readParcelable(Payment.class.getClassLoader());
        this.fulfillmentMethod = in.readString();
    }

    public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel source) {
            return new Request(source);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
}
