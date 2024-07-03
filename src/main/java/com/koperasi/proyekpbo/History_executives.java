package com.koperasi.proyekpbo;

import java.util.Date;

public class History_executives {
    private int id_history_executive;
    private Date date_start_executive;
    private Date date_end_executive;

    public History_executives(int id_history_executive, Date date_start_executive, Date date_end_executive) {
        this.id_history_executive = id_history_executive;
        this.date_start_executive = date_start_executive;
        this.date_end_executive = date_end_executive;
    }
    public void History_executive(){

    }

    public int getId_history_executive() {
        return id_history_executive;
    }

    public void setId_history_executive(int id_history_executive) {
        this.id_history_executive = id_history_executive;
    }

    public Date getDate_start_executive() {
        return date_start_executive;
    }

    public void setDate_start_executive(Date date_start_executive) {
        this.date_start_executive = date_start_executive;
    }

    public Date getDate_end_executive() {
        return date_end_executive;
    }

    public void setDate_end_executive(Date date_end_executive) {
        this.date_end_executive = date_end_executive;
    }
}