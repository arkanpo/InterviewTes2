package com.arkanpo.interviewtes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver
{
    private static final String  base_url = "https://borneo.litebig.co.id:8088/tmoney/index.php/";

    private static Retrofit retrofit;

    public static Retrofit getClinet() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit;

    }
}
