package com.iwant2tryhard.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.iwant2tryhard.mynotesapp.core.AppData;
import com.iwant2tryhard.mynotesapp.core.JsonDataObject;
import com.iwant2tryhard.mynotesapp.core.NoteUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogin extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;

    private final UserLogin userLogin = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        //reading from file
        FileInputStream fis = null;
        Map<String, Map<String, String>> availableUsers = new HashMap<>();
        try {
            fis = openFileInput("users.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();

            Gson gson = new Gson();
            JsonDataObject data = gson.fromJson(json, JsonDataObject.class);
            availableUsers = data.getObjectMap();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String, Map<String, String>> finalAvailableUsers = availableUsers;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "clicked");
                Log.d("click", "usernameEditText: '" + usernameEditText.getText().toString() + "'");
                Log.d("click", "passwordEditText: '" + passwordEditText.getText().toString() + "'");

                if (!usernameEditText.getText().toString().isEmpty()) {
                    List<NoteUser> users = NoteUser.mapsToUser(finalAvailableUsers);
                    Log.d("noteuser-get", "users: " + Arrays.toString(users.toArray()));

                    for (int i = 0; i < users.size(); i++) {
                        NoteUser user = users.get(i);
                        Log.d("noteuser-get", "user id: " + i);
                        Log.d("noteuser-get", "username: " + user.getUSERNAME());
                        Log.d("noteuser-get", "user password: " + user.getPASSWORD());
                        if (user.getUSERNAME().equals(usernameEditText.getText().toString())
                                && user.getPASSWORD().equals(passwordEditText.getText().toString())) {
                            Log.d("test", "logged in using " + usernameEditText.getText().toString() + ", " + passwordEditText.getText().toString());
                            AppData.isLoggedIn = true;
                            AppData.userId = i;
                            Toast.makeText(userLogin, "Logged in as " + user.getUSERNAME() + " (id:" + i + ")", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(userLogin, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}