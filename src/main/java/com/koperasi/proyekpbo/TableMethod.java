package com.koperasi.proyekpbo;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TableMethod {
    ObservableList<DataType> view_table (String table) throws SQLException;
    void insert_into_table (String table) throws SQLException;
    void update_from_table (String table) throws SQLException;
    void delete_from_table (String table) throws SQLException;
}