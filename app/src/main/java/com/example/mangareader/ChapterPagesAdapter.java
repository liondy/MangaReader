package com.example.mangareader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ChapterPagesAdapter extends BaseAdapter {

    private ArrayList<ChapterPages> chapterPagesList;
    private Context context;

    public ChapterPagesAdapter(ArrayList<ChapterPages> chapterList, Context context) {
        this.chapterPagesList = chapterList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.chapterPagesList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.chapterPagesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int id, View convertView, ViewGroup parent) {
        String image =  this.chapterPagesList.get(id).getImage();
        String url = "https://cdn.mangaeden.com/mangasimg/"+image;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.item_pages, null, false);

            ImageView gambar = convertView.findViewById(R.id.iv_pages);

            Picasso.get().load(url).into(gambar);
            ViewHolder vh = new ViewHolder(gambar);
            convertView.setTag(vh);
        }
        else{
            ViewHolder vh = (ViewHolder) convertView.getTag();
            Picasso.get().load(url).into(vh.gambar);
        }

        return convertView;
    }

    private class ViewHolder{
        public ImageView gambar;

        public ViewHolder(ImageView gambar) {
            this.gambar = gambar;
        }
    }
}
