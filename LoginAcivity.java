package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.login_btn);
        Button button1 = findViewById(R.id.reg_btn);

        EditText e_username = findViewById(R.id.login_name);
        EditText e_password = findViewById(R.id.login_password);
        String username = e_username.getText().toString();
        String password = e_password.getText().toString();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,username,Toast.LENGTH_SHORT);
                Toast.makeText(LoginActivity.this,password,Toast.LENGTH_SHORT);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ResActivity.class);
                startActivity(intent);
            }
        });
    }
}
