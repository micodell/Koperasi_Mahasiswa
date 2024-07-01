package com.koperasi.proyekpbo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HomePageController implements TableMethod {

    @FXML
    private ComboBox<String> drop_down_menu;

    @FXML
    private TableView<Department> table_view_awal;
    @FXML
    private TableView<DataType> table_view_dept;
    @FXML
    private TableColumn<Department, Integer> id_department_column;
    @FXML
    private TableColumn<Department, String> name_department_column;
    @FXML
    private TableColumn<Department, String> jobDesc_column;

    @FXML
    private TableView<DataType> table_view_role;
    @FXML
    private TableView<DataType> table_view_emp;
    @FXML
    private TableColumn<Pegawai, Integer> id_pegawai_column;
    @FXML
    private TableColumn<Pegawai, String> nama_pegawai_column;

    @FXML
    private TableView<Anggota> table_view_anggota;
    @FXML
    private TableView<Barang> table_view_barang;
    @FXML
    private TableView<Kategori> table_view_kategori;
    @FXML
    private TableView<Transaksi> table_view_trans;

    @FXML
    private Button insert_button;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;

    @FXML
    private Label pesan_awal, label1, label2, label3, label4, label5;

    @FXML
    private TextField textField1, textField2, textField3, textField4, textField5;

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    Scanner input = new Scanner(System.in);
//    @FXML
//    void make_invisible() throws SQLException {
//        table_view.setVisible(false);
//        insert_into_table(drop_down_menu.getValue());
//    }

//    private ObservableList<DataType> data;
    @FXML
    void initialize() {
        drop_down_menu.setItems(
                FXCollections.observableArrayList(
                        "Departments",
                        "Roles",
                        "Employees",
                        "Executives(Anggota)",
                        "Stocks(Barang)",
                        "Categories(Kategori)",
                        "Transactions"
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

        insert_button.setOnAction(this::onInsertClicked);
        update_button.setOnAction(this::onUpdateClicked);
        delete_button.setOnAction(this::onDeleteClicked);
    }

    @FXML
    void onInsertClicked (ActionEvent event) {
        try {
            String selected = drop_down_menu.getValue();

            switch (selected) {
                case "Departments":
                    update_from_table("Department");
                    break;
                case "Roles":
                    update_from_table("Role");
                    break;
                case "Employees":
                    update_from_table("Pegawai");
                    break;
                case "Executives(Anggota)":
                    update_from_table("Anggota");
                    break;
                case "Stocks(Barang)":
                    update_from_table("Barang");
                    break;
                case "Categories(Kategori)":
                    update_from_table("Kategori");
                    break;
                case "Transactions":
                    update_from_table("Transaksi");
                    break;
                default:
                    System.out.println("Pilih tabel terlebih dahulu untuk melakukan insert.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    void onUpdateClicked (ActionEvent event) {
        try {
            String selected = drop_down_menu.getValue();

            switch (selected) {
                case "Departments":
                    update_from_table("Department");
                    break;
                case "Roles":
                    update_from_table("Role");
                    break;
                case "Employees":
                    update_from_table("Pegawai");
                    break;
                case "Executives(Anggota)":
                    update_from_table("Anggota");
                    break;
                case "Stocks(Barang)":
                    update_from_table("Barang");
                    break;
                case "Categories(Kategori)":
                    update_from_table("Kategori");
                    break;
                case "Transactions":
                    update_from_table("Transaksi");
                    break;
                default:
                    System.out.println("Pilih tabel terlebih dahulu untuk melakukan update.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    void onDeleteClicked (ActionEvent event) {
        try {
            String selected = drop_down_menu.getValue();

            switch (selected) {
                case "Departments":
                    update_from_table("Department");
                    break;
                case "Roles":
                    update_from_table("Role");
                    break;
                case "Employees":
                    update_from_table("Pegawai");
                    break;
                case "Executives(Anggota)":
                    update_from_table("Anggota");
                    break;
                case "Stocks(Barang)":
                    update_from_table("Barang");
                    break;
                case "Categories(Kategori)":
                    update_from_table("Kategori");
                    break;
                case "Transactions":
                    update_from_table("Transaksi");
                    break;
                default:
                    System.out.println("Pilih tabel terlebih dahulu untuk melakukan update.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    void onExitClicked (ActionEvent event) {
        Platform.exit();
    }

//    @FXML
//    void onTableSelectionChanged() throws SQLException {
//        String selectedTable = drop_down_menu.getValue();
//        updateFieldsVisibility(selectedTable);
//    }

    private void updateFieldsVisibility(String selectedTable) throws SQLException {
        pesan_awal.setVisible(true);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);

        table_view_awal.setVisible(false);
        table_view_dept.setVisible(false);
        table_view_role.setVisible(false);
        table_view_emp.setVisible(false);
        table_view_anggota.setVisible(false);
        table_view_barang.setVisible(false);
        table_view_kategori.setVisible(false);
        table_view_trans.setVisible(false);

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
                label1.setVisible(true);
                label2.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_role.setVisible(true);
            }
            case "Employees" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_employee");
                label2.setText("name_employee");
                label1.setVisible(true);
                label2.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_emp.setVisible(true);
            }
            case "Executives(Anggota)" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_anggota");
                label2.setText("name_anggota");
                label1.setVisible(true);
                label2.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_anggota.setVisible(true);
            }
            case "Stocks(Barang)" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_barang");
                label2.setText("nama_barang");
                label3.setText("harga_pokok");
                label4.setText("harga_jual");
                label5.setText("stock_barang");
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);
                textField5.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_barang.setVisible(true);
            }
            case "Categories(Kategori)" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_kategori");
                label2.setText("nama_kategori");
                label1.setVisible(true);
                label2.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_kategori.setVisible(true);
            }
            case "Transactions" -> {
                pesan_awal.setVisible(false);
                label1.setText("id_transaksi");
                label2.setText("tanggal_transaksi");
                label3.setText("nama_seller");
                label4.setText("profit_total");
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);

                table_view_awal.setVisible(false);
                table_view_trans.setVisible(true);
            }
        }
    }

    public ObservableList<DataType> view_table(String table) throws SQLException {
        ObservableList<DataType> data = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM " + table;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                switch (table) {
                    case "Departments" -> {
                        data.add(new Department(rs.getInt("id_department"),rs.getString("name_department"),rs.getString("jobdesc")));
                        ObservableList<DataType> departmentObservableList = view_table(table);
                        table_view_dept.setItems(departmentObservableList);
                        id_department_column.setCellValueFactory(new PropertyValueFactory<>("id_department"));
                        name_department_column.setCellValueFactory(new PropertyValueFactory<>("name_department"));
                        jobDesc_column.setCellValueFactory(new PropertyValueFactory<>("jobdesc"));
                    }

                    case "Role" -> {
                        Role role = new Role();
                        role.setId_role(rs.getInt("id_role"));
                        role.setName_role(rs.getString("name_role"));
                        role.setId_department(rs.getInt("id_department"));
                        data.add(role);
                    }
                    case "Pegawai" -> {
                        data.add(new Pegawai(rs.getInt("id_pegawai"), rs.getString("nama_pegawai")));
                        ObservableList<DataType> pegawaiObservableList = view_table(table);
                        table_view_emp.setItems(pegawaiObservableList);
                        id_pegawai_column.setCellValueFactory(new PropertyValueFactory<>("id_pegawai"));
                        nama_pegawai_column.setCellValueFactory(new PropertyValueFactory<>("nama_pegawai"));
                    }
                    case "Transaksi" -> {
                        Transaksi transaksi = new Transaksi();
                        ObservableList<Transaksi> listOfTransaction = FXCollections.observableArrayList();
                        transaksi.setId_transaksi(rs.getInt("transaction_id"));
                        transaksi.setTanggal_transaksi(rs.getString("transaction_date"));
                        transaksi.setNama_seller(rs.getString("name_seller"));
                        transaksi.setProfit_total(rs.getInt("total_profit"));
                        listOfTransaction.add(transaksi);
//                        for (int i = 0; i < listOfTransaction.size(); i++) {
//                            System.out.println(listOfTransaction.get(i).getId_transaksi() +
//                                    " " + listOfTransaction.get(i).getTanggal_transaksi() +
//                                    " " + listOfTransaction.get(i).getNama_seller() +
//                                    " " + listOfTransaction.get(i).getProfit_total());
//                        }
//                        table_view_awal.setItems(FXCollections.observableArrayList());
                    }
                    case "Barang" -> {
                        Barang brg = new Barang();
                        ObservableList<Barang> listOfItem = FXCollections.observableArrayList();
                        brg.setId_barang(rs.getInt("item_id"));
                        brg.setNama_barang(rs.getString("item_name"));
                        brg.setHarga_pokok(rs.getInt("harga_pokok"));
                        brg.setHarga_jual(rs.getInt("harga_jual"));
                        listOfItem.add(brg);
//                        for (int i = 0; i < listOfItem.size(); i++) {
//                            System.out.println(listOfItem.get(i).getId_barang() +
//                                    " " + listOfItem.get(i).getNama_barang() +
//                                    " " + listOfItem.get(i).getHarga_pokok() +
//                                    " " + listOfItem.get(i).getHarga_jual());
//                        }
//                        table_view_awal.setItems(FXCollections.observableArrayList());
                    }
                    case "Kategori" -> {
                        Kategori kategori = new Kategori();
                        ObservableList<Kategori> listOfCategory = FXCollections.observableArrayList();
                        kategori.setId_kategori(rs.getInt("category_id"));
                        kategori.setNama_kategori(rs.getString("category_name"));
                        listOfCategory.add(kategori);
//                        for (int i = 0; i < listOfCategory.size(); i++) {
//                            System.out.println(listOfCategory.get(i).getId_kategori() +
//                                    " " + listOfCategory.get(i).getNama_kategori());
//                        }
//                        table_view_awal.setItems(FXCollections.observableArrayList());
                    }
                    case "Anggota" -> {
                        Anggota anggota = new Anggota();
                        ObservableList<Anggota> listOfExecutives = FXCollections.observableArrayList();
                        anggota.setId_anggota(rs.getInt("id_anggota"));
                        anggota.setNama_anggota(rs.getString("nama_anggota"));
                        listOfExecutives.add(anggota);
//                        for (int i = 0; i < listOfExecutives.size(); i++) {
//                            System.out.println(listOfExecutives.get(i).getId_anggota() +
//                                    " " + listOfExecutives.get(i).getNama_anggota());
//                        }
//                        table_view_anggota.setItems(FXCollections.observableArrayList());
                    }
                }
            } return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        switch (table) {
//            case "Departments" -> table_view_dept.setItems(data);
//            case "Role" -> table_view_role.setItems(data);
//            case "Pegawai" -> table_view_emp.setItems(data);
//            case "Anggota" -> table_view_anggota.setItems(data);
//            case "Barang" -> table_view_barang.setItems(data);
//            case "Kategori" -> table_view_kategori.setItems(data);
//            case "Transaksi" -> table_view_trans.setItems(data);
//        }
    }
//
//            stmt.executeUpdate();
    public void insert_into_table(String table) throws SQLException {
        con = DBConnection.getConnection();
//        table_view.setVisible(false);
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
                    st.setString(1,textField1.getText());
                    System.out.println("nama department: ");
                    String name_department = input.nextLine();
                    st.setString(2,textField2.getText());

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

//    @Override
//    public void update_from_table(String table) throws SQLException {
//        con = DBConnection.getConnection();
//        try {
//            switch (table) {
//                case "Department" -> {
//                    String update_dept_query = "UPDATE " + table + " SET id_department = ?, name_department = ? WHERE id = ?";
//                    st = con.prepareStatement(update_dept_query);
//
//                    System.out.println("id department: ");
//                    int id_department = Integer.parseInt(input.nextLine());
//                    System.out.println("nama department: ");
//                    String name_department = input.nextLine();
//
//                    st.setString(1, String.valueOf(id_department));
//                    st.setString(2, name_department);
//
//                    view_table(table);
//                }
//                case "Role" -> {
//                }
//                case "Pegawai" -> {}
//                case "Anggota" -> {}
//                case "Barang" -> {}
//                case "Kategori" -> {}
//                case "Transaksi" -> {}
//            }
//            rs = st.executeQuery();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Override
    public void update_from_table(String table) throws SQLException {
        con = DBConnection.getConnection();
        try {
            switch (table) {
                case "Department" -> {
                    String update_dept_query = "UPDATE Department SET name_department = ? WHERE id_department = ?";
                    st = con.prepareStatement(update_dept_query);

                    System.out.println("id department: ");
                    System.out.println("nama department: ");

                    st.setString(1, label1.getText());
                    st.setString(2, label2.getText());
                    st.execute();

                    int rowsUpdated = st.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Department updated successfully.");
                    }

                    view_table(table);
                }
                case "Role" -> {
                }
                case "Pegawai" -> {}
                case "Anggota" -> {}
                case "Barang" -> {}
                case "Kategori" -> {}
                case "Transaksi" -> {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating the table: " + e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void delete_from_table(String table) throws SQLException {
        con = DBConnection.getConnection();
        try {
            switch (table) {
                case "Department" -> {

                    String SQL_dept = "DELETE FROM " + table + " WHERE id_department = ?";
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
}
