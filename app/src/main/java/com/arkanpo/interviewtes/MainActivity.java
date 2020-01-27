package com.arkanpo.interviewtes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arkanpo.interviewtes.Adapter.AdapterRecyclerview;
import com.arkanpo.interviewtes.api.DaftarAPI;
import com.arkanpo.interviewtes.model.Value;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Value> values;
    AdapterRecyclerview viewAdapter;
    Button btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btn_tambah = findViewById(R.id.btn_tambah);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        values = new ArrayList<Value>();

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc =new Intent(MainActivity.this,InsertActivity.class);
                startActivity(abc);
            }
        });

        getRespon();

    }

    private void getRespon(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DaftarAPI.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        DaftarAPI daftarAPI = retrofit.create(DaftarAPI.class);
        Call<List<Value>> call = daftarAPI.view();
        call.enqueue(new Callback<List<Value>>() {
            @Override
            public void onResponse(Call<List<Value>> call, Response<List<Value>> response) {
                values = new ArrayList<>(response.body());
                viewAdapter = new AdapterRecyclerview(MainActivity.this,values);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<List<Value>> call, Throwable t) {

            }
        });

    }
}
