package com.iwant2tryhard.mynotesapp.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StorageDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NOTES_DB";
    private static final String DATABASE_TABLE = "NOTES_TABLE";
    private static final int DATABASE_VERSION_NUMBER = 1;
    private static final String DATABASE_VERSION_STRING = "0.1a";

    //data
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public StorageDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + " (" +
                KEY_ID + " INT PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_DATE + " TEXT," +
                KEY_TIME + " TEXT" + " )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addNote(NoteObj note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, note.getTITLE());
        contentValues.put(KEY_CONTENT, note.getCONTENT());
        contentValues.put(KEY_DATE, note.getDATE());
        contentValues.put(KEY_TIME, note.getTIME());

        long id = db.insert(DATABASE_TABLE, null, contentValues);
        Log.d("Inserted", "ID -> " + id);
        return id;
    }


    public NoteObj getNote(long id) {
        List<NoteObj> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                NoteObj note = new NoteObj();
                note.setID(cursor.getLong(0));
                note.setTITLE(cursor.getString(1));
                note.setCONTENT(cursor.getString(2));
                note.setDATE(cursor.getString(3));
                note.setTIME(cursor.getString(4));
                allNotes.add(note);
            } while (cursor.moveToNext());
        }

        NoteObj result = new NoteObj();

        Log.d("db-getNote-receivedId", String.valueOf(id));

        for (int i = 0; i < allNotes.size(); i++) {
            NoteObj noteobj = allNotes.get(i);
            Log.d("db-getNote-noteIterate", String.valueOf(noteobj.getID()));
            if (i == id) {
                result = noteobj;
            }
        }

        return result;
    }

    public List<NoteObj> getAllNotes() {
        List<NoteObj> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                NoteObj note = new NoteObj();
                note.setID(cursor.getLong(0));
                note.setTITLE(cursor.getString(1));
                note.setCONTENT(cursor.getString(2));
                note.setDATE(cursor.getString(3));
                note.setTIME(cursor.getString(4));
                allNotes.add(note);
            } while (cursor.moveToNext());
        }

        return allNotes;

    }
}
