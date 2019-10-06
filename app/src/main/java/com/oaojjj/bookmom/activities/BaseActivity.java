package com.oaojjj.bookmom.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oaojjj.bookmom.R;

import java.util.ArrayList;

/**
 * Toolbar 가 필요한 모든 Activity 는 이것을 상속해야 한다.
 */
public class BaseActivity extends AppCompatActivity {

    public static SharedPreferences spfUser;
    public static SharedPreferences.Editor spfEditor;

    public static final String SHARED_USER = "user";
    public static String USER_NAME = "";
    public static String USER_ID = "";
    public static String USER_PASSWORD = "";
    public static ArrayList<Activity> actList = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_toolbar);
        spfUser = getApplicationContext().getSharedPreferences(SHARED_USER, getApplicationContext().MODE_PRIVATE);
        spfEditor = spfUser.edit();
        USER_ID = spfUser.getString("userID", "");
        USER_NAME = spfUser.getString("userName", "");

      /*  spfUser.getString(USER_ID,"");
        spfUser.getString(USER_NAME,"");*/
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
        setTitle("북맘");
    }

    // 메뉴 등록
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (useToolbar()) {
            if (isSignIn()) {
                getMenuInflater().inflate(R.menu.my_page_menu, menu); // 로그인
                menu.findItem(R.id.menu_user_page).setTitle(USER_NAME);
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
                Intent loginIntent = new Intent(this, SignInActivity.class);
                startActivity(loginIntent);
                return true;
            case R.id.menu_sign_up:
                // 회원가입 메뉴 클릭 이벤트 구현
                Toast.makeText(getApplicationContext(), "회원가입", Toast.LENGTH_SHORT).show();
                Intent registerIntent = new Intent(this, SignUpActivity.class);
                startActivity(registerIntent);
                return true;
            case R.id.menu_my_page:
                Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_SHORT).show();
                Intent myPageIntent = new Intent(this, MyPageActivity.class);
                startActivity(myPageIntent);
                return true;
            case R.id.menu_logout:
                USER_ID = "";
                if (isSignIn()) {
                    spfEditor.remove("userID");
                    spfEditor.remove("userName");
                    spfEditor.commit();
                    Toast.makeText(this, "자동로그인 취소", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(BaseActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                for (int j = 0; j < actList.size(); j++)
                    actList.get(j).finish();
                finish();
                startActivity(i);
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

    /**
     * 사용자 로그인 체크 메소드
     *
     * @return 로그인 : true / 비로그인 : false
     */
    String getUserName() {
        return USER_NAME;
    }

    void setUserName(String name) {
        spfEditor.putString(name,"userName");
        this.USER_NAME = name;
    }

    boolean isSignIn() {
        if (USER_ID.isEmpty())
            return false;
        else
            return true;
    }
}
