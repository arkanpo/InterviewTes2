package com.arkanpo.interviewtes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arkanpo.interviewtes.api.DaftarAPI;
import com.arkanpo.interviewtes.api.Retroserver;
import com.arkanpo.interviewtes.api.UtilsAPI;
import com.arkanpo.interviewtes.model.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertActivity extends AppCompatActivity {

    EditText ed_nama,ed_kelas,ed_alamat,ed_tanggal;
    Button btn_input;

    private DaftarAPI daftarAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        daftarAPI = UtilsAPI.getdaftarAPI();

        ed_nama = findViewById(R.id.ed_nama);
        ed_kelas = findViewById(R.id.ed_kelas);
        ed_alamat = findViewById(R.id.ed_alamat);
        ed_tanggal = findViewById(R.id.ed_tanggal);
        btn_input = findViewById(R.id.btn_input);


        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String i_nama = ed_nama.getText().toString();
                String i_kelas = ed_kelas.getText().toString();
                String i_alamat = ed_alamat.getText().toString();
                String i_tanggal = ed_tanggal.getText().toString();
                dataAdd(i_nama,i_kelas,i_alamat,i_tanggal);
            }
        });
    }

    private void dataAdd(String nama,String kelas,String alamat,String tanggal){


        Call<Value> call = daftarAPI.adduser(nama,kelas,alamat,tanggal);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

    }
}
