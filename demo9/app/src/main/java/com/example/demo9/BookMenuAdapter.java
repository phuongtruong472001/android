package com.example.demo9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookMenuAdapter extends RecyclerView.Adapter<BookMenuAdapter.ViewHolder> {

    List<MenuBook> list = new ArrayList<>();
    Context context;
    IOnClickMenuItem mIOnClickMenuItem;

    public void setmIOnClickMenuItem(IOnClickMenuItem mIOnClickMenuItem) {
        this.mIOnClickMenuItem = mIOnClickMenuItem;
    }

    public BookMenuAdapter(List<MenuBook> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BookMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMenuAdapter.ViewHolder holder, int position) {
        MenuBook item = list.get(position);
        Glide.with(context).load(item.getImageLink()).into(holder.imageBookMenu);
        holder.titleBookMenu.setText(String.valueOf(item.getTitle()));
        holder.imageBookMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIOnClickMenuItem.iClickImageBook(item);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBookMenu;
        TextView titleBookMenu;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageBookMenu = itemView.findViewById(R.id.img_menu_book);
            titleBookMenu = itemView.findViewById(R.id.name_of_book);
        }
    }
}
