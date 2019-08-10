package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.restaurantapp3.OrderDisplay;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Order implements Parcelable {

    private static double DEFAULT_TAX = 10.5;
    private static double DEFAULT_TIP = 0.0;
    private ArrayList<RestaurantItem> items;

    DecimalFormat df = new DecimalFormat("####0.00");

    public Order(ArrayList<RestaurantItem> items) {
        this.items = items;
    }

    public Order() {
        this.items = new ArrayList<RestaurantItem>();
    }

    public ArrayList<RestaurantItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<RestaurantItem> items) {
        this.items = items;
    }

    public void addItem(RestaurantItem item) {
        items.add(item);
    }

    public void addItems(List<RestaurantItem> items) {
        for(RestaurantItem item : items) {
            addItem(item);
        }
    }

    public void removeItem(RestaurantItem item) { items.remove(item); }

    public int getCountOf(String itemName) {
        int count = 0;
        Iterator<RestaurantItem> iterator = items.iterator();
        while(iterator.hasNext()) {
            RestaurantItem item = iterator.next();
            if(item.getItemName().equals(itemName)) count++;
        }
        return count;
    }

    public String getFormattedCountOf(String itemName) { return "Qty: " + getCountOf(itemName); }

    public double getSubtotal() {
        double subtotal = 0.0;
        for(RestaurantItem item : items) {
            subtotal += item.getItemPrice();
        }
        return Double.valueOf(df.format(subtotal));
    }

    public double getTax(){
//        return getSubtotal() * (DEFAULT_TAX / 100);
        return Double.valueOf(df.format(getSubtotal() * (DEFAULT_TAX / 100)));
    }

    public double getTotal( double tip) {
        return Double.valueOf(df.format(getSubtotal() + getTax() + tip));
    }

    public String getFormattedTotal(double tip) {
        return String.format(Locale.getDefault(),"$%4.2f", getTotal(tip));
    }

    public void removeAll(RestaurantItem itemToRemove) {
        Iterator<RestaurantItem> iterator = items.iterator();
        while(iterator.hasNext()) {
            RestaurantItem item = iterator.next();
            if(item.getItemName().equals(itemToRemove.getItemName())) {
                iterator.remove();
            }
        }
    }

    public int countThisItem(RestaurantItem itemToCount) {
        int count = 0;
        Iterator<RestaurantItem> iterator = items.iterator();
        while(iterator.hasNext()) {
            RestaurantItem item = iterator.next();
            if(item.getItemName().equals(itemToCount.getItemName())) {
                count++;
            }
        }
        return count;
    }

    public String formattedCountOf(RestaurantItem item) {
        return "Qty: " + countThisItem(item);
    }

    @Override
    public String toString() {
        String orderAsString = "";
        Iterator<RestaurantItem> iterator = items.iterator();
        while(iterator.hasNext()) {
            RestaurantItem item = iterator.next();
            orderAsString += item.toString() + "\n";
        }
        return orderAsString.trim();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.items);
    }

    protected Order(Parcel in) {
        this.items = in.createTypedArrayList(RestaurantItem.CREATOR);
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
