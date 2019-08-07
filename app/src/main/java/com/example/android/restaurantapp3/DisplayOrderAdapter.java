package com.example.android.restaurantapp3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.RestaurantItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DisplayOrderAdapter extends RecyclerView.Adapter<DisplayOrderAdapter.ViewHolder> {

    private Order mOrder;
    private Context mContext;
    private static List<RestaurantItem> mItems;

    public DisplayOrderAdapter(Order mOrder, Context mContext) {
        this.mOrder = mOrder;
        this.mContext = mContext;
        mItems = new ArrayList<>();
        Iterator<RestaurantItem> iterator = getmOrder().getItems().iterator();
        while(iterator.hasNext()) {
            mItems.add(iterator.next());
        }

    }

    public Order getmOrder() {
        return mOrder;
    }

    public void setmOrder(Order mOrder) {
        this.mOrder = mOrder;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DisplayOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.line_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final DisplayOrderAdapter.ViewHolder holder, int position) {
        final RestaurantItem item = mItems.get(position);

        holder.liName.setText(item.getItemName());
        holder.liQuantity.setText(getmOrder().getFormattedCountOf(item.getItemName()));
        double price = getmOrder().countThisItem(item) * item.getItemPrice();
        holder.liPrice.setText(String.format(Locale.getDefault(),"$%4.2f", price));
    }

    @Override
    public int getItemCount() { return mItems.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView liName;
        public TextView liQuantity;
        public TextView liPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            liName = itemView.findViewById(R.id.li_name);
            liQuantity = itemView.findViewById(R.id.li_quantity);
            liPrice = itemView.findViewById(R.id.li_price);

        }
    }

}