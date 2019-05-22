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
import com.koperasi.koperasiku.history.Anggota;
import com.koperasi.koperasiku.history.Transaksi1;
import com.koperasi.koperasiku.input.RequestHandler;
import com.koperasi.koperasiku.input.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilTransaksi extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextIdT;
    private EditText editTextIdA;
    private EditText editTextIdB;
    private EditText editTextJumlah;
    private EditText editTextSatuan;
    private EditText editTextTotal;
    private EditText editTextTanggal;
    private EditText editTextIdP;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_transaksi);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID_T);

        editTextIdT = (EditText) findViewById(R.id.editTextIdT);
        editTextIdA = (EditText) findViewById(R.id.editTextIdA);
        editTextIdB = (EditText) findViewById(R.id.editTextIdB);
        editTextJumlah = (EditText) findViewById(R.id.editTextJumlah);
        editTextSatuan = (EditText) findViewById(R.id.editTextSatuan);
        editTextTotal = (EditText) findViewById(R.id.editTextTotal);
        editTextTanggal = (EditText) findViewById(R.id.editTextTanggal);
        editTextIdP = (EditText) findViewById(R.id.editTextIdP);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextIdT.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilTransaksi.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP_T,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY_T);
            JSONObject c = result.getJSONObject(0);

            String IdT = c.getString(konfigurasi.TAG_ID_T);
            String IdA = c.getString(konfigurasi.TAG_NAMA_T);
            String IdB = c.getString(konfigurasi.TAG_KEBUTUHAN_T);
            String Jumlah = c.getString(konfigurasi.TAG_JUMLAH_T);
            String Satuan = c.getString(konfigurasi.TAG_SATUAN_T);
            String Total = c.getString(konfigurasi.TAG_TOTAL_T);
            String Tanggal = c.getString(konfigurasi.TAG_TANGGAL_T);
            String Petugas = c.getString(konfigurasi.TAG_PETUGAS_T);


            editTextIdT.setText(IdT);
            editTextIdA.setText(IdA);
            editTextIdB.setText(IdB);
            editTextJumlah.setText(Jumlah);
            editTextSatuan.setText(Satuan);
            editTextTotal.setText(Total);
            editTextTanggal.setText(Tanggal);
            editTextIdP.setText(Petugas);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){

        final String IdA = editTextIdA.getText().toString().trim();
        final String IdB = editTextIdB.getText().toString().trim();
        final String Jumlah = editTextJumlah.getText().toString().trim();
        final String Satuan = editTextSatuan.getText().toString().trim();
        final String Total = editTextTotal.getText().toString().trim();
        final String Tanggal = editTextTanggal.getText().toString().trim();
        final String IdP = editTextIdP.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilTransaksi.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilTransaksi.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID_T,id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA_T,IdA);
                hashMap.put(konfigurasi.KEY_EMP_KEBUTUHAN_T,IdB);
                hashMap.put(konfigurasi.KEY_EMP_JUMLAH_T,Jumlah);
                hashMap.put(konfigurasi.KEY_EMP_SATUAN_T,Satuan);
                hashMap.put(konfigurasi.KEY_EMP_TOTAL_T,Total);
                hashMap.put(konfigurasi.KEY_EMP_TANGGAL_T,Tanggal);
                hashMap.put(konfigurasi.KEY_EMP_PETUGAS_T,IdP);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP_T,hashMap);

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
                loading = ProgressDialog.show(TampilTransaksi.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilTransaksi.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP_T, id);
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
                        startActivity(new Intent(TampilTransaksi.this,Anggota.class));
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
        if (v == buttonUpdate) {
            updateEmployee();
            Intent intent = new Intent(TampilTransaksi.this, Transaksi1.class);
            startActivity(intent);
            onStop();
        }

        if (v == buttonDelete) {
            confirmDeleteEmployee();
        }

    }
}
