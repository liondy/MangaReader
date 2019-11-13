package com.example.mangareader;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public CardView item_parent;
    public RelativeLayout click_parent;
    public ImageView item_icon;
    public TextView item_title;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        item_parent = itemView.findViewById(R.id.item_parent);
        click_parent = itemView.findViewById(R.id.click_parent);
        item_icon = itemView.findViewById(R.id.item_icon);
        item_title = itemView.findViewById(R.id.item_title);
    }
}
