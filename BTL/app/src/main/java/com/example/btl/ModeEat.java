package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ModeEat extends AppCompatActivity {
    ImageView imgBMI,imgChildren,imgAdults,imgElderly,imgSick;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_eat);
        AnhXa();
        imgBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this,BMI.class);
                startActivity(intent);
            }
        });
        imgChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this,Chidren.class);
                startActivity(intent);
            }
        });
        imgAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this,Adults.class);
                startActivity(intent);
            }
        });
        imgElderly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this,Elderly.class);
                startActivity(intent);
            }
        });
        imgSick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this,Sick.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa(){
        imgBMI=findViewById(R.id.imgBMI);
        imgChildren=findViewById(R.id.imgChildren);
        imgAdults=findViewById(R.id.imgAdults);
        imgElderly=findViewById(R.id.imgElderly);
        imgSick=findViewById(R.id.imgSick);

    }
}
