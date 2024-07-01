package com.koperasi.proyekpbo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePageController {

    @FXML
    private Button delete_button;

    @FXML
    private ComboBox<String> drop_down_menu;

    @FXML
    private Button insert_button;

    @FXML
    private TableView<?> table_view;

    @FXML
    private Button update_button;
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    void initialize() {
        drop_down_menu.setItems(
                FXCollections.observableArrayList(
                        "Department",
                        "Role",
                        "Pegawai",
                        "Anggota",
                        "Barang",
                        "Kategori",
                        "Transaksi"
                )
        );
        // Add listener to the ComboBox
        drop_down_menu.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected value: " + newValue);
            }
        });
    }

    void view_table(String table) throws SQLException {
        String SQL = "SELECT * FROM " + table;
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(SQL);
            rs = st.executeQuery();
            while (rs.next()) {
                switch (table) {
                    case "Department" -> {
                        Department dept = new Department();
                        ObservableList<Department> listOfDept = FXCollections.observableArrayList();

                        dept.setId_department(rs.getInt("id_department"));
//                        untuk mengambil value dari table yang kita query
                        dept.setName_department(rs.getString("name_department"));
                        listOfDept.add(dept);
                        for (int i = 0; i < listOfDept.size(); i++) {
                            System.out.println(listOfDept.get(i));
                        }
                    }
                    case "Role" -> {

                    }
                    case "Pegawai" -> {}
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setInsert_button(String table) throws SQLException{
        String SQL = "INSERT TO " + table + drop_down_menu.getValue();
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(SQL);
            rs = st.executeQuery();
            while (rs.next()) {
                switch (table) {
                    case "Department" -> {
                        Department dept = new Department();
                        ObservableList<Department> listOfDept = FXCollections.observableArrayList();

                        dept.setId_department(rs.getInt("id_department"));
//                        untuk mengambil value dari table yang kita query
                        dept.setName_department(rs.getString("name_department"));
                        listOfDept.add(dept);
                    }
                    case "Role" -> {
                        Role role = new Role();
                        ObservableList<Role> listOfRole = FXCollections.observableArrayList();

                        role.setId_role(rs.getInt("id_role"));
                        role.setName_role(rs.getString("name_role"));
                        listOfRole.add(role);
                    }
                    case "Pegawai" -> {
                        Pegawai pegawai = new Pegawai();
                        ObservableList<Pegawai> listofPegawai = FXCollections.observableArrayList();

                        pegawai.setId_pegawai(rs.getInt("id_pegawai"));
                        pegawai.setNama_pegawai(rs.getString("nama_pegawai"));
                        listofPegawai.add(pegawai);
                    }
                    case "Anggota" -> {
                        Anggota anggota = new Anggota();
                        ObservableList<Anggota> listOfAnggota = FXCollections.observableArrayList();

                        anggota.setId_anggota(rs.getInt("id_anggota"));
                        anggota.setNama_anggota(rs.getString("nama_anggota"));
                        listOfAnggota.add(anggota);
                    }
                    case "Barang" -> {
                        Barang barang = new Barang();
                        ObservableList<Barang> listOfBarang = FXCollections.observableArrayList();

                        barang.setId_barang(rs.getInt("id_barang"));
                        barang.setNama_barang(rs.getString("nama_barang"));
                        barang.setHarga_pokok(rs.getInt("harga_pokok"));
                        barang.setHarga_jual(rs.getInt("harga_jual"));
                        listOfBarang.add(barang);
                    }
                    case "Kategori" -> {
                        Kategori kategori = new Kategori();
                        ObservableList<Kategori> listOfKategori = FXCollections.observableArrayList();

                        kategori.setId_kategori(rs.getInt("id_kategori"));
                        kategori.setNama_kategori(rs.getString("nama_kategori"));
                        listOfKategori.add(kategori);
                    }
                    case "Transaksi" -> {
                        Transaksi transaksi = new Transaksi();
                        ObservableList<Transaksi> listOfTransaksi = FXCollections.observableArrayList();

                        transaksi.setId_transaksi(rs.getInt("id_transaksi"));
                        transaksi.setTanggal_transaksi(rs.getString("tanggal_transaksi"));
                        transaksi.setNama_seller(rs.getString("nama_seller"));
                        transaksi.setProfit_total(rs.getInt("profit_total"));
                        listOfTransaksi.add(transaksi);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setDelete_button(String table) throws SQLException{
        String SQL = "DELETE FROM " + table + drop_down_menu.getValue();
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(SQL);
            rs = st.executeQuery();
            while (rs.next()){
                switch (table){

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
