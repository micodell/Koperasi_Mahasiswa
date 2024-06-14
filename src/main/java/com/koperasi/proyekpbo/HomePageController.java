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
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
