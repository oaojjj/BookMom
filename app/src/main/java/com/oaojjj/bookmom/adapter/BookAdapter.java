package com.oaojjj.bookmom.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHoler> {

    @NonNull
    @Override
    public BookViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class BookViewHoler extends RecyclerView.ViewHolder {
        public BookViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }

}
