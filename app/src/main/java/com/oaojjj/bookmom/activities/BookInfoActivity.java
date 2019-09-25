package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.oaojjj.bookmom.R;

public class BookInfoActivity extends BaseActivity {

    ImageButton ibBookMark;

    Button btBookRental;

    // TODO 재우형
    // 북마크 추가 -> true , 북마크 없음 -> false
    // 이 변수에 사용자 북마크 정보를 저장
    boolean CHECK_BOOK_MARK=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        ibBookMark = findViewById(R.id.ib_book_mark); // WebView 구현해서 책 제목을 넘겨서 책에 대한 정보페이지를 웹으로 나타낸다.
        btBookRental = findViewById(R.id.bt_book_rental);

        // 북마크 버튼
        ibBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CHECK_BOOK_MARK){
                    ibBookMark.setSelected(true);
                }
                else{
                    ibBookMark.setSelected(false);
                }
            }
        });

        // 대여 버튼
        btBookRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
