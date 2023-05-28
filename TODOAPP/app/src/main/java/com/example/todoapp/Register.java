package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mloginBtn;
    ProgressBar progressBar;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerViewModel = new ViewModelProvider(this,(ViewModelProvider.Factory)ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(RegisterViewModel.class);
        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mloginBtn = findViewById(R.id.loginSignupBtn);
        progressBar = findViewById(R.id.progressBar);


        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullName = mFullName.getText().toString();
                final String phone = mPhone.getText().toString();


                if(TextUtils.isEmpty(email)){
                     mEmail.setError("Email is Required");
                     return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                User user = new User(fullName, email, password, phone);
                registerViewModel.saveRegisteredUser(user);

                Toast.makeText(Register.this, "Registered Successfully.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();

            }
        });
    }
}