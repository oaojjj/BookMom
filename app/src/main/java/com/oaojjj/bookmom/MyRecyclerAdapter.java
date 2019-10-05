package com.oaojjj.bookmom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.activities.BaseActivity;
import com.oaojjj.bookmom.models.BookItem;
import com.oaojjj.bookmom.models.BookMarkDB;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    BookMarkDB bookMarkDB;
    private Context mContext;

    private List<BookItem> mItemList;

    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);
    }

    private MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }

    public MyRecyclerAdapter(Context Context, List<BookItem> itemList) {
        bookMarkDB = new BookMarkDB(mContext);
        mContext = Context;
        mItemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BookItem item = mItemList.get(position);
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());
        holder.rental.setText(item.getRental());

        //TODO 재우형 북마크 테스트가 불가능해서 일단 만들었는데 되는지 테스트 필요
        if (bookMarkDB.isBookMark(item.getTitle())) {
            holder.bookMark.setImageResource(R.drawable.ic_bookmark_black_24dp);
        } else {
            holder.bookMark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }


        // 클릭이벤트 구현
        if (mListener != null) {
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView category;
        TextView rental;
        ImageView bookMark;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_item_book_title);
            category = itemView.findViewById(R.id.tv_item_book_category);
            rental = itemView.findViewById(R.id.tv_item_book_rental);
            bookMark = itemView.findViewById(R.id.iv_item_book_mark);
        }
    }
}
