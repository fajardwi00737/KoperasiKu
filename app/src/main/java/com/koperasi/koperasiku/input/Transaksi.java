package com.koperasi.koperasiku.input;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koperasi.koperasiku.InputTanggal;
import com.koperasi.koperasiku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transaksi extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
private EditText kebutuhan, petugas, tanggal;
Button input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        kebutuhan = (EditText)findViewById(R.id.etKebutuhan);
        petugas = (EditText)findViewById(R.id.etTanggal);
        input = (Button)findViewById(R.id.inputData);
        Button button = (Button) findViewById(R.id.btn_tanggal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new InputTanggal();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input();
            }
        });

    }

    private void input(){
        String url_regist = "http://192.168.5.25/input/input.php";
        final String kebutuhan = this.kebutuhan.getText().toString().trim();
        final String petugas = this.petugas.getText().toString().trim();
        final String tanggal = this.tanggal.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_regist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")){
                        Toast.makeText(Transaksi.this, "input berhasil ", Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(Transaksi.this, "input error eh ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Transaksi.this, "input error oyy ", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("daftar_kebutuhan_id", kebutuhan);
                params.put("petugas_id", petugas);
                params.put("tanggal", tanggal);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentdate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());

        tanggal = (EditText) findViewById(R.id.etTanggal);
        tanggal.setText(currentdate);
    }
}
