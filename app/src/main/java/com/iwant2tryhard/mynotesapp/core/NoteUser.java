package com.iwant2tryhard.mynotesapp.core;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteUser {
    private long ID;
    private String DISPLAYNAME, USERNAME, PASSWORD;

    public NoteUser() {
        //Log.d("NoteObj", "Created Note:1");}
    }

    public NoteUser(long id, String displayName, String username, String password) {
        //Log.d("NoteObj", "Created Note:2");
        this.ID = id;
        this.DISPLAYNAME = displayName;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public NoteUser(String displayName, String username, String password) {
        //Log.d("NoteObj", "Created Note:3");
        this.DISPLAYNAME = displayName;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public NoteUser(String username, String password) {
        //Log.d("NoteObj", "Created Note:3");
        this.DISPLAYNAME = username;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public static NoteUser mapToUser(String id, Map<String, String> map) {
        NoteUser result = new NoteUser();
        result.setID(Long.parseLong(id));
        result.setDISPLAYNAME(map.get("displayName"));
        result.setUSERNAME(map.get("username"));
        result.setPASSWORD(map.get("password"));
        return result;
    }

    public static List<NoteUser> mapsToUser(Map<String, Map<String, String>> map) {
        List<NoteUser> result = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {
            String id = (String) map.keySet().toArray()[i];
            Map<String, String> content = map.get(id);
            NoteUser subresult = mapToUser(id, content);
            result.add(subresult);
        }

        return result;
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

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    @NonNull
    @Override
    public String toString() {
        return "NoteUser{" +
                "ID=" + ID +
                ", DISPLAYNAME='" + DISPLAYNAME + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                '}';
    }
}
