package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView createAccount;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,10}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        email = findViewById(R.id.login_mail);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_btn);
        createAccount = findViewById(R.id.create_new_account);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Valid Email Id Required");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (!password.getText().toString().trim().matches(passwordPattern)) {
                    password.setError("Strong Password Required");
                } else if (email.getText().toString().trim().equals("maulik@gmail.com") && password.getText().toString().trim().equalsIgnoreCase("maulik@123")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Unsu ccessfully", Toast.LENGTH_LONG).show();
                }

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
