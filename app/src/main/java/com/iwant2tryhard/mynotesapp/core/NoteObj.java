package com.iwant2tryhard.mynotesapp.core;

public class NoteObj {
    private long ID;
    private String TITLE, CONTENT, DATE, TIME;

    public NoteObj(){
        //Log.d("NoteObj", "Created Note:1");}
    }

    public NoteObj(long id, String title, String content, String date, String time){
        //Log.d("NoteObj", "Created Note:2");
        this.ID = id;
        this.TITLE = title;
        this.CONTENT = content;
        this.DATE = date;
        this.TIME = time;
    }

    public NoteObj(String title, String content, String date, String time){
        //Log.d("NoteObj", "Created Note:3");
        this.TITLE = title;
        this.CONTENT = content;
        this.DATE = date;
        this.TIME = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
