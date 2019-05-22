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
import com.koperasi.koperasiku.input.RequestHandler;
import com.koperasi.koperasiku.input.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Tampilpegawa extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextdaerah;
    private EditText editTextkamar;
    private EditText editTextSaldo;


    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilpegawa);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (EditText) findViewById(R.id.editTextNama);
        editTextdaerah = (EditText) findViewById(R.id.editTextDaerah);
        editTextkamar = (EditText) findViewById(R.id.editTextKamar);
        editTextSaldo = (EditText) findViewById(R.id.editTextSaldo);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampilpegawa.this,"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String name = c.getString(konfigurasi.TAG_NAMA);
            String desg = c.getString(konfigurasi.TAG_DAERAH);
            String sal = c.getString(konfigurasi.TAG_KAMAR);
            String saldo = c.getString(konfigurasi.TAG_SALDO);


            editTextName.setText(name);
            editTextdaerah.setText(desg);
            editTextkamar.setText(sal);
            editTextSaldo.setText(saldo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String desg = editTextdaerah.getText().toString().trim();
        final String salary = editTextkamar.getText().toString().trim();
        final String saldo = editTextSaldo.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampilpegawa.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Tampilpegawa.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID,id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,name);
                hashMap.put(konfigurasi.KEY_EMP_DAERAH,desg);
                hashMap.put(konfigurasi.KEY_EMP_KAMAR,salary);
                hashMap.put(konfigurasi.KEY_EMP_SALDO,saldo);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

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
                loading = ProgressDialog.show(Tampilpegawa.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Tampilpegawa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
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
                        startActivity(new Intent(Tampilpegawa.this,Anggota.class));
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
            Intent intent = new Intent(Tampilpegawa.this, Anggota.class);
            startActivity(intent);
            finish();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }

    }
}