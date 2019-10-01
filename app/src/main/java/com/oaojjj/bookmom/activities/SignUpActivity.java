package com.oaojjj.bookmom.activities;

import android.os.Bundle;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.retrofit.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;

//TODO 주은
/**
 * 회원가입 화면
 */
public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Call<ResponseBody> check= RetrofitClient.getInstance().getApi().insert("asdf","1123","dfasfd");
    }

    @Override
    protected boolean useToolbar() {
        return false;
    }
}
