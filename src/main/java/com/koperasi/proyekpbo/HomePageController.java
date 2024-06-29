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
                        table = "departments";
                        Department dept = new Department();
                        ObservableList<Department> listOfDept = FXCollections.observableArrayList();

                        dept.setId_department(rs.getString("id_department"));
//                        untuk mengambil value dari table yang kita query
                        dept.setName_department(rs.getString("name_department"));
                        listOfDept.add(dept);
                        for (int i = 0; i < listOfDept.size(); i++) {
                            System.out.println(listOfDept.get(i));
                        }
                    }
                    case "Role" -> {}
                    case "Pegawai" -> {}
                    case "Anggota" -> {}
                    case "Barang" -> {}
                    case "Kategori" -> {}
                    case "Transaksi" -> {}
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
}
