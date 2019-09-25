package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.oaojjj.bookmom.R;

//TODO 주은
/**
 * 메인페이지
 */
public class MainActivity extends BaseActivity {

    private ImageButton btn_mypage;
    private ImageButton btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_mypage=findViewById(R.id.btn_mypage);
        btn_list=findViewById(R.id.btn_list);

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class); // 액티비티 이동
                startActivity(intent);

            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class); // 액티비티 이동
                startActivity(intent);

            }
        });
    }
}
