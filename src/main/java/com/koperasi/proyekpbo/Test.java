package com.koperasi.proyekpbo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    static void view_table(String table) throws SQLException {
        String SQL = "SELECT * FROM public." + table;
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                switch (table) {
                    case "departments" -> {
                        Department dept = new Department();
                        ObservableList<Department> listOfDept = FXCollections.observableArrayList();

                        dept.setId_department(rs.getInt("id_department"));
//                        untuk mengambil value dari table yang kita query
                        dept.setName_department(rs.getString("name_department"));
                        listOfDept.add(dept);
//                        for (int i = 0; i < listOfDept.size(); i++) {
//                            System.out.println(listOfDept.get(i).getId() +
//                                    "\t\t" + listOfDept.get(i).getName_department());
//                        }
                    }
                    case "Role" -> {}
                    case "Pegawai" -> {}
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
//        view_table("departments");
    }

}
