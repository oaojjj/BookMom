package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.util.Log;

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
 * 회원가입 화면
 */
public class SignUpActivity extends BaseActivity {
    boolean chkid =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Call<ResponseBody> signup = RetrofitClient.getInstance().getApi().insert("","","");// 회원가입 눌렀을때 실행 되게하기 id password, name 순서대로 입력받은 값을 넣고,chkid 값이 true가 되면 회원가입 완료
        Call<ResponseBody> checkid= RetrofitClient.getInstance().getApi().chk1d("");// 입력받은 id값 넣고 checkid 버튼을 눌럿을때 실행
        checkid.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String a=response.body().toString();
                if(a.contentEquals("0"))
                    chkid=true;
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        }); //이까지 check id 버튼눌럿을때 동작
    }

    @Override
    protected boolean useToolbar() {
        return false;
    }
}
