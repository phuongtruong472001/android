package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainFood extends AppCompatActivity {
    TextView TenMon,NguyenLieu,CachCheBien,ChatDinhDuong;
    ImageView back,imageFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        AnhXa();
        Intent intent=getIntent();

        TenMon.setText(String.valueOf(intent.getStringExtra("FoodName")));
        NguyenLieu.setText(String.valueOf(intent.getStringExtra("Material")));
        CachCheBien.setText(String.valueOf(intent.getStringExtra("Recipes")));
        ChatDinhDuong.setText(String.valueOf(intent.getStringExtra("Nutrition")));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainFood.this,ListFood.class);
                startActivity(intent1);
            }
        });
    }
    void AnhXa()
    {
        TenMon=findViewById(R.id.TenMon);
        NguyenLieu=findViewById(R.id.NguyenLieu);
        CachCheBien=findViewById(R.id.CachCheBien);
        ChatDinhDuong=findViewById(R.id.ChatDinhDuong);
        back=findViewById(R.id.back);
        imageFood=findViewById(R.id.image);
    }
}
