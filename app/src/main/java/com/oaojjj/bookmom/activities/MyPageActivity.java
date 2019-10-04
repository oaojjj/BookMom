package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.oaojjj.bookmom.R;

public class MyPageActivity extends BaseActivity {

    TextView tvMyBookMark;
    TextView tvMyRental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        tvMyBookMark = findViewById(R.id.tv_my_book_mark);
        tvMyRental = findViewById(R.id.tv_my_rental);

    }
}
