package com.oaojjj.bookmom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookItem;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHoler> {

    private List<BookItem> mItemList;

    public BookAdapter(List<BookItem> itemList) {
        mItemList = itemList;
    }

    public interface MyRecyclerViewClickListener {
        void onItemButtonClick(int position);
    }

    private MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public BookViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_rental,parent,false);
        return new BookViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHoler holder, int position) {
        holder.tvTitle.setText(mItemList.get(position).getTitle());
        holder.tvCategory.setText(mItemList.get(position).getCategory());

        if(mListener!=null){
            final int pos = position;
            holder.returnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemButtonClick(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public static class BookViewHoler extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvCategory;
        Button returnButton;
        public BookViewHoler(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_book_title1);
            tvCategory = itemView.findViewById(R.id.tv_item_book_category1);
            returnButton = itemView.findViewById(R.id.bt_rental1);
        }
    }

}
