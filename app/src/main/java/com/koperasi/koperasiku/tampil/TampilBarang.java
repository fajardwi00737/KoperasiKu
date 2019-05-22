package com.koperasi.koperasiku.tampil;

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
import com.koperasi.koperasiku.history.KeteranganInvestasi1;
import com.koperasi.koperasiku.history.Produk;
import com.koperasi.koperasiku.input.RequestHandlerBarang;
import com.koperasi.koperasiku.input.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilBarang extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextId;
    private EditText editTextNama;
    private EditText editTextHarga;
    private EditText editTextJual;

    private EditText editTextStok;


    private Button buttonUpdate;
    private Button buttonDelete;

    private String idb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_barang);

        Intent intent = getIntent();

        idb = intent.getStringExtra(konfigurasi.EMP_ID_B);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextHarga = (EditText) findViewById(R.id.editTextHarga);
        editTextJual = (EditText) findViewById(R.id.editTextJual);
        editTextStok = (EditText) findViewById(R.id.editTextStok);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(idb);

        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilBarang.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerBarang rh = new RequestHandlerBarang();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP_B,idb);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY_B);
            JSONObject c = result.getJSONObject(0);

            String name = c.getString(konfigurasi.TAG_NAMA_B);
            String harga = c.getString(konfigurasi.TAG_HARGA_B);
            String jual = c.getString(konfigurasi.TAG_JUAL_B);
            String stok = c.getString(konfigurasi.TAG_STOK_B);


            editTextNama.setText(name);
            editTextHarga.setText(harga);
            editTextJual.setText(jual);
            editTextStok.setText(stok);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String name = editTextNama.getText().toString().trim();
        final String harga = editTextHarga.getText().toString().trim();
        final String jual = editTextJual.getText().toString().trim();
        final String stok = editTextStok.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilBarang.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilBarang.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID_B,idb);
                hashMap.put(konfigurasi.KEY_EMP_NAMA_B,name);
                hashMap.put(konfigurasi.KEY_EMP_HARGA_B,harga);
                hashMap.put(konfigurasi.KEY_EMP_JUAL_B,jual);
                hashMap.put(konfigurasi.KEY_EMP_STOK_B,stok);

                RequestHandlerBarang rh = new RequestHandlerBarang();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP_B,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilBarang.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilBarang.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerBarang rh = new RequestHandlerBarang();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP_B, idb);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Pegawai ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilBarang.this,Produk.class));
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
            Intent ck = new Intent(TampilBarang.this, Produk.class);
            startActivity(ck);
            finish();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }

    }
}
