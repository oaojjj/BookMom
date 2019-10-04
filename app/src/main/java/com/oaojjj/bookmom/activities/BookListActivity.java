package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oaojjj.bookmom.MyRecyclerAdapter;
import com.oaojjj.bookmom.R;
import com.oaojjj.bookmom.models.BookItem;
import com.oaojjj.bookmom.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BookListActivity extends BaseActivity implements MyRecyclerAdapter.MyRecyclerViewClickListener {

    public static final int REQUEST_CODE = 1000;

    private TextView tvBookCount;

    private Spinner spinner;
    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;

    List<BookItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        adapter = new MyRecyclerAdapter(getApplicationContext());

        tvBookCount = findViewById(R.id.tv_book_count);

        spinner = findViewById(R.id.sp_book);

        recyclerView = findViewById(R.id.rv_book_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        dataList = new ArrayList<>();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) { // i -> 0 전체, 1 대여가능, 2 대여중
                dataList.clear();
                Call<ResponseBody> check = RetrofitClient.getInstance().getApi().list("", i);
                check.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response.body().string());

                            JSONArray bookArray = jsonObject.getJSONArray("book");

                            for (int i = 0; i < bookArray.length(); i++) {
                                JSONObject bookObject = bookArray.getJSONObject(i);
                                BookItem book;
                                if (bookObject.getString("available").contentEquals("0"))
                                    book = new BookItem(bookObject.getString("title"), bookObject.getString("kind"), "대여가능", bookObject.getString("bno"));
                                else
                                    book = new BookItem(bookObject.getString("title"), bookObject.getString("kind"), "대여중", bookObject.getString("bno"));

                                dataList.add(book);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tvBookCount.setText("( " + dataList.size() + " )");
                        adapter = new MyRecyclerAdapter(dataList);
                        adapter.setOnClickListener(BookListActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
    }

    // Item을 클릭 했을 때
    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(BookListActivity.this, BookInfoActivity.class);
        intent.putExtra("bno", dataList.get(position).getbno());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int REQUEST_CODE, int resultCode, Intent data) {
        super.onActivityResult(REQUEST_CODE, resultCode, data);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
