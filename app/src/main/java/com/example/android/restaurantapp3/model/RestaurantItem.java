package com.example.android.restaurantapp3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;
import java.util.UUID;

public class RestaurantItem implements Parcelable {

    private String itemId;
    private String itemName;
    private String itemDescription;
    private int sortPosition;
    private double itemPrice;
    private String itemImage;

    public RestaurantItem() {
    }

    public RestaurantItem(String itemId, String itemName, String itemDescription, int sortPosition, double itemPrice, String itemImage) {
        if(itemId == null) {
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.sortPosition = sortPosition;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public RestaurantItem(String itemName, String itemDescription, int sortPosition, double itemPrice, String itemImage) {
        this(null, itemName, itemDescription, sortPosition, itemPrice, itemImage);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemName);
        dest.writeString(this.itemId);
        dest.writeString(this.itemDescription);
        dest.writeInt(this.sortPosition);
        dest.writeDouble(this.itemPrice);
        dest.writeString(this.itemImage);
    }

    protected RestaurantItem(Parcel in) {
        this.itemName = in.readString();
        this.itemId = in.readString();
        this.itemDescription = in.readString();
        this.sortPosition = in.readInt();
        this.itemPrice = in.readDouble();
        this.itemImage = in.readString();
    }

    public static final Parcelable.Creator<RestaurantItem> CREATOR = new Parcelable.Creator<RestaurantItem>() {
        @Override
        public RestaurantItem createFromParcel(Parcel source) {
            return new RestaurantItem(source);
        }

        @Override
        public RestaurantItem[] newArray(int size) {
            return new RestaurantItem[size];
        }
    };


    // Two items are equal that have the same name
    public boolean equals(RestaurantItem otherItem) {
        return getItemName().equals(otherItem.getItemName());
    }

    public String getFormattedPrice() {
        return String.format(Locale.getDefault(),"$%2.2f", itemPrice);
    }
}
