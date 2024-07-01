package com.koperasi.proyekpbo;

public class Transaksi {
    private int id_transaksi;
    private String tanggal_transaksi;
    private String nama_seller;
    private int profit_total;

    public Transaksi(int id_transaksi, String tanggal_transaksi, String nama_seller, int profit_total) {
        this.id_transaksi = id_transaksi;
        this.tanggal_transaksi = tanggal_transaksi;
        this.nama_seller = nama_seller;
        this.profit_total = profit_total;
    }
    public Transaksi () {

    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getNama_seller() {
        return nama_seller;
    }

    public void setNama_seller(String nama_seller) {
        this.nama_seller = nama_seller;
    }

    public int getProfit_total() {
        return profit_total;
    }

    public void setProfit_total(int profit_total) {
        this.profit_total = profit_total;
    }
}
