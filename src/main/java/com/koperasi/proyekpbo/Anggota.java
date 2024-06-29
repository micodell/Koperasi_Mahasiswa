package com.koperasi.proyekpbo;

public class Anggota {
    private String id_anggota;
    private String nama_anggota;

    public Anggota(String id_anggota, String nama_anggota) {
        this.id_anggota = id_anggota;
        this.nama_anggota = nama_anggota;
    }

    public Anggota() {

    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String idAnggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }
}