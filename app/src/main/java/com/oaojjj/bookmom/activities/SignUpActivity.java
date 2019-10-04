package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.retrofit.RetrofitClient;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    private EditText etUserName;
    private EditText etUserID;
    private EditText etUserPWD;
    private Button btSignUp;

    private boolean CHECKED_ID = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserName = findViewById(R.id.et_name);
        etUserID = findViewById(R.id.et_id);
        etUserPWD = findViewById(R.id.et_password);
        btSignUp = findViewById(R.id.btn_register);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USER_ID = etUserID.getText().toString().trim();
                USER_PASSWORD = etUserPWD.getText().toString().trim();
                USER_NAME = etUserName.getText().toString().trim();

                if(USER_NAME.isEmpty()){
                    etUserName.setError("이름을 입력하세요");
                    etUserName.requestFocus();
                    return;
                }
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

                Call<ResponseBody> checkID = RetrofitClient.getInstance().getApi().chk1d(USER_ID);// 입력받은 id값 넣고 checkid 버튼을 눌럿을때 실행
                checkID.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String a = response.body().toString();
                        if (a.contentEquals("0"))
                            CHECKED_ID = true;
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                }); //이까지 check id 버튼눌럿을때 동작

                if (CHECKED_ID) {
                    // 회원가입 눌렀을때 실행 되게하기 id password, name 순서대로 입력받은 값을 넣고,chkid 값이 true가 되면 회원가입 완료
                    Call<ResponseBody> signUp = RetrofitClient.getInstance().getApi().insert(USER_ID, USER_PASSWORD, USER_NAME);
                    spfUser = getApplicationContext().getSharedPreferences("userID",getApplicationContext().MODE_PRIVATE);
                    spfEditor = spfUser.edit();
                }
                else{
                    Toast.makeText(SignUpActivity.this, "중복되는 id가 존재합니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected boolean useToolbar() {
        return false;
    }
}
