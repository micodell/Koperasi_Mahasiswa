module com.koperasi.proyekpbo {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.koperasi.proyekpbo to javafx.fxml;
    exports com.koperasi.proyekpbo;
}