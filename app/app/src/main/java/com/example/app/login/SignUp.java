package com.example.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.R;
import com.example.app.mainlayout.Welcome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText edtName,edtSurname,edtUsername,edtPassword,edtConFirmPassword,edtAge;
    Button btnSignUp1;
    JSONArray jsonArray;
    private static final String URL ="https://haui-hit-food.herokuapp.com/api/person";
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AnhXa();

        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String password=edtPassword.getText().toString().trim();
                String ConfirmPassword=edtConFirmPassword.getText().toString().trim();
                if (!validatePassword(password)) {
                    return;
                }
                if(!test(password,ConfirmPassword))
                {
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", edtUsername.getText().toString().trim());
                    jsonObject.put("password", edtPassword.getText().toString().trim());
                    jsonObject.put("name", edtName.getText().toString().trim());
                    jsonObject.put("surname", edtSurname.getText().toString().trim());
                    jsonObject.put("age", edtAge.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String requestbody = jsonObject.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUp.this, "Đăng kí thành công !", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this, "Lỗi rồi !", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if (requestbody == null) return null;
                        else {
                            try {
                                return requestbody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }
                };
                requestQueue.add(stringRequest);
                Intent intent = new Intent(SignUp.this, Welcome.class);
                startActivity(intent);
            }


        });
}


    private boolean test(String password,String ConfirmPassword) {

        if (ConfirmPassword.isEmpty()) {
            Toast.makeText(SignUp.this,"không thể để trống!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(ConfirmPassword)) {
            Toast.makeText(SignUp.this,"không đúng mật khẩu!",Toast.LENGTH_SHORT).show();
            return false;
        }else {

            return true;
        }
    }

    public void AnhXa()
    {
        edtName=findViewById(R.id.edtName);
        edtSurname=findViewById(R.id.edtSurname);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        edtConFirmPassword=findViewById(R.id.edtConFirmPassword);
        btnSignUp1=findViewById(R.id.btnSignUp1);
        edtAge=findViewById(R.id.edtAge);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }


}
