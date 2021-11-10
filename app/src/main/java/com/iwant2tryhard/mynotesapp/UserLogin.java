package com.iwant2tryhard.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.iwant2tryhard.mynotesapp.core.AppData;
import com.iwant2tryhard.mynotesapp.core.NoteUser;
import com.iwant2tryhard.mynotesapp.core.UserDatabase;

import java.util.List;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usernameEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty()) {
                    UserDatabase db = new UserDatabase(userLogin);
                    List<NoteUser> users = db.getAllUsers();

                    for (int i = 0; i < users.size(); i++) {
                        NoteUser user = users.get(i);
                        if (user.getUSERNAME().equals(usernameEditText.getText().toString())
                                && user.getPASSWORD().equals(passwordEditText.getText().toString())) {
                            AppData.isLoggedIn = true;
                            AppData.userId = i;
                            Intent intent = new Intent(userLogin, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}