package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.oaojjj.bookmom.R;

//TODO 주은
/**
 * 메인페이지
 *
 * 자바코드에서는 변수명 쓸 때 camelCase 로 쓰기
 * xml 에서는 소문자만 쓰기
 * 해보다가 모르는건 TODO달고 주석처리
 * */
public class MainActivity extends BaseActivity {

    private ImageButton btMyPage;
    private ImageButton btList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMyPage=findViewById(R.id.btn_my_page);
        btList=findViewById(R.id.btn_list);

        btMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class); // 액티비티 이동
                startActivity(intent);

            }
        });

        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class); // 액티비티 이동
                startActivity(intent);

            }
        });
    }
}
