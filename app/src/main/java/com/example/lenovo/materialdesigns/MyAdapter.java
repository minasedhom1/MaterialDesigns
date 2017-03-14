package com.example.lenovo.materialdesigns;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mido on 3/14/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

      private ArrayList<Item> items;
    Context context;
    MyAdapter(ArrayList<Item> items, Context context)
    {
        this.items=items;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item myItem=items.get(position);
        holder.name.setText(Html.fromHtml(myItem.getName()));
        String des = String.valueOf(Html.fromHtml(myItem.getDescription()));
        des = des.replace("\n\n", "\n");
        holder.description.setText(des);

       // holder.rate.setText(String.valueOf(myItem.getRate()));
        //  holder.image.setMaxHeight(300);
        Picasso.with(context).load(myItem.getPhoto1()).error(R.mipmap.ic_launcher).into(holder.image);
      // holder.shineButton.init((AppCompatActivity) context);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button share, call,comment,menu;
        ImageView image;
        TextView name, description,rate;
        ShineButton shineButton;
        Spinner rateSpin;
        public ViewHolder(View convertView) {
            super(convertView);
            call = (Button) convertView.findViewById(R.id.item_call_btn);
            share = (Button) convertView.findViewById(R.id.item_share_btn);
           comment = (Button) convertView.findViewById(R.id.item_comment_btn);
           menu = (Button) convertView.findViewById(R.id.item_view_menu_btn);
            name = (TextView) convertView.findViewById(R.id.name2_tv);
            description = (TextView) convertView.findViewById(R.id.item_description);
           image = (ImageView) convertView.findViewById(R.id.item_icon);
         //  shineButton = (ShineButton) convertView.findViewById(R.id.like_btn);
        }
        // each data item is just a string in this case

        }
    }

