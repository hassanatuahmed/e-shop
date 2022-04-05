package com.example.e_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.e_shop.R;
import com.example.e_shop.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductModel> items = new ArrayList<>();
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public ProductAdapter(List<ProductModel> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public ImageView product_image;
        public TextView product_title;
        public TextView product_price;
        public CardView lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            product_image = (ImageView) v.findViewById(R.id.product_image);
            product_title = (TextView) v.findViewById(R.id.product_title);
            product_price = (TextView) v.findViewById(R.id.product_price);

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final ProductModel p = items.get(position);
        OriginalViewHolder view = (OriginalViewHolder) holder;

        view.product_title.setText(p.title + "");
        view.product_price.setText(p.price + "");
        if (!p.image.isEmpty()) {
            Log.d(TAG, "onBindViewHolder: =>> " + p.image);

            Glide.with(ctx).load(p.image).placeholder(R.drawable.ic_launcher_background)
                    .dontAnimate().into(view.product_image);

        }

        view.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, items.get(position), position);
            }
        });


    }

    public interface OnItemClickListener {
        void onItemClick(View view, ProductModel obj, int pos);
    }

    private static final String TAG = "ProductAdapter";

    @Override
    public int getItemCount() {
        return items.size();
    }
}
