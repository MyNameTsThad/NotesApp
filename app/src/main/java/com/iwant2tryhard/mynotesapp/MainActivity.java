package com.iwant2tryhard.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iwant2tryhard.mynotesapp.core.AppData;
import com.iwant2tryhard.mynotesapp.core.NoteObj;
import com.iwant2tryhard.mynotesapp.core.StorageDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    Button noteInteractButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StorageDatabase db = new StorageDatabase(this);
        adapter = new Adapter(this, db.getAllNotes());
        recyclerView = findViewById(R.id.notesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (!AppData.isLoggedIn) {
            Intent i = new Intent(this, UserLogin.class);
            startActivity(i);
        }

        /*noteInteractButton = findViewById(R.id.noteInteractButton);
        if (noteInteractButton != null){
            noteInteractButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "clicked btn", Toast.LENGTH_SHORT).show();
                }
            });
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addMenuItem){
            Intent i = new Intent(this, AddNote.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.debugMenuItem) {
            StorageDatabase db = new StorageDatabase(this);
            Calendar calendar = Calendar.getInstance();
            String formattedDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
            String formattedTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));
            db.addNote(new NoteObj("11", "11", formattedDate, formattedTime, AppData.userId));
            db.addNote(new NoteObj("22", "22", formattedDate, formattedTime, AppData.userId));
            db.addNote(new NoteObj("33", "33", formattedDate, formattedTime, AppData.userId));
            db.addNote(new NoteObj("44", "44", formattedDate, formattedTime, AppData.userId));
            db.addNote(new NoteObj("55", "55", formattedDate, formattedTime, AppData.userId));
            db.addNote(new NoteObj("66", "66", formattedDate, formattedTime, AppData.userId));
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private String pad(int i){
        if (i < 10) return "0" + i;
        return String.valueOf(i);
    }
}