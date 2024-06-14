package com.koperasi.proyekpbo;

public class Role {
    private String id_role;
    private String name_role;
    private String jobdesc;

    public Role(String id_role, String name_role, String jobdesc) {
        this.id_role = id_role;
        this.name_role = name_role;
        this.jobdesc = jobdesc;
    }

    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }

    public String getName_role() {
        return name_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }
}
