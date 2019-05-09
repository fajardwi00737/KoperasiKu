package com.koperasi.koperasiku.input;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koperasi.koperasiku.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BelanjaProduk extends AppCompatActivity {
    private EditText namabarang, jumlahbarang, tanggal, total;
    private Button input;
    private static String URL_REGIST = "http://192.168.5.25/loginPHP/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja_produk);

        namabarang = findViewById(R.id.etNamaBarang);
        jumlahbarang = findViewById(R.id.etjmlhbarang);
        tanggal = findViewById(R.id.ettanggal);
        total = findViewById(R.id.ettotal);
        input = findViewById(R.id.input);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input();
            }
        });
    }

    private void input() {
        final String nama = this.namabarang.getText().toString().trim();
        final String jumlah = this.jumlahbarang.getText().toString().trim();
        final String tanggal = this.tanggal.getText().toString().trim();
        final String total = this.total.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        if(success.equals("1")){
                            Toast.makeText(BelanjaProduk.this, "Input Success", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BelanjaProduk.this, "Input gagal" , Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BelanjaProduk.this, "Input gagal oy", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("belanja_id",nama);
                params.put("jumlah",jumlah);
                params.put("tanggal", tanggal);
                params.put("total_belanja", total);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
