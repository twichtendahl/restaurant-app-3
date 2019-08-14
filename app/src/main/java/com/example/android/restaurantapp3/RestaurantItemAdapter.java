package com.example.android.restaurantapp3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.restaurantapp3.model.Order;
import com.example.android.restaurantapp3.model.RestaurantItem;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RestaurantItemAdapter extends RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder> {

    private List<RestaurantItem> mItems;
    private Context mContext;

    public RestaurantItemAdapter(List<RestaurantItem> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RestaurantItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.single_item_listing, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final RestaurantItemAdapter.ViewHolder holder, int position) {
        final RestaurantItem item = mItems.get(position);

        holder.tvName.setText(item.getItemName());
        holder.tvDescription.setText(item.getItemDescription());
        holder.tvPrice.setText(item.getFormattedPrice());
        holder.tvQuantity.setText(R.string.starting_quantity);

        try {
            String imageFile = item.getItemImage();
            InputStream stream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(stream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof MainActivity) {
                    ((MainActivity)mContext).order.addItem(item);
                    holder.tvQuantity.setText(((MainActivity)mContext).order.getFormattedCountOf(item));
                }
            }
        });

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof MainActivity) {
                    ((MainActivity)mContext).order.removeItem(item, 1);
                    holder.tvQuantity.setText(((MainActivity)mContext).order.getFormattedCountOf(item));
                }
            }
        });


    }

    @Override
    public int getItemCount() { return mItems.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView imageView;
        public TextView tvDescription;
        public TextView tvPrice;
        public Button addButton;
        public TextView tvQuantity;
        public Button removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameLabel);
            imageView = itemView.findViewById(R.id.itemImage);
            tvDescription = itemView.findViewById(R.id.itemDescription);
            tvPrice = itemView.findViewById(R.id.priceLabel);
            addButton = itemView.findViewById(R.id.addToOrder);
            removeButton = itemView.findViewById(R.id.removeFromOrder);
            tvQuantity = itemView.findViewById(R.id.quantity);
        }
    }

}
