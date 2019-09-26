package com.oaojjj.bookmom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.models.BookItem;

import java.util.List;

public class MyRecyclerAdapter extends  RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private List<BookItem> mItemList;

    public interface MyRecyclerViewClickListener{
        void onItemClicked(int position);
    }

    private  MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener){
        mListener = listener;
    }

    public MyRecyclerAdapter(List<BookItem> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookItem item = mItemList.get(position);
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());
        holder.rental.setText(item.getRental());

        // 클릭이벤트 구현
        if(mListener != null){
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView category;
        TextView rental;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_item_book_title);
            category = itemView.findViewById(R.id.tv_item_book_category);
            rental = itemView.findViewById(R.id.tv_item_book_rental);
        }
    }
}
