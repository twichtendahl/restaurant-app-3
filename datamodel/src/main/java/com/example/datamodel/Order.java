package com.example.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {

    private ArrayList<OrderItem> items;

    public Order(ArrayList<OrderItem> items) {
        this.items = items;
    }
    public Order() {
        this.items = new ArrayList<>();
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public boolean itemIsOnOrder(RestaurantItem itemToFind) {
        boolean itemIsOnOrder = false;
        for (OrderItem orderItem : items) {
            if (orderItem.getItem().equals(itemToFind)) {
                itemIsOnOrder = true;
            }
        }
        return itemIsOnOrder;
    }

    public void addItem(RestaurantItem toAdd) {
        // Bump quantity by 1 if item is on order
        if(!itemIsOnOrder(toAdd)) {
            items.add(new OrderItem(toAdd));
        } else {
            for (OrderItem orderItem : items) {
                if (orderItem.getItem().equals(toAdd)) {
                    orderItem.adjustQuantity(1);
                }
            }
        }
    }

    public void addItems(List<RestaurantItem> items) {
        for(RestaurantItem item : items) {
            addItem(item);
        }
    }

    public void addOrderItems(List<OrderItem> orderItems) {
        items.addAll(orderItems);
    }

    public void removeItem(RestaurantItem itemToRemove, int amountToRemove) {
        for (OrderItem item : items) {
            if(item.getItem().getItemName().equals(itemToRemove.getItemName())) {
                item.adjustQuantity(-1 * amountToRemove);
            }
        }
    }

    public int getCountOf(RestaurantItem toCount) {
        for(OrderItem item : items) {
            if(item.getItem().equals(toCount)) {
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

    public void removeAll(RestaurantItem itemToRemove) {
        removeItem(itemToRemove, getCountOf(itemToRemove));
    }

    @Override
    public String toString() {
        String orderAsString = "";
        for (OrderItem item : items) {
            orderAsString += item.toString() + ",";
        }
        // Exclude final comma
        return orderAsString.substring(0, orderAsString.length() - 2);
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

    public static final Creator<Order> CREATOR = new Creator<Order>() {
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
