package com.example.android.restaurantapp3.model;

import java.util.Locale;

public class RestaurantItem {

    private String itemId;
    private String itemName;
    private String itemDescription;
    private int sortPosition;
    private double itemPrice;
    private String itemImage;

    public RestaurantItem() {
    }

    public RestaurantItem(String itemId, String itemName, String itemDescription, int sortPosition, double itemPrice, String itemImage) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.sortPosition = sortPosition;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public String toString() {
        return "RestaurantItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", sortPosition=" + sortPosition +
                ", itemPrice=" + itemPrice +
                ", itemImage=" + itemImage +
                '}';
    }

    public String getFormattedPrice() {
        return String.format(Locale.getDefault(),"$%2.2f", this.getItemPrice());
    }
}
