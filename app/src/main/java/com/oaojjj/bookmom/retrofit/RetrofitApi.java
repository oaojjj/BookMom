package com.oaojjj.bookmom.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseBody> insert(@Field("id") String id, @Field("password") String password, @Field("name") String name);

    @GET("chk1d.php")
    Call<ResponseBody> chk1d (@Query("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> login (@Field("id") String id,@Field("password") String password);

    @GET("list.php")
    Call<ResponseBody> list (@Query("title") String title,@Query("option") int option);

    @GET("mypage.php")
    Call<ResponseBody> mypage (@Query("id") String id);

    @GET("r_reg.php")
    Call<ResponseBody> r_reg (@Query("bno") String bno,@Query("id") String id ,@Query("date") String date);

    @GET("r_del.php")
    Call<ResponseBody> r_del (@Query("bno") String bno,@Query("id") String id );

    @GET("view.php")
    Call<ResponseBody> view (@Query("bno") String bno);
}
