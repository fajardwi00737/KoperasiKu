package com.koperasi.koperasiku.input;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
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
import com.koperasi.koperasiku.BackgroundTask;
import com.koperasi.koperasiku.InputTanggal;
import com.koperasi.koperasiku.R;
import com.koperasi.koperasiku.history.Transaksi1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transaksi extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextIdA;
    private EditText editTextIdB;
    private EditText editTextJumlah;
    private EditText editTextSatuan;
    private EditText editTextTotal;
    private EditText editTextTanggal;
    private EditText editTextIdP;

    private Button buttonAdd;
    private Button buttonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        editTextIdA = (EditText) findViewById(R.id.editTextIdA);
        editTextIdB= (EditText) findViewById(R.id.editTextIdB);
        editTextJumlah = (EditText) findViewById(R.id.editTextJumlah);
        editTextSatuan = (EditText) findViewById(R.id.editTextSatuan);
        editTextTotal = (EditText) findViewById(R.id.editTextTotal);
        editTextTanggal = (EditText) findViewById(R.id.editTextTanggal);
        editTextIdP = (EditText) findViewById(R.id.editTextIdP);


        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    private void addEmployee(){

        final String IdA = editTextIdA.getText().toString().trim();
        final String IdB = editTextIdB.getText().toString().trim();
        final String Jumlah = editTextJumlah.getText().toString().trim();
        final String Satuan = editTextSatuan.getText().toString().trim();
        final String Total = editTextTotal.getText().toString().trim();
        final String Tanggal = editTextTanggal.getText().toString().trim();
        final String IdP = editTextIdP.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Transaksi.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Transaksi.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA_T,IdA);
                params.put(konfigurasi.KEY_EMP_KEBUTUHAN_T,IdB);
                params.put(konfigurasi.KEY_EMP_JUMLAH_T,Jumlah);
                params.put(konfigurasi.KEY_EMP_SATUAN_T,Satuan);
                params.put(konfigurasi.KEY_EMP_TOTAL_T,Total);
                params.put(konfigurasi.KEY_EMP_TANGGAL_T,Tanggal);
                params.put(konfigurasi.KEY_EMP_PETUGAS_T,IdP);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_T, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,Transaksi1.class));
            finish();
        }
    }
}
