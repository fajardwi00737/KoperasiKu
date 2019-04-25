package com.koperasi.koperasiku.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koperasi.koperasiku.R;
import com.koperasi.koperasiku.history.Anggota;
import com.koperasi.koperasiku.history.HistoryBelanja;
import com.koperasi.koperasiku.history.KeteranganInvestasi1;
import com.koperasi.koperasiku.history.KeteranganSimpanPinjam;
import com.koperasi.koperasiku.history.Produk;
import com.koperasi.koperasiku.history.Transaksi1;
import com.koperasi.koperasiku.input.Transaksi;

public class TransaksiFragment extends Fragment{
    CardView cv1,cv2,cv3,cv4,cv5,cv6;
    Activity context;
    public TransaksiFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        cv1 = (CardView)context.findViewById(R.id.cardview);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Produk.class);
                startActivity(intent);
            }
        });
        cv2 = (CardView)context.findViewById(R.id.cardview1);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Anggota.class);
                startActivity(intent);
            }
        });
        cv3 = (CardView)context.findViewById(R.id.cardview2);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Transaksi1.class);
                startActivity(intent);
            }
        });
        cv4 = (CardView)context.findViewById(R.id.cardview3);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,KeteranganInvestasi1.class);
                startActivity(intent);
            }
        });
        cv5 = (CardView)context.findViewById(R.id.cardview4);
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,KeteranganSimpanPinjam.class);
                startActivity(intent);
            }
        });
        cv6 = (CardView)context.findViewById(R.id.cardview5);
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,HistoryBelanja.class);
                startActivity(intent);
            }
        });
    }
}