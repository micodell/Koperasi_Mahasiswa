package com.koperasi.proyekpbo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ComboBox<String> drop_down_menu;

    @FXML
    private Button insert_button;

    @FXML
    private Button update_button;

    @FXML
    private Button delete_button;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> jobDescColumn;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Label pesan_awal;

    @FXML
    private TableView<?> table_view_anggota;

    @FXML
    private TableView<?> table_view_awal;

    @FXML
    private TableView<?> table_view_barang;

    @FXML
    private TableView<?> table_view_dept;

    @FXML
    private TableView<?> table_view_emp;

    @FXML
    private TableView<?> table_view_kategori;

    @FXML
    private TableView<?> table_view_role;

    @FXML
    private TableView<?> table_view_trans;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private TextField textField5;


    @FXML
    void onDeleteClicked(ActionEvent event) {

    }

    @FXML
    void onInsertClicked(ActionEvent event) {

    }

    @FXML
    void onUpdateClicked(ActionEvent event) {

    }

}