package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Order implements Parcelable {

    private static double DEFAULT_TAX = 10.5;
    private static double DEFAULT_TIP = 0.0;
    private ArrayList<OrderItem> items;

    public Order(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public Order() {
        this.items = new ArrayList<OrderItem>();
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public boolean itemIsOnOrder(RestaurantItem itemToFind) {
        boolean itemIsOnOrder = false;
        for (OrderItem item : getItems()) {
            if (item.equals(itemToFind)) {
                itemIsOnOrder = true;
            }
        }
        return itemIsOnOrder;
    }

    public void addItem(RestaurantItem toAdd) {
        // Bump quantity by 1 if item is on order
        if(itemIsOnOrder(toAdd)) {
            items.get(items.indexOf(toAdd)).adjustQuantity(1);
        } else { //Add to order for first time
            items.add(new OrderItem(toAdd));
        }
    }

    public void addItems(List<RestaurantItem> items) {
        for(RestaurantItem item : items) {
            addItem(item);
        }
    }

    public void addOrderItems(List<OrderItem> items) {
        for(OrderItem item : items) {
            for(int i = 0; i < item.getQuantity(); i++) {
                addItem(item.getItem());
            }
        }
    }

    public void removeItem(RestaurantItem itemToRemove, int amountToRemove) {
        for (OrderItem item : items) {
            if(item.getItem().getItemName().equals(itemToRemove.getItemName())) {
                item.adjustQuantity(-1 * amountToRemove);
            }
        }
    }

    public int getCountOf(RestaurantItem toCount) {
        for(OrderItem item : getItems()) {
            if(item.equals(toCount)) {
                return item.getQuantity();
            }
        }
        return 0;
    }

    public String getFormattedCountOf(RestaurantItem toCount) {
        return "Qty: " + getCountOf(toCount);
    }

    public double getSubtotal() {
        double subtotal = 0.0;
        for(OrderItem item : items) {
            subtotal += item.price();
        }
        return subtotal;
    }

    public double getTotal(double tax, double tip) {
        return getSubtotal() * (1 + (tax/10)) + tip;
    }

    public String getFormattedTotal() {
        return String.format(Locale.getDefault(),"$%4.2f", getTotal(DEFAULT_TAX, DEFAULT_TIP));
    }

    public void removeAll(RestaurantItem itemToRemove) {
        Iterator<OrderItem> iterator = items.iterator();
        while(iterator.hasNext()) {
            OrderItem item = iterator.next();
            if(item.getItem().equals(itemToRemove)) {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        String orderAsString = "";
        for (OrderItem item : items) {
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
        this.items = in.createTypedArrayList(OrderItem.CREATOR);
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
