package com.oaojjj.bookmom.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.oaojjj.bookmom.R;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class CustomDialog extends Dialog {

    private Context mContext;

    private TextView title;
    private TextView userName;
    private Button btPositive;
    private Button btNegative;
    private DatePicker datePicker;

    private Calendar calendar;
    private String mTitle;


    private String resultDate;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    private int year;

    public CustomDialog(@NonNull Context context, String title) {
        super(context);
        mContext = context;
        mTitle = title;
    }

    public void setListener(View.OnClickListener mPositiveListener, View.OnClickListener mNegativeListener) {
        this.mPositiveListener = mPositiveListener;
        this.mNegativeListener = mNegativeListener;
    }

    public String getResultDate() {
        return resultDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);


        title = findViewById(R.id.tv_dialog_title);
        userName = findViewById(R.id.tv_user_name);

        btPositive = findViewById(R.id.bt_positive);
        btNegative = findViewById(R.id.bt_negative);

        btPositive.setOnClickListener(mPositiveListener);
        btNegative.setOnClickListener(mNegativeListener);

        datePicker = findViewById(R.id.dp_date);

        title.setText(mTitle);

        // 현재 년도 가져오기
        calendar = Calendar.getInstance();
        year = calendar.get(calendar.YEAR);

        // 최대 6개월 대여 가능
        datePicker.setMinDate(System.currentTimeMillis());
        datePicker.setMaxDate(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(180));

        // 최종 대여 날짜 저장
        resultDate = String.format("%s-%s-%s", datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());

    }

}


       /* // 다이얼로그 밖의 화면을 흐리게 만듬
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        getWindow().setAttributes(layoutParams);*/

