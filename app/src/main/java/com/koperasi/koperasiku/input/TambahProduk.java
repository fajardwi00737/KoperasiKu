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

import com.koperasi.koperasiku.R;
import com.koperasi.koperasiku.history.Anggota;
import com.koperasi.koperasiku.history.Produk;

import java.util.HashMap;

public class TambahProduk extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextBarang;
    private EditText editTextHarga;
    private EditText editTextJual;
    private EditText editTextStok;


    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);
        editTextBarang = (EditText) findViewById(R.id.editTextBarang);
        editTextHarga = (EditText) findViewById(R.id.editTextHarga);
        editTextJual = (EditText) findViewById(R.id.editTextJual);
        editTextStok = (EditText) findViewById(R.id.editTextStok);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }
    private void addEmployee(){

        final String name = editTextBarang.getText().toString().trim();
        final String daerah = editTextHarga.getText().toString().trim();
        final String kamar = editTextJual.getText().toString().trim();
        final String saldo = editTextStok.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahProduk.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahProduk.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA_B,name);
                params.put(konfigurasi.KEY_EMP_HARGA_B,daerah);
                params.put(konfigurasi.KEY_EMP_JUAL_B,kamar);
                params.put(konfigurasi.KEY_EMP_STOK_B,saldo);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_B, params);
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
            startActivity(new Intent(this,Produk.class));
        }
    }
}
