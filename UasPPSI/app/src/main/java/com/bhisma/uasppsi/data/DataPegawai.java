package com.bhisma.uasppsi.data;

public class DataPegawai {
    private String id_pegawai, nama_pegawai, regid;

    public DataPegawai() {
    }

    public DataPegawai(String id_pegawai, String nama_pegawai) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
    }

    public String getIdPegawai() {
        return id_pegawai;
    }

    public void setIdPegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNamaPegawai() {
        return nama_pegawai;
    }

    public void setNamaPegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }


}
