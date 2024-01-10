package com.example.assnetworking.ReadBook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assnetworking.Home.Adapter.BookAdapter;
import com.example.assnetworking.Home.Model.Book;
import com.example.assnetworking.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReadBookAdapter extends RecyclerView.Adapter<ReadBookAdapter.ReadBookViewHolder>{
    private Context context;
    private List<String> listImage;

    public ReadBookAdapter(Context context) {
        this.context = context;
    }

    public void setDataReadBook(List<String> listImage) {
        this.listImage = listImage;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ReadBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contentbook, parent, false);
        return new ReadBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadBookViewHolder holder, int position) {
        String image = listImage.get(position);
        if (image == null) {
            return;
        }
        holder.num_img.setText(position+1+"");
        Picasso.get().load(image).placeholder(R.drawable.imageload).error(R.drawable.imageload).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listImage == null ? 0 : listImage.size();
    }

    public class ReadBookViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView num_img;
        public ReadBookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_readbook);
            num_img = itemView.findViewById(R.id.num_img);
        }
    }
}
