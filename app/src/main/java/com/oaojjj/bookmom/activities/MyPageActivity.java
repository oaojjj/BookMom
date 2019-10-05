package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookMarkDB;

import java.util.List;

public class MyPageActivity extends BaseActivity {

    public static List<String> bookTitleList;
    BookMarkDB bookMarkDB;

    TextView tvMyBookMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        tvMyBookMark = findViewById(R.id.tv_my_book_mark);

        // 북마크한 책 제목 표시
        for(int i=0;i<bookTitleList.size();i++){
            if(bookMarkDB.isBookMark(bookTitleList.get(i))){
                if(i==0){
                    tvMyBookMark.setText(bookTitleList.get(i));
                }
                tvMyBookMark.setText(tvMyBookMark.getText()+"\n"+bookTitleList.get(i));
            }
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
