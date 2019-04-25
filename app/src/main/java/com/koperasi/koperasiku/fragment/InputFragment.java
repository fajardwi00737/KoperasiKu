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
import com.koperasi.koperasiku.input.BelanjaProduk;
import com.koperasi.koperasiku.input.KeteranganInvestasi;
import com.koperasi.koperasiku.input.KeteranganPinjam;
import com.koperasi.koperasiku.input.Transaksi;

public class InputFragment extends Fragment{
    CardView cv1,cv2,cv3,cv4;
    Activity context;
    public InputFragment() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }
    public void onStart() {
        super.onStart();
        cv1 = (CardView)context.findViewById(R.id.cardView);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Transaksi.class);
                startActivity(intent);
            }
        });
        cv2 = (CardView)context.findViewById(R.id.cardView1);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,KeteranganInvestasi.class);
                startActivity(intent);
            }
        });
        cv3 = (CardView)context.findViewById(R.id.cardView2);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,KeteranganPinjam.class);
                startActivity(intent);
            }
        });
        cv4 = (CardView)context.findViewById(R.id.cardView3);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BelanjaProduk.class);
                startActivity(intent);
            }
        });
    }
}

