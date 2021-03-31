package com.example.demo9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView TacGia,Gia,DanhGia,MoTa,LuotDG,TheLoai,SLTrang;
    ImageView image;
    Button GuiDG;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        Intent intent=getIntent();
        TacGia.setText(String.valueOf(intent.getStringExtra("author")));
        Gia.setText(String.valueOf(intent.getStringExtra("price")));
        DanhGia.setText(String.valueOf(intent.getStringExtra("DanhGia")));
        MoTa.setText(String.valueOf(intent.getStringExtra("description")));
        LuotDG.setText(String.valueOf(intent.getStringExtra("numOfReview")));
        TheLoai.setText(String.valueOf(intent.getStringExtra("categoty")));
        SLTrang.setText(String.valueOf(intent.getStringExtra("numOfPage")));
        GuiDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Đã gửi đánh giá !", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent1);
            }
        });
    }
    public void AnhXa()
    {
        image=findViewById(R.id.image);
        TacGia=findViewById(R.id.TacGia);
        Gia=findViewById(R.id.Gia);
        DanhGia=findViewById(R.id.DanhGia);
        MoTa=findViewById(R.id.MoTa);
        LuotDG=findViewById(R.id.LuotDG);
        TheLoai=findViewById(R.id.TheLoai);
        SLTrang=findViewById(R.id.SLTrang);;
        GuiDG=findViewById(R.id.GuiDG);
        back=findViewById(R.id.back);
    }

}