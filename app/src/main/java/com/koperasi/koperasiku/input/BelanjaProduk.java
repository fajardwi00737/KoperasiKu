package com.koperasi.koperasiku.input;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.koperasi.koperasiku.history.Anggota;
import com.koperasi.koperasiku.history.HistoryBelanja;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BelanjaProduk extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextName;
    private EditText editTextDaerah;
    private EditText editTextKamar;
    private EditText editTextTotal;


    private Button buttonAdd;
    private Button buttonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja_produk);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDaerah = (EditText) findViewById(R.id.editTextDaerah);
        editTextKamar = (EditText) findViewById(R.id.editTextKamar);
        editTextTotal = (EditText) findViewById(R.id.editTextTotal);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    private void addEmployee(){

        final String name = editTextName.getText().toString().trim();
        final String daerah = editTextDaerah.getText().toString().trim();
        final String kamar = editTextKamar.getText().toString().trim();
        final String total = editTextTotal.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BelanjaProduk.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(BelanjaProduk.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA_BE,name);
                params.put(konfigurasi.KEY_EMP_JUMLAH_BE,daerah);
                params.put(konfigurasi.KEY_EMP_TANGGAL_BE,kamar);
                params.put(konfigurasi.KEY_EMP_TOTAL_BE,total);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_BE, params);
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
            startActivity(new Intent(this,HistoryBelanja.class));
            finish();
        }
    }
}
