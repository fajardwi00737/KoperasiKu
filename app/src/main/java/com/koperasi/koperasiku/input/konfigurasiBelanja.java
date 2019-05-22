package com.koperasi.koperasiku.input;

public class konfigurasiBelanja {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD ="http://192.168.5.25/barang/tambahBarang.php";
    public static final String URL_GET_ALL = "http://192.168.5.25/belanja/tampilSemuaBelanja.php";
    public static final String URL_GET_EMP = "http://192.168.5.25/barang/tampilBarang.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.5.25/barang/updateBarang.php";
    public static final String URL_DELETE_EMP = "http://192.168.5.25/barang/hapusBarang.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_HARGA = "jumlah";
    public static final String KEY_EMP_STOK = "tanggal";
    public static final String KEY_EMP_TOTAL = "total";

    //JSON Tags
    public static final String TAG_JSON_ARRAY ="belanja";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_HARGA = "jumlah";
    public static final String TAG_STOK = "tanggal";
    public static final String TAG_TOTAL = "total";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID = "id";
}
