package com.koperasi.proyekpbo;

public class Barang implements DataType{
    private int id_barang;
    private String nama_barang;
    private int harga_pokok;
    private int harga_jual;

    public Barang(int id_stock, String nama_barang, int harga_pokok, int harga_jual) {
        this.id_barang = id_stock;
        this.nama_barang = nama_barang;
        this.harga_pokok = harga_pokok;
        this.harga_jual = harga_jual;
    }
    public Barang () {

    }

    @Override
    public int getId() {
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

    public int getHarga_pokok() {
        return harga_pokok;
    }

    public void setHarga_pokok(int harga_pokok) {
        this.harga_pokok = harga_pokok;
    }

    public int getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(int harga_jual) {
        this.harga_jual = harga_jual;
    }
}