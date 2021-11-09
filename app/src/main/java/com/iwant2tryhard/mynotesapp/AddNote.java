package com.iwant2tryhard.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.iwant2tryhard.mynotesapp.core.NoteObj;
import com.iwant2tryhard.mynotesapp.core.StorageDatabase;

import java.util.Calendar;
import java.util.Objects;

public class AddNote extends AppCompatActivity {

    Toolbar toolbar;
    EditText titleEditText, noteContentEditText;
    Calendar calendar;

    String formattedDate, formattedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = findViewById(R.id.toolbar);
        titleEditText = findViewById(R.id.usernameEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        calendar = Calendar.getInstance();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Note");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        formattedDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
        formattedTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    Objects.requireNonNull(getSupportActionBar()).setTitle(s);
                }else{
                    Objects.requireNonNull(getSupportActionBar()).setTitle("New Note");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_mgnt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.confirmMenuItem){
            NoteObj note = new NoteObj(titleEditText.getText().toString(), noteContentEditText.getText().toString(), formattedDate, formattedTime);
            StorageDatabase db = new StorageDatabase(this);
            db.addNote(note);

            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.cancelMenuItem){
            //if ()
            Toast.makeText(this, "Note Canceled!", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }

    private String pad(int i){
        if (i < 10) return "0" + i;
        return String.valueOf(i);
    }
}