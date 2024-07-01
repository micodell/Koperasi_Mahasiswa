package com.koperasi.proyekpbo;

public class Role implements DataType{
    private int id_role;
    private String name_role;
    private int id_department;

    public Role(int id_role, String name_role, String jobdesc) {
        this.id_role = id_role;
        this.name_role = name_role;
        this.id_department = id_department;
    }

    public Role() {

    }

    @Override
    public int getId() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getName_role() {
        return name_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }
}
