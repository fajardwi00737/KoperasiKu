package com.koperasi.koperasiku.input;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koperasi.koperasiku.R;
import com.koperasi.koperasiku.history.Anggota;
import com.koperasi.koperasiku.history.KeteranganInvestasi1;
import com.koperasi.koperasiku.history.KeteranganSimpanPinjam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class KeteranganPinjam extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextId;
    private EditText editTextTanngal;
    private EditText editTextJumlah;



    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterangan_pinjam);
        editTextId = (EditText) findViewById(R.id.editTextID);
        editTextTanngal = (EditText) findViewById(R.id.editTextTanggal);
        editTextJumlah = (EditText) findViewById(R.id.editTextJumlah);


        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }
    private void addEmployee(){

        final String id = editTextId.getText().toString().trim();
        final String tanggal = editTextTanngal.getText().toString().trim();
        final String jumlah = editTextJumlah.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KeteranganPinjam.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(KeteranganPinjam.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA_P,id);
                params.put(konfigurasi.KEY_EMP_TANGGAL_P,tanggal);
                params.put(konfigurasi.KEY_EMP_JUMLAH_P,jumlah);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_P, params);
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
            startActivity(new Intent(this,KeteranganSimpanPinjam.class));
            finish();
        }
    }
}
