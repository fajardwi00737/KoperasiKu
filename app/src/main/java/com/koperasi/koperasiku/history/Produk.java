package com.koperasi.koperasiku.history;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.koperasi.koperasiku.R;
import com.koperasi.koperasiku.input.RequestHandler;
import com.koperasi.koperasiku.input.TambahAnggota;
import com.koperasi.koperasiku.input.TambahProduk;
import com.koperasi.koperasiku.tampil.TampilBarang;
import com.koperasi.koperasiku.input.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Produk extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;

    private String JSON_STRING;
    private FloatingActionButton tambahproduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
        tambahproduk = (FloatingActionButton)findViewById(R.id.barang);
        tambahproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Produk.this, TambahProduk.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY_B);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID_B);
                String name = jo.getString(konfigurasi.TAG_NAMA_B);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_ID_B,id);
                employees.put(konfigurasi.TAG_NAMA_B,name);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                Produk.this, list, R.layout.list_item,
                new String[]{konfigurasi.TAG_ID_B,konfigurasi.TAG_NAMA_B},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Produk.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL_B);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilBarang.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi.TAG_ID_B).toString();
        intent.putExtra(konfigurasi.EMP_ID_B,empId);
        startActivity(intent);
        finish();
    }

}
