package com.koperasi.proyekpbo;

public class Barang {
    private int id_barang;
    private String nama_barang;
    private int stock_barang;
    private double harga_pokok;
    private double harga_jual;
    private int id_kategori;

    public Barang(int id_barang, String nama_barang, int stock_barang, double harga_pokok, double harga_jual, int id_kategori) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.stock_barang = stock_barang;
        this.harga_pokok = harga_pokok;
        this.harga_jual = harga_jual;
        this.id_kategori = id_kategori;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getStock_barang() {
        return stock_barang;
    }

    public void setStock_barang(int stock_barang) {
        this.stock_barang = stock_barang;
    }

    public double getHarga_pokok() {
        return harga_pokok;
    }

    public void setHarga_pokok(double harga_pokok) {
        this.harga_pokok = harga_pokok;
    }

    public double getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(double harga_jual) {
        this.harga_jual = harga_jual;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }
}