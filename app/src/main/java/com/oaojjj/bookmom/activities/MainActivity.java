package com.oaojjj.bookmom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.oaojjj.bookmom.R;

public class MainActivity extends BaseActivity {

    private ImageButton btMyPage;
    private ImageButton btList;
    Button bt_search;
    EditText search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actList.add(this);
        btMyPage=findViewById(R.id.btn_my_page);
        btList=findViewById(R.id.btn_list);
        bt_search=findViewById(R.id.bt_search1);
        search_text=findViewById(R.id.et_search1);

        if(USER_ID.isEmpty()){
            Log.d("USER_ID_TEST","TEST");
        }

        if(isSignIn()){
            btMyPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MyPageActivity.class); // 액티비티 이동
                    startActivity(intent);
                }
            });
        }else{
            btMyPage.setClickable(false);
        }


        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class); // 액티비티 이동
                startActivity(intent);
            }
        });
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class); // 액티비티 이동
                intent.putExtra("title",search_text.getText().toString());
                startActivity(intent);
            }
        });
    }
    @Override
    public void onActivityResult(int REQUEST_CODE,int resultCode,Intent data){
        super.onActivityResult(REQUEST_CODE,resultCode,data);
        Intent intent =getIntent();
        finish();
        startActivity(intent);
    }
}
