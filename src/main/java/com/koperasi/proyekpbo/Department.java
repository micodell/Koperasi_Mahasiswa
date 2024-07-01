package com.koperasi.proyekpbo;

public class Department implements DataType{
    private int id_department;
    private String name_department;
    private String jobdesc;


    public Department(int id_department, String name_department, String jobdesc) {
        this.id_department = id_department;
        this.name_department = name_department;
        this.jobdesc = jobdesc;
    }

    public Department() {
    }

    @Override
    public int getId() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getName_department() {
        return name_department;
    }

    public void setName_department(String name_department) {
        this.name_department = name_department;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }
}