package com.koperasi.proyekpbo;

public class Pegawai {
    private int id_pegawai;
    private String nama_pegawai;
    private int id_role;
    private int id_history_employee;

    public Pegawai(int id_pegawai, String nama_pegawai, int id_role, int id_history_employee) {
        this.id_pegawai = id_pegawai;
        this.nama_pegawai = nama_pegawai;
        this.id_role = id_role;
        this.id_history_employee = id_history_employee;
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

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_history_employee() {
        return id_history_employee;
    }

    public void setId_history_employee(int id_history_employee) {
        this.id_history_employee = id_history_employee;
    }
}
