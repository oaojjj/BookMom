package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oaojjj.bookmom.MyRecyclerAdapter;
import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookItem;
import com.oaojjj.bookmom.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Call<ResponseBody> signin = RetrofitClient.getInstance().getApi().login("", ""); // id , password 입력받은값 순서대로 넣기 로그인 버튼 눌럿을때 수행
        signin.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                String a=response.body().string();
                Log.d("a",a);
                if (a.equals("1")) // 1이면 로그인 가능
                {
                    setUserName(""); //id값 입력
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    SignInActivity.this.startActivity(intent);
                    finish();
                }}
                catch(Exception e){}
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
