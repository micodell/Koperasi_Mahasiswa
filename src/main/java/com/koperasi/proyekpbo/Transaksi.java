package com.koperasi.proyekpbo;

import java.util.Date;

public class Transaksi {
    private int id_transaksi;
    private Date tanggal_transaksi;
    private String name_seller;
    private int id_barang;
    private int total_jual;
    private double profit_total;
    private int kuantitas_jual;
    private int id_role;

    public Transaksi(int id_transaksi, Date tanggal_transaksi, String nama_seller, int id_barang, int total_jual, double profit_total, int kuantitas_jual, int id_role) {
        this.id_transaksi = id_transaksi;
        this.tanggal_transaksi = tanggal_transaksi;
        this.name_seller = nama_seller;
        this.id_barang = id_barang;
        this.total_jual = total_jual;
        this.profit_total = profit_total;
        this.kuantitas_jual = kuantitas_jual;
        this.id_role = id_role;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Date getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(Date tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getName_seller() {
        return name_seller;
    }

    public void setNama_seller(String nama_seller) {
        this.name_seller = nama_seller;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getTotal_jual() {
        return total_jual;
    }

    public void setTotal_jual(int total_jual) {
        this.total_jual = total_jual;
    }

    public double getProfit_total() {
        return profit_total;
    }

    public void setProfit_total(double profit_total) {
        this.profit_total = profit_total;
    }

    public int getKuantitas_jual() {
        return kuantitas_jual;
    }

    public void setKuantitas_jual(int kuantitas_jual) {
        this.kuantitas_jual = kuantitas_jual;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}