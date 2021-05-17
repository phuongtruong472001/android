package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    ImageView imgIntroduce,imgFood,imgMode,imgTower;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AnhXa();
        imgIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome.this, Introduce.class);
                startActivity(intent);
            }
        });
        imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this,ListFood.class);
                startActivity(intent1);
            }
        });
        imgMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this,ModeEat.class);
                startActivity(intent1);
            }
        });
    }
    public void AnhXa(){
        imgIntroduce=findViewById(R.id.imgIntroduce);
        imgFood=findViewById(R.id.imgFood);
        imgMode=findViewById(R.id.imgMode);
        imgTower=findViewById(R.id.imgTower);
    }
}
