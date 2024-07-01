module com.koperasi.proyekpbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.koperasi.proyekpbo to javafx.fxml;
    exports com.koperasi.proyekpbo;
}