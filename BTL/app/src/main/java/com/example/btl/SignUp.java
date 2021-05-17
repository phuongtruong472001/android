package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText edtName,edtSurname,edtUsername,edtPassword,edtConFirmPassword;
    Button btnSignUp1;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         // có ít nhất 01 ký tự số
                    "(?=.*[a-z])" +         // có ít nhất 01 ký chữ thường
                   "(?=.*[!@#$%^&*=+])" +  // có ít nhất 01 ký tự đặc biệt
                    "(?=\\S+$)" +           // không được có khoảng trắng (space)
                    ".{6,}" +               // có ít nhất 6 ký tự
                    "$");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        AnhXa();
        String password=edtPassword.getText().toString().trim();
        String ConfirmPassword=edtConFirmPassword.getText().toString().trim();
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword(password) ) {
                    return;
                }
                if(!thu(password,ConfirmPassword)) {
                    return;
                }


                Intent intent=new Intent(SignUp.this, Welcome.class);
                startActivity(intent);
            }
        });

    }

    public void AnhXa()
    {
        edtName=findViewById(R.id.edtName);
        edtSurname=findViewById(R.id.edtSurname);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        edtConFirmPassword=findViewById(R.id.edtConFirmPassword);
        btnSignUp1=findViewById(R.id.btnSignUp1);
    }
    private boolean validatePassword(String passwordInput ) {
        passwordInput = edtPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {

            edtPassword.setError("Mật khẩu không thể bỏ trống.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            edtPassword.setError("Mật khẩu bao gồm ít nhất 01 ký tự chữ thường, 01 ký tự chữ hoa, 01 ký tự số và 01 ký tự đặc biệt.");
            return false;
        } else {
            edtPassword.setError(null);
            return true;
        }
    }

    private boolean thu(String passwordInput,String ConfirmPassword ) {
        //passwordInput = edtPassword.getText().toString().trim();

        if (!passwordInput.equals(ConfirmPassword)) {

             edtConFirmPassword.setError("Mật khẩu không khớp !");
            return false;
        } else {
        edtConFirmPassword.setError(null);
        return true;

        }
    }



}
