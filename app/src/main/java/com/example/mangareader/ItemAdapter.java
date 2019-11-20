package com.example.mangareader;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int MAX_TITLE_LENGTH = 8;
    private ArrayList<CustomBottomItem> items;
    private String defaultBackground;
    private String defaultTint;
    private ItemSelector itemSelectorInterface;

    public ItemAdapter(int defaultOpenIndex, ArrayList<CustomBottomItem> items, ItemSelector itemSelectorInterface) {
        this.items = items;
        this.itemSelectorInterface = itemSelectorInterface;
        setDefaultOpen(defaultOpenIndex);
        this.itemSelectorInterface.itemSelect(defaultOpenIndex);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setIcon(((ItemViewHolder) holder).item_icon, items.get(position).getItemIconId());
        setTitle(((ItemViewHolder) holder).item_title, items.get(position).getItemTitle());
        setSelectedItemStyle(
                ((ItemViewHolder) holder).item_parent,
                ((ItemViewHolder) holder).item_title,
                ((ItemViewHolder) holder).item_icon,
                items.get(position).isOpen(),
                items.get(position).getItemBackgroundColor(),
                items.get(position).getItemTintColor());
        setOnClickItem(((ItemViewHolder) holder).click_parent, position, items.get(position).getItemId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void setIcon(ImageView imageView, int iconId) {
        imageView.setImageResource(iconId);
    }

    @SuppressLint("SetTextI18n")
    private void setTitle(TextView textView, String text) {
        if (text.length() > MAX_TITLE_LENGTH) {
            textView.setText(text.substring(0, MAX_TITLE_LENGTH) + "…");
        } else {
            textView.setText(text);
        }
    }

    @SuppressLint("ResourceType")
    private void setSelectedItemStyle(CardView parent, TextView title, ImageView icon, boolean isOpen, String parentColor, String tintColor) {
        if (isOpen) {
            title.setVisibility(View.VISIBLE);
            parent.setCardBackgroundColor(Color.parseColor(parentColor));
            title.setTextColor(Color.parseColor(tintColor));
            icon.setColorFilter(Color.parseColor(tintColor), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            title.setVisibility(View.GONE);
            parent.setCardBackgroundColor(Color.parseColor(getDefaultBackground()));
            title.setTextColor(Color.parseColor(getDefaultTint()));
            icon.setColorFilter(Color.parseColor(getDefaultTint()), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    private void setOnClickItem(RelativeLayout parent, final int position, final int itemID) {
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //It closes all items first, and then opens the selected item.
                changeCloseData();
                items.get(position).setOpen(true);
                notifyDataSetChanged();
                itemSelectorInterface.itemSelect(itemID);
            }
        });
    }

    //When you select a new item, you close all of them. This way, only one item will always be open.
    private void changeCloseData() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setOpen(false);
        }
    }

    //Sets the default open item.
    private void setDefaultOpen(int index) {
        if (index > -1 && index <= items.size() - 1) {
            items.get(index).setOpen(true);
        } else {
            if (!items.isEmpty()) {
                items.get(0).setOpen(true);
            }
        }
    }

    //Getter-Setters
    private String getDefaultBackground() {
        return defaultBackground;
    }

    public void setDefaultBackground(String defaultBackground) {
        this.defaultBackground = defaultBackground;
    }

    private String getDefaultTint() {
        return defaultTint;
    }

    public void setDefaultTint(String defaultTint) {
        this.defaultTint = defaultTint;
    }
}
