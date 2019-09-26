package com.oaojjj.bookmom.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oaojjj.bookmom.R;

//TODO 주은
/**
 * 로그인 화면
 */
public class SignInActivity extends BaseActivity {

    SharedPreferences preferencesUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }


    // 자동로그인 체크시 SharedPreferences 에 유저정보를 입력하여 내부저장소에 저장
    public void onAutoSignInClick(View view) {
        Toast.makeText(this, "자동로그인", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean useToolbar() {
        return false;
    }
}
