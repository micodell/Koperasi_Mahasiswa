package com.koperasi.proyekpbo;

import javafx.application.Platform;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class HomePageController implements TableMethod {

    @FXML
    private ComboBox<String> drop_down_menu;

    @FXML
    private TableView<Department> table_view_awal;


    @FXML
    private TableView<Department> table_view_dept;
    @FXML
    private TableColumn<Department, Integer> id_department_column;
    @FXML
    private TableColumn<Department, String> name_department_column;
    @FXML
    private TableColumn<Department, String> jobDesc_column;


    @FXML
    private TableView<Role> table_view_role;
    @FXML
    private TableColumn<Role, Integer> id_department_role_column;
    @FXML
    private TableColumn<Role, String> name_role_column;
    @FXML
    private TableColumn<Role, Integer> id_role_column;


    @FXML
    private TableView<Pegawai> table_view_emp;
    @FXML
    private TableColumn<Pegawai, Integer> id_pegawai_column;
    @FXML
    private TableColumn<Pegawai, String> nama_pegawai_column;
    @FXML
    private TableColumn<Pegawai, Integer> id_role_pegawai_column;
    @FXML
    private TableColumn<Pegawai, Integer> id_history_employee_pegawai_column;


    @FXML
    private TableView<Anggota> table_view_anggota;
    @FXML
    private TableColumn<Anggota, Integer> id_anggota_column;
    @FXML
    private TableColumn<Anggota, String> nama_anggota_column;
    @FXML
    private TableColumn<Anggota, Integer> id_role_anggota_column;
    @FXML
    private TableColumn<Anggota, Integer> id_history_executive_anggota_column;


    @FXML
    private TableView<Barang> table_view_barang;
    @FXML
    private TableColumn<Barang, Integer> id_barang_column;
    @FXML
    private TableColumn<Barang, String> nama_barang_column;
    @FXML
    private TableColumn<Barang, Integer> stock_barang_column;
    @FXML
    private TableColumn<Barang, Double> harga_pokok_barang_column;
    @FXML
    private TableColumn<Barang, Double> harga_jual_barang_column;
    @FXML
    private TableColumn<Barang, Integer> id_kategori_barang_column;


    @FXML
    private TableView<Kategori> table_view_kategori;
    @FXML
    private TableColumn<Kategori, Integer> id_kategori_column;
    @FXML
    private TableColumn<Kategori, String> nama_kategori_column;


    @FXML
    private TableView<Transaksi> table_view_trans;
    @FXML
    private TableColumn<Transaksi, Integer> id_transaksi_column;
    @FXML
    private TableColumn<Transaksi, Date> tanggal_transaksi_column;
    @FXML
    private TableColumn<Transaksi, String> name_seller_transaksi_column;
    @FXML
    private TableColumn<Transaksi, Integer> id_barang_transaksi_column;
    @FXML
    private TableColumn<Transaksi, Integer> total_jual_transaksi_column;
    @FXML
    private TableColumn<Transaksi, Double> profit_total_transaksi_column;
    @FXML
    private TableColumn<Transaksi, Integer> kuantitas_jual_transaksi_column;
    @FXML
    private TableColumn<Transaksi, Integer> id_role_transaksi_column;

    @FXML
    private TableView<History_employees> table_view_hist_emp;
    @FXML
    private TableColumn<History_employees, Integer> id_history_employee_column;
    @FXML
    private TableColumn<History_employees, Date> date_start_employee_column;
    @FXML
    private TableColumn<History_employees, Date> date_end_employee_column;


    @FXML
    private TableView<History_executives> table_view_hist_anggota;
    @FXML
    private TableColumn<History_executives, Integer> id_history_anggota_column;
    @FXML
    private TableColumn<History_executives, Date> date_start_anggota_column;
    @FXML
    private TableColumn<History_executives, Date> date_end_anggota_column;


    @FXML
    private Button insert_button;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;

    @FXML
    private Label pesan_awal, label1, label2, label3, label4, label5, label6, label7, label8;

    @FXML
    private TextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8;

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    Connection con = null;
    PreparedStatement st = null;

    Integer id_selected = null;

    @FXML
    void initialize() {
        drop_down_menu.setItems(
                FXCollections.observableArrayList(
                        "Departments",
                        "Roles",
                        "Employees",
                        "Executives",
                        "Stocks",
                        "Categories",
                        "Transactions",
                        "History_employees",
                        "History_executives"
                )
        );
        // Add listener to the ComboBox
        drop_down_menu.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected value: " + newValue);
                try {
                    updateFieldsVisibility(newValue);
                    view_table(newValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        table_view_dept.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_department()));
                textField1.setEditable(false);
                textField2.setText(newValue.getName_department());
                textField3.setText(newValue.getJobdesc());
                id_selected = newValue.getId_department();
            }
        });
        table_view_role.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_role()));
                textField1.setEditable(false);
                textField2.setText(newValue.getName_role());
                textField3.setText(String.valueOf(newValue.getId_department()));
                id_selected = newValue.getId_role();
            }
        });
        table_view_emp.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_pegawai()));
                textField1.setEditable(false);
                textField2.setText(newValue.getNama_pegawai());
                textField3.setText(String.valueOf(newValue.getId_role()));
                textField4.setText(String.valueOf(newValue.getId_history_employee()));
                id_selected = newValue.getId_pegawai();
            }
        });
        table_view_anggota.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_anggota()));
                textField1.setEditable(false);
                textField2.setText(newValue.getNama_anggota());
                textField3.setText(String.valueOf(newValue.getId_role()));
                textField4.setText(String.valueOf(newValue.getId_history_executive()));
                id_selected = newValue.getId_anggota();
            }
        });
        table_view_barang.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_barang()));
                textField1.setEditable(false);
                textField2.setText(newValue.getNama_barang());
                textField3.setText(String.valueOf(newValue.getStock_barang()));
                textField4.setText(String.valueOf(newValue.getHarga_pokok()));
                textField5.setText(String.valueOf(newValue.getHarga_jual()));
                textField6.setText(String.valueOf(newValue.getId_kategori()));
                id_selected = newValue.getId_barang();
            }
        });
        table_view_kategori.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_kategori()));
                textField1.setEditable(false);
                textField2.setText(newValue.getNama_kategori());
                id_selected = newValue.getId_kategori();
            }
        });
        table_view_trans.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textField1.setText(String.valueOf(newValue.getId_transaksi()));
                textField1.setEditable(false);
                textField2.setText(String.valueOf(newValue.getTanggal_transaksi()));
                textField3.setText(String.valueOf(newValue.getName_seller()));
                textField4.setText(String.valueOf(newValue.getId_barang()));
                textField5.setText(String.valueOf(newValue.getTotal_jual()));
                textField6.setText(String.valueOf(newValue.getProfit_total()));
                textField7.setText(String.valueOf(newValue.getKuantitas_jual()));
                textField8.setText(String.valueOf(newValue.getId_role()));
                id_selected = newValue.getId_transaksi();
            }
        });
        table_view_hist_emp.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textField1.setText(String.valueOf(newValue.getId_history_employee()));
            textField1.setEditable(false);
            textField2.setText(String.valueOf(newValue.getDate_start_employee()));
            textField3.setText(String.valueOf(newValue.getDate_end_employee()));
            id_selected = newValue.getId_history_employee();
        });
        table_view_hist_anggota.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textField1.setText(String.valueOf(newValue.getId_history_executive()));
            textField1.setEditable(false);
            textField2.setText(String.valueOf(newValue.getDate_start_executive()));
            textField3.setText(String.valueOf(newValue.getDate_end_executive()));
            id_selected = newValue.getId_history_executive();
        });

        insert_button.setOnAction(this::onInsertClicked);
        update_button.setOnAction(this::onUpdateClicked);
        delete_button.setOnAction(this::onDeleteClicked);
    }

    @FXML
    void onInsertClicked(ActionEvent event) {
        try {
            String selected = drop_down_menu.getValue();

            switch (selected) {
                case "Departments" -> insert_into_table("Departments");
                case "Roles" -> insert_into_table("Roles");
                case "Employees" -> insert_into_table("Employees");
                case "Executives" -> insert_into_table("Executives");
                case "Stocks" -> insert_into_table("Stocks");
                case "Categories" -> insert_into_table("Categories");
                case "Transactions"-> insert_into_table("Transactions");
                case "History_employees" -> insert_into_table("History_employees");
                case "History_executives" -> insert_into_table("History_executives");
                default -> System.out.println("Pilih tabel terlebih dahulu untuk melakukan insert.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    void onUpdateClicked(ActionEvent event) {
        try {
            String selected = drop_down_menu.getValue();

            switch (selected) {
                case "Departments" -> update_from_table("Departments");
                case "Roles" -> update_from_table("Roles");
                case "Employees" -> update_from_table("Employees");
                case "Executives" -> update_from_table("Executives");
                case "Stocks" -> update_from_table("Stocks");
                case "Categories" -> update_from_table("Categories");
                case "Transactions"-> update_from_table("Transactions");
                case "History_employees" -> update_from_table("History_employees");
                case "History_executives" -> update_from_table("History_executives");
                default -> System.out.println("Pilih tabel terlebih dahulu untuk melakukan update.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    void onDeleteClicked(ActionEvent event) {
        try {
            String selected = drop_down_menu.getValue();

            switch (selected) {
                case "Departments" -> delete_from_table("Departments");
                case "Roles" -> delete_from_table("Roles");
                case "Employees" -> delete_from_table("Employees");
                case "Executives" -> delete_from_table("Executives");
                case "Stocks" -> delete_from_table("Stocks");
                case "Categories" -> delete_from_table("Categories");
                case "Transactions"-> delete_from_table("Transactions");
                case "History_employees" -> delete_from_table("History_employees");
                case "History_executives" -> delete_from_table("History_executives");
                default -> System.out.println("Pilih tabel terlebih dahulu untuk melakukan update.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    void onExitClicked(ActionEvent event) {
        Platform.exit();
    }

    private void updateFieldsVisibility(String selectedTable) throws SQLException {
        pesan_awal.setVisible(true);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);

        table_view_awal.setVisible(false);
        table_view_dept.setVisible(false);
        table_view_role.setVisible(false);
        table_view_emp.setVisible(false);
        table_view_anggota.setVisible(false);
        table_view_barang.setVisible(false);
        table_view_kategori.setVisible(false);
        table_view_trans.setVisible(false);
        table_view_hist_emp.setVisible(false);
        table_view_hist_anggota.setVisible(false);

        switch (selectedTable) {
            case "Departments" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_department");
                label2.setText("name_department");
                label3.setText("jobdesc");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_dept.setVisible(true);
                view_table("departments");
            }
            case "Roles" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_role");
                label2.setText("name_role");
                label3.setText("id_department");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_role.setVisible(true);
                view_table("Roles");
            }
            case "Employees" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_employee");
                label2.setText("name_employee");
                label3.setText("id_role");
                label4.setText("id_history_employee");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_emp.setVisible(true);
                view_table("Employees");
            }
            case "Executives" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_executive");
                label2.setText("name_executive");
                label3.setText("id_role");
                label4.setText("id_history_executive");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_anggota.setVisible(true);
                view_table("Executives");
            }
            case "Stocks" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_barang");
                label2.setText("nama_barang");
                label3.setText("stock_barang");
                label4.setText("harga_pokok");
                label5.setText("harga_jual");
                label6.setText("id_kategori");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);
                textField5.setVisible(true);
                textField6.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_barang.setVisible(true);
                view_table("Stocks");
            }
            case "Categories" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_kategori");
                label2.setText("nama_kategori");

                label1.setVisible(true);
                label2.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_kategori.setVisible(true);
                view_table("Categories");
            }
            case "Transactions" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_transaksi");
                label2.setText("tanggal_transaksi");
                label3.setText("name_seller");
                label4.setText("id_barang");
                label5.setText("total_jual");
                label6.setText("profit_total");
                label7.setText("kuantitas_jual");
                label8.setText("id_role");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label7.setVisible(true);
                label8.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);
                textField5.setVisible(true);
                textField6.setVisible(true);
                textField7.setVisible(true);
                textField8.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_trans.setVisible(true);
                view_table("Transactions");
            }
            case "History_employees" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_history_employees");
                label2.setText("date_start_employee");
                label3.setText("date_end_employee");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_hist_emp.setVisible(true);
                view_table("History_employees");
            }
            case "History_executives" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_history_executives");
                label2.setText("date_start_executives");
                label3.setText("date_end_executives");

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_hist_anggota.setVisible(true);
                view_table("History_executives");
            }
        }
    }

    public ObservableList<Department> getDepartment() throws SQLException {
        ObservableList<Department> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM departments";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Department department = new Department(rs.getInt("id_department"), rs.getString("name_department"), rs.getString("jobdesc"));
            list.add(department);
        }
        return list;
    }

    public ObservableList<Role> getRole() throws SQLException {
        ObservableList<Role> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM roles";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Role role = new Role(rs.getInt("id_role"), rs.getString("name_role"), rs.getInt("id_department"));
            list.add(role);
        }
        return list;
    }

    public ObservableList<Pegawai> getPegawai() throws SQLException {
        ObservableList<Pegawai> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM employees";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Pegawai pegawai = new Pegawai(rs.getInt("id_pegawai"), rs.getString("nama_pegawai"), rs.getInt("id_role"), rs.getInt("id_history_employee"));
            list.add(pegawai);
        }
        return list;
    }

    public ObservableList<Anggota> getAnggota() throws SQLException {
        ObservableList<Anggota> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM executives";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Anggota anggota = new Anggota(rs.getInt("id_anggota"), rs.getString("nama_anggota"), rs.getInt("id_role"), rs.getInt("id_history_executive"));
            list.add(anggota);
        }
        return list;
    }

    public ObservableList<Barang> getBarang() throws SQLException {
        ObservableList<Barang> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM Stocks";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Barang barang = new Barang(rs.getInt("id_barang"), rs.getString("nama_barang"), rs.getInt("stock_barang"), rs.getDouble("harga_pokok"), rs.getDouble("harga_jual"), rs.getInt("id_kategori"));
            list.add(barang);
        }
        return list;
    }

    public ObservableList<Kategori> getKategori() throws SQLException {
        ObservableList<Kategori> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM Categories";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Kategori kategori = new Kategori(rs.getInt("id_kategori"), rs.getString("nama_kategori"));
            list.add(kategori);
        }
        return list;
    }

    public ObservableList<Transaksi> getTransaksi() throws SQLException {
        ObservableList<Transaksi> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM transactions";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Transaksi transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getDate("tanggal_transaksi"), rs.getString("name_seller"),
                    rs.getInt("id_barang"), rs.getInt("total_jual"), rs.getDouble("profit_total"), rs.getInt("kuantitas_jual"),
                    rs.getInt("id_role"));
            list.add(transaksi);
        }
        return list;
    }

    public ObservableList<History_employees> getHistory_employees() throws SQLException {
        ObservableList<History_employees> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM history_employees";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            History_employees history_employees = new History_employees(rs.getInt("id_history_employee"),
                    rs.getDate("date_start_employee"), rs.getDate("date_end_employee"));
            list.add(history_employees);
        }
        return list;
    }

    public ObservableList<History_executives> getHistory_executives() throws SQLException {
        ObservableList<History_executives> list = FXCollections.observableArrayList();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM history_executives";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            History_executives history_executives = new History_executives(rs.getInt("id_history_executive"),
                    rs.getDate("date_start_executive"), rs.getDate("date_end_executive"));
            list.add(history_executives);
        }
        return list;
    }



    public void view_table(String table) throws SQLException {
        String query = "SELECT * FROM " + table;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            switch (table) {
                case "Departments" -> {
                    ObservableList<Department> list = getDepartment();
                    table_view_dept.setItems(list);
                    id_department_column.setCellValueFactory(new PropertyValueFactory<>("id_department"));
                    name_department_column.setCellValueFactory(new PropertyValueFactory<>("name_department"));
                    jobDesc_column.setCellValueFactory(new PropertyValueFactory<>("jobdesc"));
                }

                case "Roles" -> {
                    ObservableList<Role> list = getRole();
                    table_view_role.setItems(list);
                    id_role_column.setCellValueFactory(new PropertyValueFactory<>("id_role"));
                    name_role_column.setCellValueFactory(new PropertyValueFactory<>("name_role"));
                    id_department_role_column.setCellValueFactory(new PropertyValueFactory<>("id_department"));
                }
                case "Employees" -> {
                    ObservableList<Pegawai> list = getPegawai();
                    table_view_emp.setItems(list);
                    id_pegawai_column.setCellValueFactory(new PropertyValueFactory<>("id_pegawai"));
                    nama_pegawai_column.setCellValueFactory(new PropertyValueFactory<>("nama_pegawai"));
                    id_role_pegawai_column.setCellValueFactory(new PropertyValueFactory<>("id_role"));
                    id_history_employee_pegawai_column.setCellValueFactory(new PropertyValueFactory<>("id_history_employee"));
                }
                case "Executives" -> {
                    ObservableList<Anggota> list = getAnggota();
                    table_view_anggota.setItems(list);
                    id_anggota_column.setCellValueFactory(new PropertyValueFactory<>("id_anggota"));
                    nama_anggota_column.setCellValueFactory(new PropertyValueFactory<>("nama_anggota"));
                    id_role_anggota_column.setCellValueFactory(new PropertyValueFactory<>("id_role"));
                    id_history_executive_anggota_column.setCellValueFactory(new PropertyValueFactory<>("id_history_executive"));
                }
                case "Stocks" -> {
                    ObservableList<Barang> list = getBarang();
                    table_view_barang.setItems(list);
                    id_barang_column.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
                    nama_barang_column.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
                    stock_barang_column.setCellValueFactory(new PropertyValueFactory<>("stock_barang"));
                    harga_pokok_barang_column.setCellValueFactory(new PropertyValueFactory<>("harga_pokok"));
                    harga_jual_barang_column.setCellValueFactory(new PropertyValueFactory<>("harga_jual"));
                    id_kategori_barang_column.setCellValueFactory(new PropertyValueFactory<>("id_kategori"));
                }
                case "Categories" -> {
                    ObservableList<Kategori> list = getKategori();
                    table_view_kategori.setItems(list);
                    id_kategori_column.setCellValueFactory(new PropertyValueFactory<>("id_kategori"));
                    nama_kategori_column.setCellValueFactory(new PropertyValueFactory<>("nama_kategori"));
                }
                case "Transactions" -> {
                    ObservableList<Transaksi> list = getTransaksi();
                    table_view_trans.setItems(list);
                    id_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("id_transaksi"));
                    tanggal_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("tanggal_transaksi"));
                    name_seller_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("name_seller"));
                    id_barang_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
                    total_jual_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("total_jual"));
                    profit_total_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("profit_total"));
                    kuantitas_jual_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("kuantitas_jual"));
                    id_role_transaksi_column.setCellValueFactory(new PropertyValueFactory<>("id_role"));
                }
                case "History_employees" -> {
                    ObservableList<History_employees> list = getHistory_employees();
                    table_view_hist_emp.setItems(list);
                    id_history_employee_column.setCellValueFactory(new PropertyValueFactory<>("id_history_employee"));
                    date_start_employee_column.setCellValueFactory(new PropertyValueFactory<>("date_start_employee"));
                    date_end_employee_column.setCellValueFactory(new PropertyValueFactory<>("date_end_employee"));
                }
                case "History_executives" -> {
                    ObservableList<History_executives> list = getHistory_executives();
                    table_view_hist_anggota.setItems(list);
                    id_history_anggota_column.setCellValueFactory(new PropertyValueFactory<>("id_history_executive"));
                    date_start_anggota_column.setCellValueFactory(new PropertyValueFactory<>("date_start_executive"));
                    date_end_anggota_column.setCellValueFactory(new PropertyValueFactory<>("date_end_executive"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insert_into_table(String table) throws SQLException {
        con = DBConnection.getConnection();
        try {
            switch (table) {
                case "Departments" -> {
                    String SQL_dept = """
                        INSERT INTO departments
                        VALUES (?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_dept);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2,textField2.getText());
                    st.setString(3, textField3.getText());

                    st.execute();
                    System.out.println("INSERTED");
                    view_table(table);
                }
                case "Roles" -> {
                    String SQL_role = """
                        INSERT INTO roles
                        VALUES (?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_role);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2,textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));

                    st.execute();
                    view_table(table);
                }
                case "Employees" -> {
                    String SQL_emp = """
                        INSERT INTO employees
                        VALUES (?, ?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_emp);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2,textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setInt(4, Integer.parseInt(textField4.getText()));

                    st.execute();
                    view_table(table);
                }
                case "Executives" -> {
                    String SQL_anggota = """
                        INSERT INTO executives
                        VALUES (?, ?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_anggota);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2,textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setInt(4, Integer.parseInt(textField4.getText()));

                    st.execute();
                    view_table(table);
                }
                case "Stocks" -> {
                    String SQL_barang = """
                        INSERT INTO stocks
                        VALUES (?, ?, ?, ?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_barang);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2,textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setDouble(4, Double.parseDouble(textField4.getText()));
                    st.setDouble(5, Double.parseDouble(textField5.getText()));
                    st.setInt(6, Integer.parseInt(textField6.getText()));

                    st.execute();
                    view_table(table);
                }
                case "Categories" -> {
                    String SQL_kategori = """
                        INSERT INTO categories
                        VALUES (?, ?)
                        """;
                    st = con.prepareStatement(SQL_kategori);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2,textField2.getText());

                    st.execute();
                    view_table(table);
                }
                case "Transactions" -> {
                    String SQL_trans = """
                        INSERT INTO transactions
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_trans);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setDate(2, java.sql.Date.valueOf(textField2.getText()));
                    st.setString(3, textField3.getText());
                    st.setInt(4, Integer.parseInt(textField4.getText()));
                    st.setInt(5, Integer.parseInt(textField5.getText()));
                    st.setDouble(6, Double.parseDouble(textField6.getText()));
                    st.setInt(7, Integer.parseInt(textField7.getText()));
                    st.setInt(8, Integer.parseInt(textField8.getText()));

                    st.execute();
                    view_table(table);
                }
                case "History_employees" -> {
                    String SQL_hist_emp = """
                        INSERT INTO History_employees
                        VALUES (?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_hist_emp);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setDate(2, java.sql.Date.valueOf(textField2.getText()));
                    st.setDate(3, java.sql.Date.valueOf(textField3.getText()));

                    st.execute();
                    view_table(table);
                }
                case "History_executives" -> {
                    String SQL_hist_anggota = """
                        INSERT INTO History_executives
                        VALUES (?, ?, ?)
                        """;
                    st = con.prepareStatement(SQL_hist_anggota);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setDate(2, java.sql.Date.valueOf(textField2.getText()));
                    st.setDate(3, java.sql.Date.valueOf(textField3.getText()));

                    st.execute();
                    view_table(table);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update_from_table(String table) throws SQLException {
        con = DBConnection.getConnection();
        try {
            switch (table) {
                case "Departments" -> {
                    String update_dept_query = "UPDATE Departments SET id_department = ?, name_department = ?, jobdesc = ? WHERE id_department = ?";
                    st = con.prepareStatement(update_dept_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2, textField2.getText());
                    st.setString(3, textField3.getText());
                    st.setInt(4, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "Roles" -> {
                    String update_role_query = "UPDATE Roles SET id_role = ?, name_role = ?, id_department = ? WHERE id_role = ?";
                    st = con.prepareStatement(update_role_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2, textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setInt(4, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "Employees" -> {
                    String update_emp_query = "UPDATE Employees SET id_pegawai = ?, nama_pegawai = ?, id_role = ?, id_history_employee = ? WHERE id_pegawai = ?";
                    st = con.prepareStatement(update_emp_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2, textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setInt(4, Integer.parseInt(textField4.getText()));
                    st.setInt(5, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "Executives" -> {
                    String update_exc_query = "UPDATE Executives SET id_anggota = ?, name_anggota = ?, id_role = ?, id_history_executive = ? WHERE id_anggota = ?";
                    st = con.prepareStatement(update_exc_query);

                    System.out.println(label1.getText());
                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2, textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setInt(4, Integer.parseInt(textField4.getText()));
                    st.setInt(5, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "Stocks" -> {
                    String update_stk_query = "UPDATE Stocks SET id_barang = ?, nama_barang = ?, stock_barang = ?, harga_pokok = ?, harga_jual = ?, id_kategori = ? WHERE id_barang = ?";
                    st = con.prepareStatement(update_stk_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2, textField2.getText());
                    st.setInt(3, Integer.parseInt(textField3.getText()));
                    st.setDouble(4, Double.parseDouble(textField4.getText()));
                    st.setDouble(5, Double.parseDouble(textField5.getText()));
                    st.setInt(6, Integer.parseInt(textField6.getText()));
                    st.setInt(7, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "Categories" -> {
                    String update_ctg_query = "UPDATE Categories SET id_kategori = ?, nama_kategori = ? WHERE id_kategori = ?";
                    st = con.prepareStatement(update_ctg_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setString(2, textField2.getText());
                    st.setInt(3, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "Transactions" -> {
                    String update_tsc_query = "UPDATE Transactions SET id_transaksi = ?, tanggal_transaksi = ?, name_seller = ?, id_barang = ?, total_jual = ?, profit_total = ?, kuantitas_jual = ?, id_role = ? WHERE id_transaksi = ?";
                    st = con.prepareStatement(update_tsc_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setDate(2, java.sql.Date.valueOf(textField2.getText()));
                    st.setString(3, textField3.getText());
                    st.setInt(4, Integer.parseInt(textField4.getText()));
                    st.setInt(5, Integer.parseInt(textField5.getText()));
                    st.setDouble(6, Double.parseDouble(textField6.getText()));
                    st.setInt(7, Integer.parseInt(textField7.getText()));
                    st.setInt(8, Integer.parseInt(textField8.getText()));
                    st.setInt(9, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "History_employees" -> {
                    String update_hist_emp_query = "UPDATE history_employees SET id_history_employee = ?, date_start_employee = ?, date_end_employee = ? WHERE id_history_employee = ?";
                    st = con.prepareStatement(update_hist_emp_query);

                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setDate(2, java.sql.Date.valueOf(textField2.getText()));
                    st.setDate(3, java.sql.Date.valueOf(textField3.getText()));
                    st.setInt(4, id_selected);

                    st.execute();

                    view_table(table);
                }
                case "History_executives" -> {
                    String update_hist_exc_query = "UPDATE history_executives SET id_history_employee = ?, date_start_executive = ?, date_end_executive = ? WHERE id_history_executive = ?";
                    st = con.prepareStatement(update_hist_exc_query);

                    System.out.println(label1.getText());
                    st.setInt(1, Integer.parseInt(textField1.getText()));
                    st.setDate(2, java.sql.Date.valueOf(textField2.getText()));
                    st.setDate(3, java.sql.Date.valueOf(textField3.getText()));
                    st.setInt(4, id_selected);

                    st.execute();

                    view_table(table);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating the table: " + e.getMessage());
        }
    }


    @Override
    public void delete_from_table(String table) throws SQLException {
        con = DBConnection.getConnection();
        try {
            switch (table) {
                case "Departments" -> {

                    String SQL_dept = "DELETE FROM " + table + " WHERE id_department = ?";
                    st = con.prepareStatement(SQL_dept);
                    st.setInt(1,id_selected);
                    st.execute();

                    view_table(table);
                }
                case "Roles" -> {

                    String SQL_role = "DELETE FROM " + table + " WHERE id_role = ?";
                    st = con.prepareStatement(SQL_role);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "Employees" -> {

                    String SQL_pegawai = "DELETE FROM " + table + " WHERE id_pegawai = ?";
                    st = con.prepareStatement(SQL_pegawai);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "Executives" -> {

                    String SQL_anggota = "DELETE FROM " + table + " WHERE id_anggota = ?";
                    st = con.prepareStatement(SQL_anggota);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "Stocks" -> {

                    String SQL_barang = "DELETE FROM " + table + " WHERE id_barang = ?";
                    st = con.prepareStatement(SQL_barang);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "Categories" -> {

                    String SQL_kategori = "DELETE FROM " + table + " WHERE id_kategori = ?";
                    st = con.prepareStatement(SQL_kategori);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "Transactions" -> {

                    String SQL_transaksi = "DELETE FROM " + table + " WHERE id_transaksi = ?";
                    st = con.prepareStatement(SQL_transaksi);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "History_employees" -> {

                    String SQL_history_employee = "DELETE FROM " + table + " WHERE id_history_employee = ?";
                    st = con.prepareStatement(SQL_history_employee);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
                case "History_executives" -> {

                    String SQL_history_executive = "DELETE FROM " + table + " WHERE id_history_executive = ?";
                    st = con.prepareStatement(SQL_history_executive);
                    st.setInt(1, id_selected);
                    st.execute();

                    view_table(table);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}