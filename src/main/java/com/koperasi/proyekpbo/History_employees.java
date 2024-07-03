package com.koperasi.proyekpbo;

import java.sql.Date;

public class History_employees {
    private int id_history_employee;
    private Date date_start_employee;
    private Date date_end_employee;

    public History_employees(int id_history_employee, Date date_start_employee, Date date_end_executive) {
        this.id_history_employee = id_history_employee;
        this.date_start_employee = date_start_employee;
        this.date_end_employee = date_end_executive;
    }
    public History_employees(){

    }

    public int getId_history_employee() {
        return id_history_employee;
    }

    public void setId_history_employee(int id_history_employee) {
        this.id_history_employee = id_history_employee;
    }

    public Date getDate_start_employee() {
        return date_start_employee;
    }

    public void setDate_start_employee(Date date_start_employee) {
        this.date_start_employee = date_start_employee;
    }

    public Date getDate_end_employee() {
        return date_end_employee;
    }

    public void setDate_end_employee(Date date_end_executive) {
        this.date_end_employee = date_end_executive;
    }
}