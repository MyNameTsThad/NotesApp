package com.iwant2tryhard.mynotesapp.core;

public class NoteUser {
    private long ID;
    private String USERNAME, PASSWORD;

    public NoteUser(){
        //Log.d("NoteObj", "Created Note:1");}
    }

    public NoteUser(long id, String username, String password){
        //Log.d("NoteObj", "Created Note:2");
        this.ID = id;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public NoteUser(String username, String password){
        //Log.d("NoteObj", "Created Note:3");
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
