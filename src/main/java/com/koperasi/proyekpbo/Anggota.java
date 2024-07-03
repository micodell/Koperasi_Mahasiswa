package com.koperasi.proyekpbo;

public class Anggota {
    private int id_anggota;
    private String nama_anggota;
    private int id_role;
    private int id_history_executive;

    public Anggota(int id_anggota, String nama_anggota, int id_role, int id_history_executive) {
        this.id_anggota = id_anggota;
        this.nama_anggota = nama_anggota;
        this.id_role = id_role;
        this.id_history_executive = id_history_executive;
    }

    public int getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(int id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_history_executive() {
        return id_history_executive;
    }

    public void setId_history_executive(int id_history_executive) {
        this.id_history_executive = id_history_executive;
    }
}