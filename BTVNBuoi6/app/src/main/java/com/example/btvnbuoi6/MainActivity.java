package com.example.btvnbuoi6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button BtnNext;

//    String json = "{\n" +
//            "\"id\": 2,\n" +
//            "\"username\": \"abc2\",\n" +
//            "\"password\": \"xyz\"\n" +
//            "}";
//    String array = "[\n" +
//            "{\n" +
//            "\"id\": 2,\n" +
//            "\"username\": \"abc2\",\n" +
//            "\"password\": \"xyz\"\n" +
//            "},\n" +
//            "{\n" +
//            "\"id\": 4,\n" +
//            "\"username\": \"abc4\",\n" +
//            "\"password\": \"xyz\"\n" +
//            "},\n" +
//            "{\n" +
//            "\"id\": 5,\n" +
//            "\"username\": \"abc5\",\n" +
//            "\"password\": \"xyz\"\n" +
//            "},\n" +
//            "{\n" +
//            "\"id\": 6,\n" +
//            "\"username\": \"aaaaaaaaa\",\n" +
//            "\"password\": \"aaaaaaaaa\"\n" +
//            "}\n" +
//            "]";
//    String weather = "{\n" +
//            "  \"coord\": {\n" +
//            "    \"lon\": -0.13,\n" +
//            "    \"lat\": 51.51\n" +
//            "  },\n" +
//            "  \"weather\": [\n" +
//            "    {\n" +
//            "      \"id\": 300,\n" +
//            "      \"main\": \"Drizzle\",\n" +
//            "      \"description\": \"light intensity drizzle\",\n" +
//            "      \"icon\": \"09d\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"base\": \"stations\",\n" +
//            "  \"main\": {\n" +
//            "    \"temp\": 280.32,\n" +
//            "    \"pressure\": 1012,\n" +
//            "    \"humidity\": 81,\n" +
//            "    \"temp_min\": 279.15,\n" +
//            "    \"temp_max\": 281.15\n" +
//            "  }\n" +
//            "}";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         BtnNext= findViewById(R.id.BtnNext);
        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent1);
            }
        });
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            int id= jsonObject.getInt("id");
//            String username = jsonObject.getString("username");
//            String passsword = jsonObject.getString("password");
//            Account account = new Account(id,username,passsword);
//            Log.d("TAG", account.getUsename()+", " +account.getPassword());
//            JSONArray jsonArray = new JSONArray(array);
//            List<Account> list = new ArrayList<>();

//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                int id1 = jsonObject1.getInt("id");
//                String username1 = jsonObject1.getString("username");
//                String passsword1 = jsonObject1.getString("password");
//                list.add(new Account(id1,username1,passsword1));
//                Log.d("TAG", id1+", "+ username1+", "+passsword1);
//            }
//            JSONObject jsonObject = new JSONObject(weather);
//            JSONObject json = jsonObject.getJSONObject("coord");
//            Log.d("TAG", json.getString("lon")+", "+json.getString("lat"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        TextView textView = findViewById(R.id.tvShow);




        /*Button btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username","aaaaaaaaa");
                    jsonObject.put("password","bbbbbbbbb");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String requestbody = jsonObject.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Lỗi rồi :(");
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if(requestbody==null) return null;
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
            }
        });
        Button btnPut = findViewById(R.id.btnPut);
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("password","hihi");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String requestbody = jsonObject.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.PATCH, url+"/7", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Lỗi rồi :(");
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if(requestbody==null) return null;
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
            }
        });
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url+"/11", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText("Xóa thành công!"+response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Lỗi rồi");
                    }
                });
                requestQueue.add(stringRequest);
            }
        });*/
    }
}