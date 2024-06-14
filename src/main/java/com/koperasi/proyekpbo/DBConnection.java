package com.koperasi.proyekpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/BD";
    private static final String USER = "postgres";
    private static final String PASSWORD = "223344";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method untuk mendapatkan koneksi ke database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static void main(String[] args) throws SQLException {
        getConnection();
    }
}
