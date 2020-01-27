package com.arkanpo.interviewtes.api;

import com.arkanpo.interviewtes.model.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DaftarAPI {
    String URL = "https://borneo.litebig.co.id:8088/tmoney/index.php/";


    @GET("getDummy")
    Call<List<Value>> view();

    @FormUrlEncoded
    @POST("insertDummy")
    Call<Value>  adduser(
                        @Field("nama") String nama,
                        @Field("kelas") String kelas,
                        @Field("alamat") String alamat,
                        @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("delDummy")
    Call<Value>  deleteuser(
            @Field("id") String id);


    @FormUrlEncoded
    @POST("updateDummy")
    Call<Value>  editdata(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("kelas") String kelas,
            @Field("alamat") String alamat,
            @Field("tanggal") String tanggal);


}
