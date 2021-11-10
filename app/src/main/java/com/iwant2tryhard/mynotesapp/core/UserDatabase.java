package com.iwant2tryhard.mynotesapp.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "USER_DB";
    private static final String DATABASE_TABLE = "USER_TABLE";
    private static final int DATABASE_VERSION_NUMBER = 1;
    private static final String DATABASE_VERSION_STRING = "0.1a";

    //data
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + " (" +
                KEY_ID + " INT PRIMARY KEY," +
                KEY_USERNAME + " TEXT," +
                KEY_PASSWORD + " TEXT" + " )";

        db.execSQL(query);

        addUser(new NoteUser(0, "thad", ""));
        addUser(new NoteUser(1, "pudit", ""));
        addUser(new NoteUser(2, "thitirat", ""));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addUser(NoteUser user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, user.getUSERNAME());
        contentValues.put(KEY_PASSWORD, user.getPASSWORD());

        long id = db.insert(DATABASE_TABLE, null, contentValues);
        Log.d("Inserted", "ID -> " + id);
        return id;
    }


    public NoteUser getUser(long id) {
        List<NoteUser> allUsers = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                NoteUser user = new NoteUser();
                user.setID(cursor.getLong(0));
                user.setUSERNAME(cursor.getString(1));
                user.setPASSWORD(cursor.getString(2));
                allUsers.add(user);
            } while (cursor.moveToNext());
        }

        NoteUser result = new NoteUser();

        for (int i = 0; i < allUsers.size(); i++) {
            NoteUser NoteUser = allUsers.get(i);
            if (i == id) {
                result = NoteUser;
            }
        }

        return result;
    }

    public List<NoteUser> getAllUsers() {
        List<NoteUser> allUsers = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                NoteUser note = new NoteUser();
                note.setID(cursor.getLong(0));
                note.setUSERNAME(cursor.getString(1));
                note.setPASSWORD(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        return allUsers;

    }
}
