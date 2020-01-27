package com.arkanpo.interviewtes.api;

public class UtilsAPI {

    public static final String BASE_ROOT_URL = "https://borneo.litebig.co.id:8088/tmoney/index.php/";

    public static DaftarAPI getdaftarAPI() {
        return RetrofitClient.getClient(BASE_ROOT_URL).create(DaftarAPI.class);
    }

}
