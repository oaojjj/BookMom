package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.utils.CustomDialog;

public class BookInfoActivity extends BaseActivity {

    private TextView tvTitle;
    private Button btBookRental;
    private ImageButton ibBookMark;

    private CustomDialog customDialog;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    // TODO 재우형
    // 북마크 추가 -> true , 북마크 없음 -> false
    // 이 변수에 사용자 북마크 정보를 저장아니면 따로 모델클래스 만들어도 상관 없음
    boolean CHECK_BOOK_MARK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        tvTitle = findViewById(R.id.tv_book_title);
        btBookRental = findViewById(R.id.bt_book_rental);
        ibBookMark = findViewById(R.id.ib_book_mark); // WebView 구현해서 책 제목을 넘겨서 책에 대한 정보페이지를 웹으로 나타낸다.

        // 북마크 버튼
        ibBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CHECK_BOOK_MARK) {
                    ibBookMark.setSelected(true);
                } else {
                    ibBookMark.setSelected(false);
                }
            }
        });

        // 대여 버튼 다이얼로그 생성
        btBookRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(BookInfoActivity.this, tvTitle.getText().toString());
                customDialog.setListener(mPositiveListener, mNegativeListener);
                customDialog.show();
            }
        });

        // 다이얼로그
        // 완료 버튼
        mPositiveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookInfoActivity.this, "대여 완료!", Toast.LENGTH_SHORT).show();
                customDialog.dismiss();
            }
        };

        // 취소 버튼
        mNegativeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookInfoActivity.this, "취소", Toast.LENGTH_SHORT).show();
                customDialog.dismiss();
            }
        };

    }
}
