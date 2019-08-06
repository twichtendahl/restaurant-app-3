package com.example.android.restaurantapp3.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Order {

    private static double DEFAULT_TAX = 10.5;
    private static double DEFAULT_TIP = 0.0;
    private ArrayList<RestaurantItem> items;

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
        Iterator<RestaurantItem> iterator = items.iterator();
        while(iterator.hasNext()) {
            items.add(iterator.next());
        }
    }

    public void removeItem(RestaurantItem item) { items.remove(item); }

    public double getSubtotal() {
        double subtotal = 0.0;
        for(RestaurantItem item : items) {
            subtotal += item.getItemPrice();
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


}
