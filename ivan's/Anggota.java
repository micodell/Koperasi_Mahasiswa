package com.koperasi.proyekpbo;

public class Anggota {
    private int id_anggota;
    private String nama_anggota;

    public Anggota(int id_anggota, String nama_anggota) {
        this.id_anggota = id_anggota;
        this.nama_anggota = nama_anggota;
    }

    public Anggota() {

    }

    public int getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(int idAnggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }
}