package com.koperasi.proyekpbo;

import java.sql.SQLException;

public interface TableMethod {
    void view_table (String table) throws SQLException;
    void insert_into_table (String table) throws SQLException;
}
