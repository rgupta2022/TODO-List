package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //    EditText mEmail, mpassword;
    //    Button mLoginBtn;
    //    TextView mCreateBtn;
    //    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText txtUserName = findViewById(R.id.email);
        EditText txtPassword = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.loginBtn);
        TextView txtRegister = findViewById(R.id.registerBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtUserName.getText().toString().equals("rahul") &&
                        txtPassword.getText().toString().equals("77261097")) {
                    // store login token into shared preferences
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPreferences.edit();
                    spEditor.putString("loginToken", "Loggedin");
                    spEditor.commit();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(txtUserName.getText().toString())){
                    txtUserName.setError("EMAIL IS REQUIRED!!!");
                    return;
                }

                if (TextUtils.isEmpty(txtPassword.getText().toString())){
                    txtPassword.setError("PASSWORD IS REQUIRED!!!");
                    return;
                }

                Toast.makeText(Login.this, "Welcome to Login Page", Toast.LENGTH_SHORT).show();
            }
        });

        txtRegister = findViewById(R.id.loginSignupBtn);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Welcome to Register Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }

        @Override
        public void onBackPressed() {
            // do nothing, effectively disabling the back button
        }
}
