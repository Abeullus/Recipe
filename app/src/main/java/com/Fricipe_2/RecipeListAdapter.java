package com.Fricipe_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Fricipe_2.Helper.PictureHelper;
import com.Fricipe_2.Model.RecipeItem;

import java.util.ArrayList;

public class RecipeListAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final ArrayList<RecipeItem> recipeList;
    private final int layout;
    private final PictureHelper pictureHelper = new PictureHelper();

    public RecipeListAdapter(Context context, ArrayList<RecipeItem> recipeList, int layout) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recipeList = recipeList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        RecipeItem recipeItem = recipeList.get(position);

        ImageView ListView_Image = convertView.findViewById(R.id.listItem_image);
        ListView_Image.setImageBitmap(pictureHelper.getBitmapFromByteArray(recipeItem.get_thumbnail()));

        TextView listView_title = convertView.findViewById(R.id.listItem_title);
        TextView listView_author = convertView.findViewById(R.id.listItem_author);
        TextView listView_likecount = convertView.findViewById(R.id.listItem_likecount);

        listView_title.setText(recipeItem.get_recipeName());
        listView_author.setText(recipeItem.get_author());
        listView_likecount.setText(String.valueOf(recipeItem.get_likeCount()));

        return convertView;
    }
}
