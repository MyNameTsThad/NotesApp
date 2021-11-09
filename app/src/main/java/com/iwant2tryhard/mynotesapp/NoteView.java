package com.iwant2tryhard.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iwant2tryhard.mynotesapp.core.NoteObj;
import com.iwant2tryhard.mynotesapp.core.StorageDatabase;

public class NoteView extends AppCompatActivity {
    TextView noteContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noteContentTextView = findViewById(R.id.noteContentTextView);

        Intent i = getIntent();
        int id = i.getIntExtra("ID", 0);

        Toast.makeText(this, "ID RECIVED -> " + (id), Toast.LENGTH_SHORT).show();

        StorageDatabase db = new StorageDatabase(this);
        NoteObj note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTITLE());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noteContentTextView.setText(note.getCONTENT());


        FloatingActionButton editFloatingButton = findViewById(R.id.editFloatingButton);
        editFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_modification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteMenuItem){
            Intent i = new Intent(this, AddNote.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}