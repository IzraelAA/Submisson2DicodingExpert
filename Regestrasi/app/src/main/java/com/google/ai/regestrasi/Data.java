package com.google.ai.regestrasi;

public class Data {
    public String User;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
    public Data(){

    }
    public Data(String user, String name, String pass, String email) {
        this.user = user;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }
    private String user;
    private String pass;
    private String email;
    private String nohp;
}
