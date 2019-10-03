package com.oaojjj.bookmom.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oaojjj.bookmom.R;

/**
 * Toolbar 가 필요한 모든 Activity 는 이것을 상속해야 한다.
 */
public class BaseActivity extends AppCompatActivity {

    private static String USER_NAME = "default";

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
        if (useToolbar()) {
            if (isSignIn()) {
                getMenuInflater().inflate(R.menu.my_page_menu, menu); // 로그인
                menu.findItem(R.id.menu_my_page).setTitle(USER_NAME);
            } else {
                getMenuInflater().inflate(R.menu.basic_menu, menu); // 비로그인
            }
        }
        return true;
    }


    // 메뉴 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_in:
                // 로그인 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
                Intent login_intent = new Intent(this, SignInActivity.class);
                startActivity(login_intent);
                return true;
            case R.id.menu_sign_up:
                // 회원가입 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "회원가입", Toast.LENGTH_SHORT).show();
                Intent register_intent = new Intent(this, SignUpActivity.class);
                startActivity(register_intent);
                return true;
            case R.id.menu_my_page:
                // 마이페이지 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_SHORT).show();
                Intent my_intent = new Intent(this, MyPageActivity.class);
                startActivity(my_intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 툴바의 사용 유무
     *
     * @return 사용 : true / 사용안함 : 오버라이딩해서 false 반환
     */
    protected boolean useToolbar() {
        return true;
    }


    //TODO 재우형

    /**
     * 사용자 로그인 체크 메소드
     *
     * @return 로그인 : true / 비로그인 : false
     */
    String getUserName(){
        return USER_NAME;
    }
    void setUserName(String name){
        this.USER_NAME=name;
    }
    boolean isSignIn() {
        if (!USER_NAME.equals("default"))
            return true;
        return false;
    }
}
