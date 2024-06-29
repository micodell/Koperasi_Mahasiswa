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
import java.util.Scanner;

public class HomePageController implements TableMethod {

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
    Scanner input = new Scanner(System.in);
    @FXML
    void make_invisible() throws SQLException {
        table_view.setVisible(false);
        insert_into_table("Sesuatu");
    }

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

    public void view_table(String table) throws SQLException {
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

                        dept.setId_department(rs.getString("department_id"));
//                        untuk mengambil value dari table yang kita query
                        dept.setName_department(rs.getString("department_name"));
                        listOfDept.add(dept);
                        for (int i = 0; i < listOfDept.size(); i++) {
                            System.out.println(listOfDept.get(i).getId_department() +
                                    " " + listOfDept.get(i).getName_department());
                        }
                    }
                    case "Role" -> {
                        Role role = new Role();
                        ObservableList<Role> listOfRole = FXCollections.observableArrayList();
                        role.setId_role(rs.getString("role_id"));
                        role.setName_role(rs.getString("role_name"));
                        role.setJobdesc(rs.getString("job_desc"));
                        listOfRole.add(role);
                        for (int i = 0; i < listOfRole.size(); i++) {
                            System.out.println(listOfRole.get(i).getId_role() +
                                    " " + listOfRole.get(i).getName_role() +
                                    " " + listOfRole.get(i).getJobdesc());
                        }
                    }
                    case "Pegawai" -> {
                        Pegawai pegawai = new Pegawai();
                        ObservableList<Pegawai> listOfEmployees = FXCollections.observableArrayList();
                        pegawai.setId_pegawai(rs.getString("employee_id"));
                        pegawai.setNama_pegawai(rs.getString("employee_name"));
                        listOfEmployees.add(pegawai);
                        for (int i = 0; i < listOfEmployees.size(); i++) {
                            System.out.println(listOfEmployees.get(i).getId_pegawai() +
                                    " " + listOfEmployees.get(i).getNama_pegawai());
                        }
                    }
                    case "Transaksi" -> {
                        Transaksi transaksi = new Transaksi();
                        ObservableList<Transaksi> listOfTransaction = FXCollections.observableArrayList();
                        transaksi.setId_transaksi(rs.getString("transaction_id"));
                        transaksi.setTanggal_transaksi(rs.getString("transaction_date"));
                        transaksi.setNama_seller(rs.getString("name_seller"));
                        transaksi.setProfit_total(rs.getInt("total_profit"));
                        listOfTransaction.add(transaksi);
                        for (int i = 0; i < listOfTransaction.size(); i++) {
                            System.out.println(listOfTransaction.get(i).getId_transaksi() +
                                    " " + listOfTransaction.get(i).getTanggal_transaksi() +
                                    " " + listOfTransaction.get(i).getNama_seller() +
                                    " " + listOfTransaction.get(i).getProfit_total());
                        }
                    }
                    case "Barang" -> {
                        Barang brg = new Barang();
                        ObservableList<Barang> listOfItem = FXCollections.observableArrayList();
                        brg.setId_barang(rs.getString("item_id"));
                        brg.setNama_barang(rs.getString("item_name"));
                        brg.setHarga_pokok(rs.getInt("harga_pokok"));
                        brg.setHarga_jual(rs.getInt("harga_jual"));
                        listOfItem.add(brg);
                        for (int i = 0; i < listOfItem.size(); i++) {
                            System.out.println(listOfItem.get(i).getId_barang() +
                                    " " + listOfItem.get(i).getNama_barang() +
                                    " " + listOfItem.get(i).getHarga_pokok() +
                                    " " + listOfItem.get(i).getHarga_jual());
                        }
                    }
                    case "Kategori" -> {
                        Kategori kategori = new Kategori();
                        ObservableList<Kategori> listOfCategory = FXCollections.observableArrayList();
                        kategori.setId_kategori(rs.getString("category_id"));
                        kategori.setNama_kategori(rs.getString("category_name"));
                        listOfCategory.add(kategori);
                        for (int i = 0; i < listOfCategory.size(); i++) {
                            System.out.println(listOfCategory.get(i).getId_kategori() +
                                    " " + listOfCategory.get(i).getNama_kategori());
                        }
                    }
                    case "Anggota" -> {
                        Anggota anggota = new Anggota();
                        ObservableList<Anggota> listOfExecutives = FXCollections.observableArrayList();
                        anggota.setId_anggota(rs.getString("id_anggota"));
                        anggota.setNama_anggota(rs.getString("nama_anggota"));
                        listOfExecutives.add(anggota);
                        for (int i = 0; i < listOfExecutives.size(); i++) {
                            System.out.println(listOfExecutives.get(i).getId_anggota() +
                                    " " + listOfExecutives.get(i).getNama_anggota());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//
//            stmt.executeUpdate();
    public void insert_into_table(String table) throws SQLException {
        con = DBConnection.getConnection();
        table_view.setVisible(false);
        try {
            switch (table) {
                case "Department" -> {

                    String SQL_dept = """
                INSERT INTO public."departments" (id_department, name_department)
                VALUES (?, ?)
                """;
                    st = con.prepareStatement(SQL_dept);

                    System.out.println("id department: ");
                    int id_department = Integer.parseInt(input.nextLine());
                    System.out.println("nama department: ");
                    String name_department = input.nextLine();

                    st.setString(1, String.valueOf(id_department));
                    st.setString(2, name_department);

                    view_table(table);
                }
                case "Role" -> {}
                case "Pegawai" -> {}
                case "Anggota" -> {}
                case "Barang" -> {}
                case "Kategori" -> {}
                case "Transaksi" -> {}
            }
            rs = st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update_from_table(String table) throws SQLException {

    }

    @Override
    public void delete_from_table(String table) throws SQLException {

    }
}
