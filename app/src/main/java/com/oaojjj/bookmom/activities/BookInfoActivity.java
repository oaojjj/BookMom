package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oaojjj.bookmom.MyRecyclerAdapter;
import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookItem;
import com.oaojjj.bookmom.retrofit.RetrofitClient;
import com.oaojjj.bookmom.utils.CustomDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookInfoActivity extends BaseActivity {

    private TextView tvTitle,tvCategory;
    private Button btBookRental;
    private ImageButton ibBookMark;

    private CustomDialog customDialog;
    private String rental,bno;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    // TODO 재우형
    // 북마크 추가 -> true , 북마크 없음 -> false
    // 이 변수에 사용자 북마크 정보를 저장아니면 따로 모델클래스 만들어도 상관 없음
    boolean CHECK_BOOK_MARK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        tvTitle = findViewById(R.id.tv_book_title);
        tvCategory = findViewById(R.id.tv_book_category);
        btBookRental = findViewById(R.id.bt_book_rental);
        ibBookMark = findViewById(R.id.ib_book_mark); // WebView 구현해서 책 제목을 넘겨서 책에 대한 정보페이지를 웹으로 나타낸다.
        Intent intent = getIntent();
        bno=intent.getExtras().getString("bno");
        Call<ResponseBody> check= RetrofitClient.getInstance().getApi().view(bno);
        check.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{

                    JSONObject jsonObject = new JSONObject(response.body().string());

                    JSONArray bookArray = jsonObject.getJSONArray("book");
                    JSONObject bookObject = bookArray.getJSONObject(0);
                    tvTitle.setText(bookObject.getString("title"));
                    tvCategory.setText(bookObject.getString("kind"));
                    rental=bookObject.getString("available");
                    if(!isSignIn()||rental.contentEquals("1")){
                        btBookRental.setEnabled(false);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        // 북마크 버튼
        ibBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CHECK_BOOK_MARK) {
                    ibBookMark.setSelected(true);
                } else {
                    ibBookMark.setSelected(false);
                }
            }
        });

        // 대여 버튼 다이얼로그 생성
        btBookRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(BookInfoActivity.this, tvTitle.getText().toString(),getUserName());
                customDialog.setListener(mPositiveListener, mNegativeListener);
                customDialog.show();
            }
        });

        // 다이얼로그
        // 완료 버튼
        mPositiveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> reg= RetrofitClient.getInstance().getApi().r_reg(bno,getUserName(),customDialog.getResultDate());
                reg.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String a=response.body().toString();
                        if(a.contentEquals("0"))
                            Log.d("T", "onResponse: ");
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                Toast.makeText(BookInfoActivity.this, "대여 완료!", Toast.LENGTH_SHORT).show();
                btBookRental.setEnabled(false);
                customDialog.dismiss();
            }
        };

        // 취소 버튼
        mNegativeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookInfoActivity.this, "취소", Toast.LENGTH_SHORT).show();
                customDialog.dismiss();
            }
        };

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

