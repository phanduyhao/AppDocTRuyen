package com.example.assnetworking.DetailBook.CommentsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assnetworking.Comment.Comments;
import com.example.assnetworking.DetailBook.Model.Comment;
import com.example.assnetworking.Home.Adapter.BookAdapter;
import com.example.assnetworking.Home.Model.Book;
import com.example.assnetworking.R;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{
    private Context context;
    private List<Comments> commentsList;
    public CommentsAdapter(Context context) {
        this.context = context;
    }

    public void setDataComments(List<Comments> commentsList) {
        this.commentsList = commentsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comments comments = commentsList.get(position);
        if (comments == null) {
            return;
        }
        holder.tv_namecomment.setText(comments.getId_user().getFullname());
        holder.tv_contentcomment.setText(comments.getContent());
        holder.tv_commenttimecomment.setText(comments.getCommenttime());
    }

    @Override
    public int getItemCount() {
        return commentsList == null ? 0 : commentsList.size();
    }


    public class CommentsViewHolder extends RecyclerView.ViewHolder{
        TextView tv_namecomment, tv_contentcomment, tv_commenttimecomment;
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namecomment = itemView.findViewById(R.id.tv_namecomment);
            tv_contentcomment = itemView.findViewById(R.id.tv_contentcomment);
            tv_commenttimecomment = itemView.findViewById(R.id.tv_commenttimecomment);
        }
    }
}
