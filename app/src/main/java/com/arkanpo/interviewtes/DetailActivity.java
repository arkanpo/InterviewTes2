package com.arkanpo.interviewtes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arkanpo.interviewtes.api.DaftarAPI;
import com.arkanpo.interviewtes.api.UtilsAPI;
import com.arkanpo.interviewtes.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private String intent_id = "",intent_nama = "",intent_kelas = "",intent_alamat = "",intent_tanggal = "";

    EditText ed_nama,ed_kelas,ed_alamat,ed_tanggal;
    Button btn_delete,btn_update;

    private DaftarAPI daftarAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        daftarAPI = UtilsAPI.getdaftarAPI();

        ed_nama = findViewById(R.id.ed_nama);
        ed_kelas = findViewById(R.id.ed_kelas);
        ed_alamat = findViewById(R.id.ed_alamat);
        ed_tanggal = findViewById(R.id.ed_tanggal);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);

        intent_id= getIntent().getStringExtra("intent_id");
        intent_nama= getIntent().getStringExtra("intent_nama");
        intent_kelas= getIntent().getStringExtra("intent_kelas");
        intent_alamat= getIntent().getStringExtra("intent_alamat");
        intent_tanggal= getIntent().getStringExtra("intent_tanggal");

        Detaildata();

        Toast.makeText(DetailActivity.this,intent_nama,Toast.LENGTH_SHORT).show();


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_nama = ed_nama.getText().toString();
                String edt_kelas = ed_kelas.getText().toString();
                String edt_alamat = ed_alamat.getText().toString();
                String edt_tanggal = ed_tanggal.getText().toString();
                UpdateData(intent_id,edt_nama,edt_kelas,edt_alamat,edt_tanggal);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletedata(intent_id);
            }
        });



    }

    private void Detaildata(){
        ed_nama.setText(intent_nama);
        ed_kelas.setText(intent_kelas);
        ed_alamat.setText(intent_alamat);
        ed_tanggal.setText(intent_tanggal);
    }

    private void Deletedata(String id) {

      Call<Value> call = daftarAPI.deleteuser(intent_id);
      call.enqueue(new Callback<Value>() {
          @Override
          public void onResponse(Call<Value> call, Response<Value> response) {
              Toast.makeText(DetailActivity.this, "Data berhasil di hapus", Toast.LENGTH_SHORT).show();
              finish();
          }

          @Override
          public void onFailure(Call<Value> call, Throwable t) {
              Toast.makeText(DetailActivity.this, "Gagal Delete", Toast.LENGTH_SHORT).show();
          }
      });

    }

    private void UpdateData(String id,String nama,String kelas,String alamat,String tanggal){
        Call<Value> call = daftarAPI.editdata(intent_id,nama,kelas,alamat,tanggal);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Toast.makeText(DetailActivity.this, "Data berhasil di Update", Toast.LENGTH_SHORT).show();
                Intent abc = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(abc);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Gagal Edit", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
