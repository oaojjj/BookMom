package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

/**
 * 로그인 화면
 */
public class SignInActivity extends BaseActivity{

    final static private String SIGN_IN_CHECK = "1";

    private EditText etUserID;
    private EditText etUserPWD;
    private CheckBox chAutoSignIn;
    private Button btSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUserID = findViewById(R.id.et_id);
        etUserPWD = findViewById(R.id.et_pwd);
        chAutoSignIn = findViewById(R.id.cb_auto_sign_in);
        btSignIn = findViewById(R.id.btn_login);

        // 로그인 버튼
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USER_ID = etUserID.getText().toString().trim();
                USER_PASSWORD = etUserPWD.getText().toString().trim();

                if (USER_ID.isEmpty()){
                    etUserID.setError("아이디를 입력하세요");
                    etUserID.requestFocus();
                    return;
                }
                if(USER_PASSWORD.isEmpty()){
                    etUserPWD.setError("비밀번호를 입력하세요");
                    etUserPWD.requestFocus();
                    return;
                }

                Call<ResponseBody> signIn = RetrofitClient.getInstance().getApi().login(USER_ID, USER_PASSWORD); // id , password 입력받은값 순서대로 넣기 로그인 버튼 눌럿을때 수행

                signIn.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String flag = response.body().string();
                            Log.d("SignActivity_flag", flag);
                            // flag 1 -> 로그인 가능
                            //      0 -> 로그인 불가능

                            if (flag.equals(SIGN_IN_CHECK))
                            {
                                //자동로그인 체크시
                                if(chAutoSignIn.isChecked()){
                                    spfUser = getApplicationContext().getSharedPreferences("userID",getApplicationContext().MODE_PRIVATE);
                                    spfEditor = spfUser.edit();
                                    spfEditor.putString(USER_ID,"userID");
                                    spfEditor.commit();
                                }

                                setUserName(USER_NAME); //id값 입력

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(SignInActivity.this, "아이디 또는 비밀번호를 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.d("SignActivity_e",e.toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("SignActivity_t",t.toString());
                    }
                });

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
