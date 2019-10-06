package com.oaojjj.bookmom.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.adapter.BookAdapter;
import com.oaojjj.bookmom.adapter.MyRecyclerAdapter;
import com.oaojjj.bookmom.models.BookItem;
import com.oaojjj.bookmom.models.BookMarkDB;
import com.oaojjj.bookmom.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends BaseActivity implements BookAdapter.MyRecyclerViewClickListener {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private BookMarkDB bookMarkDB;
    private TextView tvMyBookMark;

    public static List<String> bookTitleList;
    ArrayList<BookItem> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        recyclerView = findViewById(R.id.rv_rental);
        tvMyBookMark = findViewById(R.id.tv_my_book_mark);
        dataList= new ArrayList<>();
        Call<ResponseBody> check = RetrofitClient.getInstance().getApi().mypage(USER_ID);
        check.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.body().string());

                    JSONArray bookArray = jsonObject.getJSONArray("rental");

                    for (int i = 0; i < bookArray.length(); i++) {
                        JSONObject bookObject = bookArray.getJSONObject(i);
                       BookItem rental = new BookItem(bookObject.getString("title"), bookObject.getString("date"),"0",bookObject.getString("bno"));
                        dataList.add(rental);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter = new BookAdapter(dataList);
                adapter.setOnClickListener(MyPageActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        bookTitleList=new ArrayList<>();
        // 북마크한 책 제목 표시
        Call<ResponseBody> list = RetrofitClient.getInstance().getApi().list("",0);
        list.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.body().string());

                    JSONArray bookArray = jsonObject.getJSONArray("book");

                    for (int i = 0; i < bookArray.length(); i++) {
                        JSONObject bookObject = bookArray.getJSONObject(i);
                        bookTitleList.add(bookObject.getString("title"));
                    }
                    for (int i = 0; i < bookTitleList.size(); i++) {
                        if (bookMarkDB.isBookMark(bookTitleList.get(i))) {
                            if (i == 0) {
                                tvMyBookMark.setText(bookTitleList.get(i));
                            }
                            tvMyBookMark.setText(tvMyBookMark.getText() + "\n" + bookTitleList.get(i));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // 반납하기 눌렀을때
    @Override
    public void onItemButtonClick(int position) {

    }
}
