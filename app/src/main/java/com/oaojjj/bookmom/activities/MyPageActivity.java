package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.adapter.BookAdapter;
import com.oaojjj.bookmom.models.BookMarkDB;

import java.util.List;

public class MyPageActivity extends BaseActivity implements BookAdapter.MyRecyclerViewClickListener {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private BookMarkDB bookMarkDB;
    private TextView tvMyBookMark;

    public static List<String> bookTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        recyclerView = findViewById(R.id.rv_rental);
        tvMyBookMark = findViewById(R.id.tv_my_book_mark);

        adapter.setOnClickListener(this);

        // 북마크한 책 제목 표시
        for (int i = 0; i < bookTitleList.size(); i++) {
            if (bookMarkDB.isBookMark(bookTitleList.get(i))) {
                if (i == 0) {
                    tvMyBookMark.setText(bookTitleList.get(i));
                }
                tvMyBookMark.setText(tvMyBookMark.getText() + "\n" + bookTitleList.get(i));
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // 반납하기 눌렀을때
    @Override
    public void onItemButtonClick(int position) {

    }
}
