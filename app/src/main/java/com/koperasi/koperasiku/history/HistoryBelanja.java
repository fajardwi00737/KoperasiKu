package com.koperasi.koperasiku.history;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class HistoryBelanja extends AppCompatActivity {
    private TextView textHasil;
    private RequestQueue mQueue;
    String url = "http://192.168.5.25/transaksi/history.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_belanja);
        mQueue = Volley.newRequestQueue(this);
        textHasil = findViewById(R.id.tampiltransaksi);

        uraiJSON();
    }
    private void uraiJSON() {


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("belanja");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject anggota = jsonArray.getJSONObject(i);

                        String belanja = anggota.getString("id_belanja");
                        String barang = anggota.getString("nama_barang");
                        int jumlah = Integer.parseInt(String.valueOf(anggota.getInt("jumlah")));
                        int harga = Integer.parseInt(String.valueOf(anggota.getInt("harga_barang")));
                        String tanggal = anggota.getString("tanggal");
                        int total = jumlah * harga;

                        textHasil.append("Id Belanja : "+ belanja+ "\nNama Barang : "+ barang+ "\nJumlah : "+ jumlah + "\nTanggal : "+ tanggal+"\ntotal : " +total +"\n\n");
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
