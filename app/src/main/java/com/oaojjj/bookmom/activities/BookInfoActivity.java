package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookMarkDB;
import com.oaojjj.bookmom.retrofit.RetrofitClient;
import com.oaojjj.bookmom.utils.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookInfoActivity extends BaseActivity {

    private TextView tvTitle, tvCategory;
    private Button btBookRental, btBookDetail;
    private ImageButton ibBookMark;

    private CustomDialog customDialog;
    private String rental, bno;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    BookMarkDB bookMarkDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        actList.add(this);
        tvTitle = findViewById(R.id.tv_book_title);
        tvCategory = findViewById(R.id.tv_book_category);
        ibBookMark = findViewById(R.id.ib_book_mark);
        btBookRental = findViewById(R.id.bt_book_rental);

        //TODO 인텐트해서 책제목 넘기거나 해서 책제목을 파라미터로 넘겨야함
        bookMarkDB = new BookMarkDB(getApplicationContext());

        // TODO 재우형 상세정보 보기에 웹뷰연동?
        // WebView 구현해서 책 제목을 넘겨서 책에 대한 정보페이지를 웹으로 나타낸다.
        btBookDetail = findViewById(R.id.bt_book_detail);

        Intent intent = getIntent();
        bno = intent.getExtras().getString("bno");
        Call<ResponseBody> check = RetrofitClient.getInstance().getApi().view(bno);
        check.enqueue(new Callback<ResponseBody>() {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                              try {

                                  JSONObject jsonObject = new JSONObject(response.body().string());

                                  JSONArray bookArray = jsonObject.getJSONArray("book");
                                  JSONObject bookObject = bookArray.getJSONObject(0);
                                  tvTitle.setText(bookObject.getString("title"));
                                  tvCategory.setText(bookObject.getString("kind"));
                                  rental = bookObject.getString("available");
                                  if (!isSignIn() || rental.contentEquals("1")) {
                                          btBookRental.setEnabled(false);
                                          ibBookMark.setEnabled(false);
                                  }
                                  if (bookMarkDB.isBookMark(tvTitle.getText().toString())) {
                                      ibBookMark.setImageResource(R.drawable.ic_bookmark_black_24dp);
                                  } else {
                                      ibBookMark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                                  }
                              } catch (JSONException e) {
                                  e.printStackTrace();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          }

                          @Override
                          public void onFailure(Call<ResponseBody> call, Throwable t) {

                          }
                      });

                //TODO 재우형 이것도 테스트가 불가능해서 테스트좀 해줘용~ 파라미터는 전부 책 제목
                ibBookMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 반대로 이미 북마크에 추가 되었는데 눌렀을때 버튼 배경이 바뀌면서 북마크 remove 호출
                        // 버튼 배경은 book_mark_background 에 정의됨
                        if (bookMarkDB.isBookMark(tvTitle.getText().toString())) {
                            ibBookMark.setImageResource(R.drawable.ic_bookmark_border_black_24dp); // 클릭 안되었을 때는 빈배경
                            bookMarkDB.removeBookMark(tvTitle.getText().toString()); // 북마크 삭제
                        } else {
                            ibBookMark.setImageResource(R.drawable.ic_bookmark_black_24dp); // 클릭 되었을 때는 검은색 배경
                            bookMarkDB.addBookMark(tvTitle.getText().toString()); // 북마크 추가
                        }
                    }
                });

        // 대여 버튼 다이얼로그 생성
        btBookRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(BookInfoActivity.this, tvTitle.getText().toString(), getUserName());
                customDialog.setListener(mPositiveListener, mNegativeListener);
                customDialog.show();
            }
        });

        // 다이얼로그
        // 완료 버튼
        mPositiveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> reg = RetrofitClient.getInstance().getApi().r_reg(bno, USER_ID, customDialog.getResultDate());
                reg.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String a = response.body().toString();
                        if (a.contentEquals("0"))
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
        }

        ;

        // 취소 버튼
        mNegativeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookInfoActivity.this, "취소", Toast.LENGTH_SHORT).show();
                customDialog.dismiss();
            }
        }

        ;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

