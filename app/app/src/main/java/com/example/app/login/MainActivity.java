package com.example.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.mainlayout.Welcome;
import android.app.Activity;
import android.view.Window;
public class MainActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnSignUp,btnSignIn;
    CheckBox cbSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this, Welcome.class);
                startActivity(intent1);
            }
        });

    }
    public void AnhXa()
    {
        edtUsername=findViewById(R.id.Username);
        edtPassword=findViewById(R.id.Password);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);
        cbSave=findViewById(R.id.cbSave);
    }

}