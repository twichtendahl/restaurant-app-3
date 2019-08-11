package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class OrderItem implements Parcelable {
    private RestaurantItem item;
    private int quantity;

    public OrderItem(RestaurantItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public OrderItem(RestaurantItem item) {
        this(item, 1);
    }

    public RestaurantItem getItem() {
        return item;
    }

    public void setItem(RestaurantItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.item, flags);
        dest.writeInt(this.quantity);
    }

    protected OrderItem(Parcel in) {
        this.item = in.readParcelable(RestaurantItem.class.getClassLoader());
        this.quantity = in.readInt();
    }

    public static final Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel source) {
            return new OrderItem(source);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    @Override
    public String toString() {
        return "OrderItem: " + getQuantity() + " " + getItem().getItemName();
    }

    public double price() {
        return item.getItemPrice() * quantity;
    }

    public String formattedPrice() {
        return String.format(Locale.getDefault(), "$%4.2f", price());
    }

    public String formattedQuantity() { return "Qty: " + quantity; }

    public void adjustQuantity(int adjustment) {
        setQuantity(quantity + adjustment);
    }
}
