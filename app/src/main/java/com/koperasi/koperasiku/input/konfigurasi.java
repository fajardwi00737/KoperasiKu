package com.koperasi.koperasiku.input;

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD ="http://192.168.5.25/anggota/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.5.25/anggota/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.5.25/anggota/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.5.25/anggota/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.5.25/anggota/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_DAERAH = "daerah";
    public static final String KEY_EMP_KAMAR = "kamar";
    public static final String KEY_EMP_SALDO = "saldo";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="anggota";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_DAERAH = "daerah";
    public static final String TAG_KAMAR = "kamar";
    public static final String TAG_SALDO = "saldo";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID = "id";


    //konfigurasi Barang
    public static final String URL_ADD_B ="http://192.168.5.25/barang/tambahBarang.php";
    public static final String URL_GET_ALL_B = "http://192.168.5.25/barang/tampilSemuaBarang.php";
    public static final String URL_GET_EMP_B = "http://192.168.5.25/barang/tampilBarang.php?id=";
    public static final String URL_UPDATE_EMP_B = "http://192.168.5.25/barang/updateBarang.php";
    public static final String URL_DELETE_EMP_B = "http://192.168.5.25/barang/hapusBarang.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID_B = "id";
    public static final String KEY_EMP_NAMA_B = "name";
    public static final String KEY_EMP_HARGA_B = "harga";
    public static final String KEY_EMP_JUAL_B = "jual";
    public static final String KEY_EMP_STOK_B = "stok";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_B ="barang";
    public static final String TAG_ID_B = "id";
    public static final String TAG_NAMA_B = "name";
    public static final String TAG_HARGA_B = "harga";
    public static final String TAG_JUAL_B = "jual";
    public static final String TAG_STOK_B = "stok";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID_B = "id";

    //Konfigurasi Belanja
    public static final String URL_ADD_BE ="http://192.168.5.25/belanja/tambahBelanja.php";
    public static final String URL_GET_ALL_BE = "http://192.168.5.25/belanja/tampilSemuaBelanja.php";
    public static final String URL_GET_EMP_BE = "http://192.168.5.25/belanja/tampilBelanja.php?id=";
    public static final String URL_UPDATE_EMP_BE = "http://192.168.5.25/belanja/updateBelanja.php";
    public static final String URL_DELETE_EMP_BE = "http://192.168.5.25/belanja/hapusBelanja.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID_BE = "id";
    public static final String KEY_EMP_NAMA_BE = "name";
    public static final String KEY_EMP_JUMLAH_BE = "tanggal";
    public static final String KEY_EMP_TANGGAL_BE = "jumlah";
    public static final String KEY_EMP_TOTAL_BE = "total";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_BE ="belanja";
    public static final String TAG_ID_BE = "id";
    public static final String TAG_NAMA_BE = "name";
    public static final String TAG_TANGGAL_BE = "tanggal";
    public static final String TAG_JUMLAH_BE = "jumlah";
    public static final String TAG_TOTAL_BE = "total";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID_BE = "id";

    //kofigurasi Investasi
    public static final String URL_ADD_I ="http://192.168.5.25/investasi/tambahInvestasi.php";
    public static final String URL_GET_ALL_I = "http://192.168.5.25/investasi/tampilSemuaInvestasi.php";
    public static final String URL_GET_EMP_I = "http://192.168.5.25/investasi/tampilInvestasi.php?id=";
    public static final String URL_UPDATE_EMP_I = "http://192.168.5.25/investasi/updateInvestasi.php";
    public static final String URL_DELETE_EMP_I = "http://192.168.5.25/investasi/hapusInvestasi.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID_I = "id";
    public static final String KEY_EMP_NAMA_I = "nama";
    public static final String KEY_EMP_HARGA_I= "tanggal";
    public static final String KEY_EMP_STOK_I = "jumlah";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_I ="simpan";
    public static final String TAG_ID_I = "id";
    public static final String TAG_NAMA_I = "nama";
    public static final String TAG_HARGA_I = "tanggal";
    public static final String TAG_STOK_I = "jumlah";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID_I = "id";

    //konfigurasi pinjam
    public static final String URL_ADD_P ="http://192.168.5.25/pinjam/tambahPinjam.php";
    public static final String URL_GET_ALL_P = "http://192.168.5.25/pinjam/tampilSemuaPinjam.php";
    public static final String URL_GET_EMP_P = "http://192.168.5.25/pinjam/tampil.php?id=";
    public static final String URL_UPDATE_EMP_P = "http://192.168.5.25/pinjam/updatePinjam.php";
    public static final String URL_DELETE_EMP_P = "http://192.168.5.25/pinjam/hapusPinjam.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID_P = "id";
    public static final String KEY_EMP_NAMA_P = "name";
    public static final String KEY_EMP_TANGGAL_P = "tanggal";
    public static final String KEY_EMP_JUMLAH_P = "jumlah";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_P ="pinjam";
    public static final String TAG_ID_P = "id";
    public static final String TAG_NAMA_P = "name";
    public static final String TAG_TANGGAL_P = "tanggal";
    public static final String TAG_JUMLAH_P = "jumlah";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID_P = "id";

    //konfigurasi transaksi
    public static final String URL_ADD_T ="http://192.168.5.25/transaksi/tambahTransaksi.php";
    public static final String URL_GET_ALL_T = "http://192.168.5.25/transaksi/tampilSemuaTransaksi.php";
    public static final String URL_GET_EMP_T = "http://192.168.5.25/transaksi/tampilTransaksi.php?id=";
    public static final String URL_UPDATE_EMP_T = "http://192.168.5.25/transaksi/updateTransaksi.php";
    public static final String URL_DELETE_EMP_T = "http://192.168.5.25/transaksi/hapusTransaksi.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID_T = "id";
    public static final String KEY_EMP_NAMA_T = "anggota";
    public static final String KEY_EMP_KEBUTUHAN_T = "kebutuhan";
    public static final String KEY_EMP_JUMLAH_T = "jumlah";
    public static final String KEY_EMP_SATUAN_T = "satuan";
    public static final String KEY_EMP_TOTAL_T = "total";
    public static final String KEY_EMP_TANGGAL_T = "tanggal";
    public static final String KEY_EMP_PETUGAS_T = "petugas";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_T ="transaksi";
    public static final String TAG_ID_T = "id";
    public static final String TAG_NAMA_T = "anggota";
    public static final String TAG_KEBUTUHAN_T = "kebutuhan";
    public static final String TAG_JUMLAH_T = "jumlah";
    public static final String TAG_SATUAN_T = "satuan";
    public static final String TAG_TOTAL_T = "total";
    public static final String TAG_TANGGAL_T = "tanggal";
    public static final String TAG_PETUGAS_T = "petugas";

    //ID karyawan
    //emp itu singkatan dari EmployeeW
    public static final String EMP_ID_T = "id";
}
