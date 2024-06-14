package com.koperasi.proyekpbo;

public class Pegawai {
    private String id_pegawai;
    private String nama_pegawai;

    public Pegawai(String id_pegawai, String nama_pegawai) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }
}
