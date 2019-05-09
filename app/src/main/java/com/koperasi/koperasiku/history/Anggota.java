package com.koperasi.koperasiku.history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.koperasi.koperasiku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Anggota extends AppCompatActivity {
    CardView coba;
    private TextView textHasil;
    private RequestQueue mQueue;

    String url = "http://192.168.5.25/input1/input.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota);

        coba = (CardView)findViewById(R.id.cardViewcoba);
        mQueue = Volley.newRequestQueue(this);
        textHasil = findViewById(R.id.isidata);

        uraiJSON();

    }
    private void uraiJSON  (){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("anggota");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject anggota = jsonArray.getJSONObject(i);

                        String idanggota = anggota.getString("id_anggota");
                        String namaanggota = anggota.getString("nama_anggota");
                        String asaldaerah = anggota.getString("asal_daerah");
                        String kamar = anggota.getString("kelompok_kamar");
                        String saldo = anggota.getString("sisa_saldo");
                        textHasil.append("id anggota : "+ idanggota+ "\nnama anggota : "+ namaanggota+ "\nAsal Daerah : "+ asaldaerah + "\nKamar : "+ kamar+"\nsisa saldo : "+saldo+"\n\n");
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error oyy", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }
}
