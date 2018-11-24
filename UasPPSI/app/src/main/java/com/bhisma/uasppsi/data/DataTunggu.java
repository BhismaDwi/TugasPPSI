package com.bhisma.uasppsi.data;

public class DataTunggu {
    private String id_kujungan, nama, instansi, tujuan, karyawan, gambar;

    public DataTunggu() {
    }

    public DataTunggu(String id_kujungan, String nama, String instansi, String tujuan, String karyawan, String gambar) {
        this.id_kujungan = id_kujungan;
        this.nama = nama;
        this.instansi = instansi;
        this.tujuan = tujuan;
        this.karyawan = karyawan;
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getId_kujungan() {
        return id_kujungan;
    }

    public void setId_kujungan(String id_kujungan) {
        this.id_kujungan = id_kujungan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(String karyawan) {
        this.karyawan = karyawan;
    }
}
