package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        Button button = findViewById(R.id.res_btn);
        EditText e_username = findViewById(R.id.login_name);
        EditText e_password = findViewById(R.id.login_password);
        String username = e_username.getText().toString();
        String password = e_password.getText().toString();

        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResActivity.this,username,Toast.LENGTH_SHORT);
                Toast.makeText(ResActivity.this,password,Toast.LENGTH_SHORT);
                Intent intent = new Intent(ResActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
