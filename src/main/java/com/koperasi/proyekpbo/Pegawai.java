package com.koperasi.proyekpbo;

public class Pegawai {
    private int id_pegawai;
    private String nama_pegawai;

    public Pegawai(int id_pegawai, String nama_pegawai) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
    }

    public Pegawai(){

    }

    public int getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(int id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }
}
