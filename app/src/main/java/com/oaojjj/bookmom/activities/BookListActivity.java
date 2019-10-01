package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.MyRecyclerAdapter;
import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookItem;
import com.oaojjj.bookmom.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookListActivity extends BaseActivity implements MyRecyclerAdapter.MyRecyclerViewClickListener {

    public static final int REQUEST_CODE = 1000;

    private TextView tvBookCount;

    private Spinner spinner;
    private RecyclerView recyclerView;

    private MyRecyclerAdapter adapter;
    private List<BookItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        tvBookCount =findViewById(R.id.tv_book_count);

        spinner = findViewById(R.id.sp_book);

        recyclerView = findViewById(R.id.rv_book_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        dataList = new ArrayList<>();


        // 임시 데이터
        dataList.add(new BookItem("리눅스 프로그래밍", "리눅스", "대여 가능"));
        dataList.add(new BookItem("c언어 프로그래밍", "프로그래밍 언어", "대여중"));
        dataList.add(new BookItem("자바 프로그래밍", "프로그래밍 언어", "대여중"));
        dataList.add(new BookItem("자료구조", "프로그래밍", "대여중"));
        dataList.add(new BookItem("미분적분학", "수학", "대여 가능"));

        // 책개수
        tvBookCount.setText("( "+dataList.size()+" )");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { // i -> 1 전체, 2 대여가능, 3 대여중
                //TODO 재우형 상황에 맞는 아이템이 나오도록 구현
                Call<ResponseBody> check= RetrofitClient.getInstance().getApi().list("","1");
                check.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("test",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                Toast.makeText(BookListActivity.this, String.valueOf(adapterView.getItemAtPosition(i)), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(this);

    }

    // Item을 클릭 했을 때
    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(BookListActivity.this,BookInfoActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
