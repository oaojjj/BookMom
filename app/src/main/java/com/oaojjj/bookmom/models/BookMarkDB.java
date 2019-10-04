package com.oaojjj.bookmom.models;

import android.content.Context;
import android.content.SharedPreferences;

public class BookMarkDB {
    public final static String SHARED_KEY = "BookMark";

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public BookMarkDB(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_KEY,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences(){return sharedPreferences;}

    public SharedPreferences.Editor getEditor(){ return editor;}

    public void addBookMark(String bookTitle){
        editor.putBoolean(bookTitle,true);
        editor.commit();
    }

    public void removeBookMark(String bookTitle){
        editor.remove(bookTitle);
        editor.commit();
    }

    public Boolean isBookMark(String bookTitle){
        Boolean flag = sharedPreferences.getBoolean(bookTitle,false);
        return flag;
    }


}
