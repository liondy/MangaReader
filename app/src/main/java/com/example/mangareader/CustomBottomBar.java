package com.example.mangareader;

import android.content.Context;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomBottomBar {
    private ItemSelector itemSelectorInterface;
    private Context context;
    private CardView custom_bottom_bar_parent;
    private RecyclerView custom_recycler_view;
    private View custom_divider;
    private ArrayList<CustomBottomItem> items;

    public CustomBottomBar(Context context, View view, ItemSelector itemSelectorInterface) {
        setType(view);
        this.context = context;
        this.itemSelectorInterface = itemSelectorInterface;
    }

    private void setType(View view) {
        this.custom_bottom_bar_parent = view.findViewById(R.id.custom_bottom_bar_parent);
        this.custom_recycler_view = view.findViewById(R.id.custom_recycler_view);
        this.custom_divider = view.findViewById(R.id.custom_divider);
        items = new ArrayList<>();
    }
}
