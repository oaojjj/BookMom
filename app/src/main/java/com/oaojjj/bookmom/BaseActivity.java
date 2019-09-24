package com.oaojjj.bookmom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_toolbar);
    }


    // layout resource 를 인자로 받아서 LayoutInflater 를 통해 inflate 되고 이후 최상단 뷰에 add 된다.
    @Override
    public void setContentView(int layoutResID) {
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_basic_toolbar, null);
        FrameLayout activityContainer = fullView.findViewById(R.id.activitys_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        Toolbar toolbar = findViewById(R.id.basic_toolbar);

        setSupportActionBar(toolbar);
        setTitle("W A P");
    }


    // 메뉴 등록
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isSignIn()){
            getMenuInflater().inflate(R.menu.my_page_menu,menu); // 로그인
        }
        else{
            getMenuInflater().inflate(R.menu.basic_menu,menu); // 비로그인
        }

        return true;
    }

    // 메뉴 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sign_in:
                // 로그인 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_sign_up:
                // 회원가입 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "회원가입", Toast.LENGTH_SHORT).show();
                return true;

            case R.menu.my_page_menu:
                // 마이페이지 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "회원가입", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 사용자 로그인 체크 메소드
     *
     * @return 로그인 : true / 비로그인 : false
     */
    private boolean isSignIn() {
        return false;
    }
}
