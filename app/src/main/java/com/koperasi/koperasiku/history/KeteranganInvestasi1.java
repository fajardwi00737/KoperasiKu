package com.koperasi.koperasiku.history;

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

public class KeteranganInvestasi1 extends AppCompatActivity {
    private TextView textHasil;
    private RequestQueue mQueue;
    String url = "http://192.168.5.25/transaksi/tampil.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterangan_investasi1);
        mQueue = Volley.newRequestQueue(this);
        textHasil = findViewById(R.id.tampiltransaksi);

        uraiJSON();
    }
    private void uraiJSON() {


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("simpan_uang");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject anggota = jsonArray.getJSONObject(i);

                        String transaksi = anggota.getString("id_simpan");
                        String kebutuhan = anggota.getString("nama_anggota");
                        String petugas = anggota.getString("tanggal");
                        String jumlah = anggota.getString("jumlah");

                        textHasil.append("Id Transaksi : "+ transaksi+ "\nNama anggota : "+ kebutuhan+ "\nNama Petugas : "+ petugas + "\nTanggal : "+ jumlah+"\n\n");
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
