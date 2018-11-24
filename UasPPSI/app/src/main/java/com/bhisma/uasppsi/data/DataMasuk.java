package com.bhisma.uasppsi.data;

public class DataMasuk {
    private String id_kujungan, namaMasuk, instansiMasuk, tujuanMasuk, karyawanMasuk;

    public DataMasuk() {
    }

    public DataMasuk(String id_kujungan, String namaMasuk, String instansiMasuk, String tujuanMasuk, String karyawanMasuk) {
        this.id_kujungan= id_kujungan;
        this.namaMasuk = namaMasuk;
        this.instansiMasuk = instansiMasuk;
        this.tujuanMasuk = tujuanMasuk;
        this.karyawanMasuk = karyawanMasuk;
    }

    public String getId_kujungan() {
        return id_kujungan;
    }

    public void setId_kujungan(String id_kujungan) {
        this.id_kujungan = id_kujungan;
    }

    public String getNamaMasuk() {
        return namaMasuk;
    }

    public void setNamaMasuk(String namaMasuk) {
        this.namaMasuk = namaMasuk;
    }

    public String getInstansiMasuk() {
        return instansiMasuk;
    }

    public void setInstansiMasuk(String instansiMasuk) {
        this.instansiMasuk = instansiMasuk;
    }

    public String getTujuanMasuk() {
        return tujuanMasuk;
    }

    public void setTujuanMasuk(String tujuanMasuk) {
        this.tujuanMasuk = tujuanMasuk;
    }

    public String getKaryawanMasuk() {
        return karyawanMasuk;
    }

    public void setKaryawanMasuk(String karyawanMasuk) {
        this.karyawanMasuk = karyawanMasuk;
    }
}
