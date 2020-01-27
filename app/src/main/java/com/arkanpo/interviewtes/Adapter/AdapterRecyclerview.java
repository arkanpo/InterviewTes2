package com.arkanpo.interviewtes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkanpo.interviewtes.DetailActivity;
import com.arkanpo.interviewtes.R;
import com.arkanpo.interviewtes.model.Value;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerview extends RecyclerView.Adapter<AdapterRecyclerview.MyViewHolder>  {

    Context context;
    ArrayList<Value> profiles;

    public AdapterRecyclerview(Context c, ArrayList<Value> p)
    {
        context = c;
        profiles = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.nama.setText(profiles.get(position).getNama());
        holder.kelas.setText(profiles.get(position).getKelas());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(context, DetailActivity.class);

                goDetail.putExtra("intent_id", profiles.get(position).getId());
                goDetail.putExtra("intent_nama", profiles.get(position).getNama());
                goDetail.putExtra("intent_kelas", profiles.get(position).getKelas());
                goDetail.putExtra("intent_alamat", profiles.get(position).getAlamat());
                goDetail.putExtra("intent_tanggal", profiles.get(position).getTanggal());

                context.startActivity(goDetail);

            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama,kelas;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.nama);
            kelas = (TextView) itemView.findViewById(R.id.kelas);
        }
    }

}
